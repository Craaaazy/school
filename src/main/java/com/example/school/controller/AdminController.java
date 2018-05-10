package com.example.school.controller;


import com.example.school.dto.BookManage;
import com.example.school.model.User;
import com.example.school.model.UserXBook;
import com.example.school.service.BookService;
import com.example.school.service.RoleService;
import com.example.school.service.UserService;
import com.example.school.service.UserXBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserXBookService userXBookService;
    @Autowired
    BookService bookService;
    @Autowired
    RoleService roleService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String usermailname;


    @GetMapping("/index")
    public String adminIndex(Principal principal){
        return "adminPage";
    }

    @GetMapping("/bookRemaind")
    @ResponseBody
    public List<BookManage> getUserBooks(){
        List<UserXBook> userXBooks = userXBookService.findAll();
        List<BookManage> bookManageList = new ArrayList<>();
        for (int i = 0; i < userXBooks.size(); i++) {
            String username = userXBooks.get(i).getUser().getUsername();
            String bookname = userXBooks.get(i).getBook().getName();

            BookManage bookManage = new BookManage(username, bookname, userXBooks.get(i).getLendedDate());

            bookManageList.add(bookManage);
        }

        return bookManageList;
    }

    @PostMapping("/bookRemaind/{username}")
    @ResponseBody
    public String remind(@PathVariable String username){
        User user = userService.findByUsername(username);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(usermailname);
        message.setTo(user.getEmail());
        message.setSubject("尽快还书!");
        String content = "请尽快还书。";

        message.setText(content);

        javaMailSender.send(message);

        return "发送成功";
    }



}
