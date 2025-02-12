package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.entity.User;
import com.example.security.CustomUserDetails;
import com.example.service.CategoryService;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.service.ReviewService;
import com.example.service.UserService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
    
    // レビュー一覧
    @GetMapping("/{id}")
    public String listReviews(@PathVariable Long id, 
                              Model model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", categoryService.getActiveCategory());
            return "operationError";
        }
        List<Review> reviews = reviewService.getReviewByProduct(product);
        
        Double averageRating = reviewService.getAverageRatingByProduct(product);
        if (averageRating==null) {
            averageRating = 0.0;
        }
        model.addAttribute("categories", categoryService.getActiveCategory());
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", averageRating);
        return "reviews/list";
    }
    
    // レビュー投稿画面
    @GetMapping("/{id}/new")
    public String showCreateForm(@PathVariable Long id, 
                                 @RequestParam Long detailId,
                                 @AuthenticationPrincipal CustomUserDetails userDetails,
                                 Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        OrderDetail orderDetail = orderService.getOrderDetailById(detailId);
        model.addAttribute("categories", categoryService.getActiveCategory());
        if (user != orderDetail.getOrder().getUser()) {
            return "NoAuthority";
        }
        Optional<Review> exsistingReview = reviewService.getReviewByOrderDetail(orderDetail);
        if (exsistingReview.isPresent()) {
            model.addAttribute("orderDetail", orderDetail);
            model.addAttribute("review", exsistingReview.get());
        } else {
            model.addAttribute("orderDetail", orderDetail);
            model.addAttribute("review", new Review());
        }
        return "reviews/form";
    }
    
    // レビュー投稿処理
    @PostMapping("/{id}/new")
    public String createReview(@PathVariable Long id,
                               @RequestParam Long detailId,
                               @ModelAttribute Review review,
                               @AuthenticationPrincipal CustomUserDetails userDetails,
                               Model model) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("categories", categoryService.getActiveCategory());
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "operationError";
        }
        OrderDetail orderDetail = orderService.getOrderDetailById(detailId);
        
        if (user != orderDetail.getOrder().getUser()) {
            return "NoAuthority";
        }
        Optional<Review> exsistingReview = reviewService.getReviewByOrderDetail(orderDetail);
        if (exsistingReview.isPresent()) {
            exsistingReview.get().setRating(review.getRating());
            exsistingReview.get().setDescription(review.getDescription());
            reviewService.updateReview(exsistingReview.get(), review);
        } else {
            reviewService.saveReview(user, orderDetail, review, product);
        }
        return "redirect:/reviews/{id}";
    }
    
}
