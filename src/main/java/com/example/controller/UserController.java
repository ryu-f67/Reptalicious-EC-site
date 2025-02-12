package com.example.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.CategoryService;
import com.example.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "users/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "users/editProfile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@Valid @ModelAttribute User updatedUser, BindingResult bindingResult, 
                                @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (bindingResult.hasErrors()) {
            return "users/editProfile";
        }
        
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        
        if(!user.getEmail().equals(updatedUser.getEmail()) && userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("mailError", "メールアドレスは、すでに使用されています。");
            return "auth/register";
        }
        
        if(!user.getPhoneNumber().equals(updatedUser.getPhoneNumber()) && userService.findByPhoneNumber(updatedUser.getPhoneNumber()).isPresent()) {
            model.addAttribute("phoneError", "電話番号は、すでに使用されています。");
            return "users/editProfile";
        }
        
        userService.updateUserProfile(user, updatedUser);
        return "redirect:/users/profile";
    }
    
    @GetMapping("/profile/editPass")
    public String showEditPassword(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "users/editPassword";
    }
    
    @PostMapping("/profile/editPass")
    public String updatePassword(@RequestParam("password") String updatedPassword, 
                                @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("categories", categoryService.getActiveCategory());
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        userService.updatePassword(user, updatedPassword);
        return "redirect:/users/profile";
    }

}
