package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.LogicalDeleteStatus;
import com.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByLogicalDeleteStatus(LogicalDeleteStatus logicalDeleteStatus);
    
    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findTop4ByLogicalDeleteStatusOrderByCreatedAtDesc(LogicalDeleteStatus logicalDeleteStatus);
    
    @Query(value = "SELECT * FROM products WHERE logical_delete_status = 'ACTIVE' ORDER BY RAND() LIMIT 8", nativeQuery = true)
    List<Product> findRandom8ActiveProducts();
}
