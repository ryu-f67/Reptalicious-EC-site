package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Category;
import com.example.entity.LogicalDeleteStatus;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.repository.ProductCategoryRepository;
import com.example.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // すべての商品を取得
    public List<Product> getAllProducts() {
        return productRepository.findByLogicalDeleteStatus(LogicalDeleteStatus.ACTIVE);
    }

    // IDで商品を取得
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("無効な商品ID: " + id));
    }
    
    // カテゴリで商品を取得
    public List<Product> getProductsByCategory(Category category) {
        return productCategoryRepository.findProductsByCategory(category);
    }
    
    // 商品をキーワードで検索
    public List<Product> getProductsByNameContaining(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    
    // 新しく登録された商品を4件取得
    public List<Product> getNewProducts() {
        return productRepository.findTop4ByLogicalDeleteStatusOrderByCreatedAtDesc(LogicalDeleteStatus.ACTIVE);
    }
    
    // 商品を8件ランダムに取得
    public List<Product> getRandomProducts() {
        return productRepository.findRandom8ActiveProducts();
    }
    
    // 商品のカテゴリを取得
    public List<ProductCategory> getProductCategoryByProduct(Product product) {
        return productCategoryRepository.findByProduct(product);
    }
    
    // 商品に紐づいているカテゴリのIDを取得
    public List<Long> getCategoryIdByProduct(Product product) {
        List<ProductCategory> productCategories = productCategoryRepository.findByProduct(product);
        List<Long> categoriesId = new ArrayList<Long>(); 
        for (int i=0; i<=2; i++) {
            if (i <= productCategories.size()-1) {
                categoriesId.add(productCategories.get(i).getCategory().getId());
            } else {
                categoriesId.add(0L);
            }
        }
        return categoriesId;
    }

    // 商品カテゴリを保存
    @Transactional
    public ProductCategory saveProductCategory(Product product, Category updateSelectedCategory) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProduct(product);
        productCategory.setCategory(updateSelectedCategory);
        return productCategoryRepository.save(productCategory);
    }
    
    // 商品カテゴリを削除
    @Transactional
    public void removeProductCategoryByProduct(Product product) {
        productCategoryRepository.deleteByProduct(product);
        
    }

    // 商品を登録
    @Transactional
    public Product saveProduct(Product product){
        if (product.getImageUrl1() != null && product.getImageUrl1().isEmpty()) {
            product.setImageUrl1(null);
        }
        if (product.getImageUrl2() != null && product.getImageUrl2().isEmpty()) {
            product.setImageUrl2(null);
        }
        if (product.getImageUrl3() != null && product.getImageUrl3().isEmpty()) {
            product.setImageUrl3(null);
        }
        return productRepository.save(product);
    }

    // 商品を更新
    @Transactional
    public Product updateProduct(Product product, Product updatedProduct){
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        if (updatedProduct.getImageUrl1() != null && updatedProduct.getImageUrl1().isEmpty()) {
            product.setImageUrl1(null);
        } else {
            product.setImageUrl1(updatedProduct.getImageUrl1());
        }
        if (updatedProduct.getImageUrl2() != null && updatedProduct.getImageUrl2().isEmpty()) {
            product.setImageUrl2(null);
        } else {
            product.setImageUrl2(updatedProduct.getImageUrl2());
        }
        if (updatedProduct.getImageUrl3() != null && updatedProduct.getImageUrl3().isEmpty()) {
            product.setImageUrl3(null);
        } else {
            product.setImageUrl3(updatedProduct.getImageUrl3());
        }

        return productRepository.save(product);
    }
    
    // 商品を削除
    @Transactional
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効な商品ID: " + id));
        product.setLogicalDeleteStatus(LogicalDeleteStatus.DELETED);
        return productRepository.save(product);
    }
    
    // 在庫を変更
    @Transactional
    public Product updateStock(Product product, Integer quantity){
        product.setStock(quantity);
        return productRepository.save(product);
    }
}
