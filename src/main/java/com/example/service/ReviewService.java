package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Review;
import com.example.entity.User;
import com.example.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    // 商品のレビュー一覧を取得
    public List<Review> getReviewByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }
    
    // レビューの保存
    @Transactional
    public Review saveReview(User user, OrderDetail orderDetail, Review review, Product product) {
        review.setId(null);
        review.setUser(user);
        review.setOrderDetail(orderDetail);
        review.setProduct(product);
        return reviewRepository.save(review);
    }
    
    // レビューの更新
    @Transactional
    public Review updateReview(Review exsistingReview, Review review) {
        exsistingReview.setRating(review.getRating());
        exsistingReview.setDescription(review.getDescription());
        return reviewRepository.save(review);
    }
    
    // OrderDetailIdでレビューを検索
    public Optional<Review> getReviewByOrderDetail(OrderDetail orderDetail) {
        return reviewRepository.findByOrderDetail(orderDetail);
    }
    
    // 商品レビューの平均値を取得
    public Double getAverageRatingByProduct(Product product) {
        return reviewRepository.findAverageRatingByProduct(product);
    }
}
