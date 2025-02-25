package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;

@Controller
public class WelcomeController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/")
    public String showTopPage(Model model) {
        List<Product> newProducts = productService.getNewProducts();
        List<Product> randomProducts = productService.getRandomProducts();
        model.addAttribute("randomProducts", randomProducts);
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "welcome/top";
    }
    
    @GetMapping("/mypage")
    public String showMyPage(Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "welcome/mypage";
    }

}
