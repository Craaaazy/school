package com.example.school.service;

import com.example.school.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    Book findByName(String name);
    List<Book> findAll();
}
