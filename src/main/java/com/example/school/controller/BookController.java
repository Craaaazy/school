package com.example.school.controller;


import com.example.school.model.Book;
import com.example.school.service.BookService;
import com.example.school.service.RoleService;
import com.example.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping("/book")
    public List<Book> getBook() {return bookService.findAll();}

    @PostMapping("/book")
    public Book postBook(@RequestBody Map<String, String> map){
        Book existed;
        int num = Integer.parseInt(map.get("num"));

        if((existed = bookService.findByName(map.get("name"))) != null){
            existed.setNum(existed.getNum() + num);
            return bookService.save(existed);
        }else{
            Book book = new Book();
            book.setName(map.get("name"));
            book.setNum(num);
            return bookService.save(book);
        }
    }



    // @GetMapping("/{}") 这里是通过name还是id别的暂时不确定
}
