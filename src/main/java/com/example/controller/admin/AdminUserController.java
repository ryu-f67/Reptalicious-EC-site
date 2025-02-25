package com.example.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.LogicalDeleteStatus;
import com.example.entity.User;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users/list";
    }
    
    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "admin/users/detail";
    }
    
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なユーザーID: " + id));
        userService.removeUser(user);
        return "redirect:/admin/users";
    }
    
    @PostMapping("/{id}/restore")
    public String restoreUser(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なユーザーID: " + id));
        user.setLogicalDeleteStatus(LogicalDeleteStatus.ACTIVE);
        userService.restoreUser(user);
        return "redirect:/admin/users";
    }
}
