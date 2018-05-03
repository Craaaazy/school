package com.example.school.service;

import com.example.school.model.User;


public interface UserService {
    User save(User user);
    User findByEmail(String email);
    User findByUsername(String name);
}
