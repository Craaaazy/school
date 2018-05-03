package com.example.school.repository;


import com.example.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User save(User user);
    User findByUsername(String name);
    List<User> findAll();
    User findByEmail(String email);
}
