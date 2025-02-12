package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.CartService;
import com.example.service.CategoryService;
import com.example.service.FavoriteService;
import com.example.service.ProductService;
import com.example.service.ReviewService;
import com.example.service.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ReviewService reviewService;

    // 商品一覧表示
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getActiveCategory();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products/list";
    }
    
    // カテゴリで検索
    @GetMapping("/category={categoryName}")
    public String listProductsByCategory(@PathVariable String categoryName,
                                         Model model) {
        Category category = categoryService.getCategoryByName(categoryName);
        List<Product> products = productService.getProductsByCategory(category);
        List<Category> categories = categoryService.getActiveCategory();
        model.addAttribute("heading", categoryName);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products/list";
    }
    
    // キーワードで検索
    @GetMapping("/keyword")
    public String listProductsByNameContaining(@RequestParam String keyword,
            Model model) {
        List<Product> products = productService.getProductsByNameContaining(keyword);
        List<Category> categories = categoryService.getActiveCategory();
        model.addAttribute("heading", keyword);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        return "products/list";
    }

    // 商品詳細表示
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id,
                              @AuthenticationPrincipal CustomUserDetails userDetails, 
                              Model model) {
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
        
        Double averageRating = reviewService.getAverageRatingByProduct(product);
        if (averageRating == null) {
            averageRating = 0.0;
        }

        // お気に入り判定
        boolean isFavorite = false;
        if (userDetails != null) {
            User user = userService.getUserByEmail(userDetails.getUsername());
            isFavorite = favoriteService.isFavorite(user, product);
        }
        
        // カート内判定
        boolean isInCart = false;
        if (userDetails != null) {
            User user = userService.getUserByEmail(userDetails.getUsername());
            isInCart = cartService.isInCart(user, product);
        }

        model.addAttribute("categories", categoryService.getActiveCategory());
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("product", product);
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("isInCart", isInCart);
        return "products/detail";
    }
    
}
