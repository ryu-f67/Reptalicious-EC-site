package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.LogicalDeleteStatus;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public String encodedPassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }
    
    public String encodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));
    }
    
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
    
    @Transactional
    public User updateUserProfile(User user, User updatedUser) {
        user.setName(updatedUser.getName());
        user.setNickname(updatedUser.getNickname());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAddress(updatedUser.getAddress());
        return userRepository.save(user);
    }
    
    @Transactional
    public User updatePassword(User user, String updatedPassword) {
        user.setPassword(encodedPassword(updatedPassword));
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    
    @Transactional
    public User removeUser(User user) {
        user.setLogicalDeleteStatus(LogicalDeleteStatus.DELETED);
        return userRepository.save(user);
    }
    
    @Transactional
    public User restoreUser(User user) {
        user.setLogicalDeleteStatus(LogicalDeleteStatus.ACTIVE);
        return userRepository.save(user);
    }
    
}
