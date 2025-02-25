package com.example.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Category;
import com.example.entity.LogicalDeleteStatus;
import com.example.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    // カテゴリ一覧表示
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("newCategory", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categories/list";
    }
    
    // カテゴリ追加処理
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category newCategory) {
        categoryService.saveCategory(newCategory);
        return "redirect:/admin/categories";
    }
    
    // カテゴリ論理削除処理
    @PostMapping("/toggle")
    public String removeCategory(@RequestParam Long id, Model model) {
        try {
            Category category = categoryService.getCategoryById(id);
            if(category.getLogicalDeleteStatus() == LogicalDeleteStatus.ACTIVE) {
                categoryService.deleteCategory(id);
            } else {
                categoryService.activeCategory(id);
            }
            
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/categories";
    }
    
}
