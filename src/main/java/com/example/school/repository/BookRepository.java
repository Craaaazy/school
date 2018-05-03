package com.example.school.repository;

import com.example.school.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    Book save(Book book);
    Book findByName(String name);
    List<Book> findAll();
}
