package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.LogicalDeleteStatus;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    // すべてのカテゴリを取得
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    // IDでカテゴリを取得
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + id));
    }
    
    // カテゴリ名でカテゴリを取得
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    // ACTIVEのカテゴリを取得
    public List<Category> getActiveCategory() {
        return categoryRepository.findByLogicalDeleteStatus(LogicalDeleteStatus.ACTIVE);
    }
    
    // カテゴリを追加
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    // カテゴリをACTIVE化
    public Category activeCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + id));
        category.setLogicalDeleteStatus(LogicalDeleteStatus.ACTIVE);
        return categoryRepository.save(category);
    }
    
    // カテゴリを論理削除
    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + id));
        category.setLogicalDeleteStatus(LogicalDeleteStatus.DELETED);
        return categoryRepository.save(category);
    }
    
    // 論理削除の判定
    public boolean isDeleted(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + id));
        return category.getLogicalDeleteStatus() == LogicalDeleteStatus.DELETED;
        
    }
}
