package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;
import com.example.entity.LogicalDeleteStatus;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByLogicalDeleteStatus(LogicalDeleteStatus logicalDeleteStatus);
    
    Category findByName(String name);
}
