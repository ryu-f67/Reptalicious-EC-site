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
    private S3Service s3Service;
    
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
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    // 商品画像を更新
    @Transactional
    public Product updateProductImages(Product product, MultipartFile imageFile1, MultipartFile imageFile2, MultipartFile imageFile3) {
        try {
            if (imageFile1 != null && !imageFile1.isEmpty()) {
                String imageUrl1 = s3Service.uploadFile(product.getId(), imageFile1, "imageFile1");
                product.setImageUrl1(imageUrl1);
            } else {
                product.setImageUrl1(null);
            }
            if (imageFile2 != null && !imageFile2.isEmpty()) {
                String imageUrl2 = s3Service.uploadFile(product.getId(), imageFile2, "imageFile2");
                product.setImageUrl2(imageUrl2);
            } else {
                product.setImageUrl2(null);
            }
            if (imageFile3 != null && !imageFile3.isEmpty()) {
                String imageUrl3 = s3Service.uploadFile(product.getId(), imageFile3, "imageFile3");
                product.setImageUrl3(imageUrl3);
            } else {
                product.setImageUrl3(null);
            }

//            if (product.getImageUrl1() != null && product.getImageUrl1().isEmpty()) {
//                product.setImageUrl1(null);
//            }
//            if (product.getImageUrl2() != null && product.getImageUrl2().isEmpty()) {
//                product.setImageUrl2(null);
//            }
//            if (product.getImageUrl3() != null && product.getImageUrl3().isEmpty()) {
//                product.setImageUrl3(null);
//            }
        } catch (Exception e) {
            throw new RuntimeException("画像のアップロードに失敗しました", e);
        }
        return productRepository.save(product);
    }
    
    
    // 商品を更新
    @Transactional
    public Product updateProduct(Product product, Product updatedProduct){
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
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
