package com.example.school.service.impl;

import com.example.school.event.TestEvent;
import com.example.school.model.Book;
import com.example.school.repository.BookRepository;
import com.example.school.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ApplicationContext applicationContext;


    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findAll() {
        applicationContext.publishEvent(new TestEvent(this));
        return bookRepository.findAll();
    }

}
