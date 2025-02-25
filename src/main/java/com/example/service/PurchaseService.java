package com.example.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.CartItem;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.repository.CartItemRepository;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;

@Service
public class PurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;

    // 購入処理
    @Transactional
    public Order processPurchase(User user, List<Long> cartItemIdList) throws Exception {

        Integer totalPrice = 0;
        
        // 注文を作成
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        order.setOrderNumber("dummy");
        order.setShippingFee(0);
        orderRepository.save(order);

        for (Long cartItemId : cartItemIdList) {
            CartItem cartItem = cartItemRepository.findById(cartItemId)
                    .orElseThrow(() -> new Exception("商品が存在しません。"));
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new Exception("商品が存在しません。"));

            totalPrice += product.getPrice() * cartItem.getQuantity();

            // 注文詳細を作成
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetailRepository.save(orderDetail);
            
            // 在庫を更新
            product.setStock(product.getStock()-cartItem.getQuantity());
            productRepository.save(product);
        }
        
        String orderNumber = generateOrderNumber(order.getId());
        
        // 注文を更新
        if (totalPrice >= 10000) {
            order.setTotalPrice(totalPrice);
            order.setShippingFee(0);
            
        } else {
            order.setTotalPrice(totalPrice+990);
            order.setShippingFee(990);
        }
        order.setOrderNumber(orderNumber);
        orderRepository.save(order);
        
        return order;
    }
    
    // 注文番号発行
    public String generateOrderNumber(Long id) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("%s-%06d", date, id);
    }
}
