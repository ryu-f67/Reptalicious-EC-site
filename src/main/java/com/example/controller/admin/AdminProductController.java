package com.example.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.service.CategoryService;
import com.example.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    // 商品一覧表示
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/products/list";
    }

    // 商品登録フォーム表示
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.getActiveCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "admin/products/form";
    }

    // 商品登録処理
    @PostMapping("/create")
    public String createProduct(@RequestParam List<Long> selectedCategoryIdList,
                                @Valid @ModelAttribute Product product,
                                @RequestParam(value = "imageFile1", required = false) MultipartFile imageFile1,
                                @RequestParam(value = "imageFile2", required = false) MultipartFile imageFile2,
                                @RequestParam(value = "imageFile3", required = false) MultipartFile imageFile3,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/products/form";
        }
        try {
            productService.saveProduct(product);
            productService.updateProductImages(product, imageFile1, imageFile2, imageFile3);
            for (Long selectedCategoryId : selectedCategoryIdList) {
                if (selectedCategoryId != 0) {
                    Category selectedCategory = categoryService.getCategoryById(selectedCategoryId);
                    productService.saveProductCategory(product, selectedCategory);
                }
            }
        } catch (Exception e) {
            bindingResult.rejectValue("imageUrl1", "uploadError", "画像のアップロードに失敗しました");
            return "admin/products/form";
        }
        
        return "redirect:/admin/products";
    }

    // 商品編集フォーム表示
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        
        List<Long> productCategoriesId = productService.getCategoryIdByProduct(product);
        List<Category> categories = categoryService.getActiveCategory();
        
        model.addAttribute("productCategoriesId", productCategoriesId);
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "admin/products/editform";
    }

    // 商品更新処理
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, 
                                @RequestParam List<Long> updateSelectedCategoryIdList,
                                @Valid @ModelAttribute Product updatedProduct, 
                                @RequestParam(value = "imageFile1", required = false) MultipartFile imageFile1,
                                @RequestParam(value = "imageFile2", required = false) MultipartFile imageFile2,
                                @RequestParam(value = "imageFile3", required = false) MultipartFile imageFile3,
                                BindingResult bindingResult,
                                Model model){
        if (bindingResult.hasErrors()) {
            return "admin/products/editform";
        }
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        
        productService.updateProduct(product, updatedProduct);
        productService.updateProductImages(product, imageFile1, imageFile2, imageFile3);
        
        productService.removeProductCategoryByProduct(product);
        
        for (Long updateSelectedCategoryId : updateSelectedCategoryIdList) {
            if (updateSelectedCategoryId != 0) {
                Category updateSelectedCategory = categoryService.getCategoryById(updateSelectedCategoryId);
                productService.saveProductCategory(product, updateSelectedCategory);
            }
        }
        
        return "redirect:/admin/products/{id}";
    }
    
    // 商品詳細
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        if (product.getLogicalDeleteStatus().name()=="DELETED") {
            return "removedError";
        }
        List<ProductCategory> productCategories = productService.getProductCategoryByProduct(product);
        if (productCategories.isEmpty()) {
            productCategories = List.of();
        } 
        model.addAttribute("product", product);
        model.addAttribute("productCategories", productCategories);
        return "admin/products/detail";
    }

    // 商品削除処理
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        productService.removeProductCategoryByProduct(product);
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
