package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // ユーザーの注文を取得
    public List<Order> getOrdersByUser(String email) {
        return orderRepository.findByUserEmailOrderByCreatedAtDesc(email);
    }

    // 特定の注文を取得（ユーザー用）
    public Order getOrderByIdAndUser(Long id, String email) {
        return orderRepository.findByIdAndUserEmail(id, email).orElseThrow(() -> new IllegalArgumentException("無効な注文ID: " + id));
    }
    
    // IDで注文詳細を取得
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("無効な注文詳細ID: " + id));
    }

    // すべての注文を取得（管理者用）
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    // 特定の注文を取得（管理者用）
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("無効な注文ID: " + id));
    }

    // 注文を保存
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
