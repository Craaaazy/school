package com.example.school.service.impl;

import com.example.school.model.User;
import com.example.school.repository.UserRepository;
import com.example.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
}
