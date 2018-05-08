package com.example.school.service;

import com.example.school.model.User;

import java.util.List;


public interface UserService {
    User save(User user);
    User findByEmail(String email);
    User findByUsername(String name);
    User findByVaridateCode(String code);
    List<User> findAll();
}
