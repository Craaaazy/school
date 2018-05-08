package com.example.school.repository;

import com.example.school.model.Book;
import com.example.school.model.User;
import com.example.school.model.UserXBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserXBookRepository extends JpaRepository<UserXBook, String>{
    UserXBook save(UserXBook userXBook);
    List<UserXBook> findByUser(User user);
    void deleteUserXBookByBook(Book book);
}
