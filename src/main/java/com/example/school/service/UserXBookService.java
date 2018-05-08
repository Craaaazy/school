package com.example.school.service;


import com.example.school.model.Book;
import com.example.school.model.User;
import com.example.school.model.UserXBook;

import java.util.List;

public interface UserXBookService {
    UserXBook save(UserXBook userXBook);
    List<UserXBook> findByUser(User user);
    List<UserXBook> findAll();
    void deleteUserXBookByBook(Book book);
}
