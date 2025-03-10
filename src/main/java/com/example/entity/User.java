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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "メールアドレスは必須です。")
    @Email(message = "有効なメールアドレスを入力してください。")
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    // パスワードチェック
    @NotBlank(message = "パスワードを入力してください。")
    @Column(nullable = false, length = 255)
    private String password;

    @NotBlank(message = "氏名は必須です。")
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 10)
    private String nickname;

    @NotBlank(message = "電話番号は必須です。")
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @NotBlank(message = "住所は必須です。")
    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LogicalDeleteStatus logicalDeleteStatus = LogicalDeleteStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
