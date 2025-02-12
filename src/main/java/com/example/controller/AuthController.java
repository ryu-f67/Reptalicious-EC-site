package com.example.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.CategoryService;
import com.example.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        if(userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("mailError", "メールアドレスは、すでに使用されています。");
            return "auth/register";
        }
        
        if(userService.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            model.addAttribute("phoneError", "電話番号は、すでに使用されています。");
            return "auth/register";
        }

        user.setRole(Role.ROLE_USER);
        userService.registerUser(user);
        return "auth/complete";
    }

    @GetMapping("/complete")
    public String registerComplete(Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "auth/complete";
    }
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "auth/login";
    }

}
