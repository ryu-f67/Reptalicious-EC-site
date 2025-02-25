package com.example.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
@RequestMapping("/admin/stocks")
public class AdminStockController {
    
    @Autowired
    private ProductService productService;

    // 商品一覧表示
    @GetMapping
    public String listStocks(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/stocks/list";
    }
    
    // 商品在庫詳細
    @GetMapping("/{id}")
    public String showStock(@PathVariable Long id, Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        model.addAttribute("product", product);
        return "admin/stocks/detail";
    }
    
    // 在庫更新処理
    @PostMapping("/{id}/edit")
    public String updateStock(@PathVariable Long id, @ModelAttribute Product updatedProduct, Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        productService.updateStock(product, updatedProduct.getStock());
        return "redirect:/admin/stocks";
    }
}
