package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.CartItem;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.CartService;
import com.example.service.CategoryService;
import com.example.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;
    
    // カート内容表示
    @GetMapping
    public String showCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        List<CartItem> cartItems = cartService.getCartItemsByUser(user);
        model.addAttribute("cartItems", cartItems);
        
        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("categories", categoryService.getActiveCategory());
        return "cart/cart";
    }
    
    // カートに商品追加
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, 
                            @AuthenticationPrincipal CustomUserDetails userDetails, 
                            Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        try {
            cartService.addItemToCart(user, productId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        return "redirect:/products/" + productId;
    }
    
    // カート内商品数量更新
    @PostMapping("/update/{id}")
    public String updateCartItem(@PathVariable Long id, 
                                @RequestParam int quantity,
                                @AuthenticationPrincipal CustomUserDetails userDetails, 
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        try {
            cartService.updateCartItem(id, user, quantity);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        return "redirect:/cart";
    }
    
    // カートから商品削除
    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, 
                                @AuthenticationPrincipal CustomUserDetails userDetails, 
                                Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        try {
            cartService.removeCartItem(id, user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        return "redirect:/cart";
    }

}
