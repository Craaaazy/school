package com.example.school.repository;

import com.example.school.model.User;
import com.example.school.model.UserXBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserXBookRepository extends JpaRepository<UserXBook, String>{
    UserXBook save(UserXBook userXBook);
    UserXBook findByUser(User user);
}
