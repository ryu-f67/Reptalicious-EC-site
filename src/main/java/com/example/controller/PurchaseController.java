package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Order;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.CartService;
import com.example.service.CategoryService;
import com.example.service.PurchaseService;
import com.example.service.UserService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CategoryService categoryService;
    
    // カート内の商品の購入処理
    @PostMapping("/inCart")
    public String cartItemsPurchase(@RequestParam List<Long> cartItemIdList, 
                                   @AuthenticationPrincipal CustomUserDetails userDetails, 
                                   Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("categories", categoryService.getActiveCategory());
        try {
            Order order = purchaseService.processPurchase(user, cartItemIdList);
            cartService.removeAllCartItems();
            model.addAttribute("order", order);
            return "purchase/complete";
        } catch (Exception e) {
            model.addAttribute("error", "購入処理中にエラーが発生しました");
            return "operationError";
        }
    }
}
