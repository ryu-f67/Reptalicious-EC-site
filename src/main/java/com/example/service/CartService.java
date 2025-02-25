package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.CartItem;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CartItemRepository;
import com.example.repository.ProductRepository;

@Service
public class CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    
    // ユーザーのカート内の商品一覧を取得
    public List<CartItem> getCartItemsByUser(User user) {
        return cartItemRepository.findByUserOrderByCreatedAtDesc(user);
    }
    
    // 商品をカートに追加
    @Transactional
    public CartItem addItemToCart(User user, Long productId) throws Exception {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new Exception("商品が存在しません。");
        }
        Product product = productOpt.get();
        // 既にカートに追加されているか確認
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem.isPresent()) {
            throw new Exception("既にカートに追加されています。");
        }
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        return cartItemRepository.save(cartItem);
    }
    
    // カート内の商品の個数変更
    @Transactional
    public void updateCartItem(Long cartItemId, User user, int quantity) throws Exception {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
        if (!cartItemOpt.isPresent()) {
            throw new Exception("カートに商品が存在しません。");
        }
        CartItem cartItem = cartItemOpt.get();
        if (!cartItem.getUser().equals(user)) {
            throw new Exception("他のユーザーのカート内の商品を変更することはできません。");
        }
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    
    @Transactional
    // カートから商品を削除
    public void removeCartItem(Long cartItemId, User user) throws Exception {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
        if (!cartItemOpt.isPresent()) {
            throw new Exception("カートに商品が存在しません。");
        }
        CartItem cartItem = cartItemOpt.get();
        
        if (!cartItem.getUser().equals(user)) {
            throw new Exception("他のユーザーのカート内の商品を削除することはできません。");
        }
        cartItemRepository.delete(cartItem);
    }
    
    @Transactional
    // カートから商品をすべて削除
    public void removeAllCartItems() {
        cartItemRepository.deleteAll();
    }
    
    // カート内の商品の存在を確認
    public boolean isInCart(User user, Product product) {
        return cartItemRepository.findByUserAndProduct(user, product).isPresent();
    }
}
