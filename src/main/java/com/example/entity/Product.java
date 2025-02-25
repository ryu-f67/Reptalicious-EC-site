package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "商品名は必須です。")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "説明は必須です。")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Min(value = 0, message = "価格は0以上に設定してください。")
    @Column(nullable = false)
    private Integer price;
    
    @Min(value = 0, message = "在庫は0以上に設定してください。")
    @Column(nullable = false)
    private Integer stock;

    @URL(message = "有効なURLを入力してください。")
    @Column(name = "image_url1", length = 255, nullable = true)
    private String imageUrl1;
    
    @URL(message = "有効なURLを入力してください。")
    @Column(name = "image_url2", length = 255, nullable = true)
    private String imageUrl2;
    
    @URL(message = "有効なURLを入力してください。")
    @Column(name = "image_url3", length = 255, nullable = true)
    private String imageUrl3;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LogicalDeleteStatus logicalDeleteStatus = LogicalDeleteStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
