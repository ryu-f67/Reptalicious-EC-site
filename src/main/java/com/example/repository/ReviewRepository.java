package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByProduct(Product product);
    
    Optional<Review> findByOrderDetail(OrderDetail orderDetail);
    
    @Query("SELECT AVG(p.rating) FROM Review p WHERE p.product = :product")
    Double findAverageRatingByProduct(@Param("product") Product product);
}
