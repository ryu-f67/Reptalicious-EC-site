package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    
    List<ProductCategory> findByProduct(Product product);
    
    List<ProductCategory> deleteByProduct(Product product);

    @Query("SELECT pc.product FROM ProductCategory pc WHERE pc.category = :category")
    List<Product> findProductsByCategory(@Param("category") Category category);
}
