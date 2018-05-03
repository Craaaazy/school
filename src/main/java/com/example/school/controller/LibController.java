package com.example.school.controller;

import com.example.school.model.Book;
import com.example.school.model.User;
import com.example.school.model.UserXBook;
import com.example.school.service.BookService;
import com.example.school.service.UserService;
import com.example.school.service.UserXBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/lib")
public class LibController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    UserXBookService userXBookService;

    @GetMapping("/index")
    public String getin(){
        return "bookSystem";
    }


    @PostMapping("/lend")
    @ResponseBody
    public String postLend(@RequestParam Map<String, String> map, Principal principal){
        Book book = bookService.findByName(map.get("name"));
        User user = userService.findByUsername(principal.getName());

        UserXBook userXBook = new UserXBook();
        userXBook.setBook(book);
        userXBook.setUser(user);
        userXBook.setLendedDate(new Date(System.currentTimeMillis()));
        userXBookService.save(userXBook);

        return "succ";
    }

    @GetMapping("/lend")
    @ResponseBody
    public List getLend(Principal principal){

        List list = userXBookService.findAll().
                stream().
                filter(x -> x.getUser().getUsername() == principal.getName()).
                collect(Collectors.toList());

//        List<UserXBook> list2 = userXBookService.findAll();
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < list2.size(); i++) {
//            if (list2.get(i).getUser().getId() == user.getId()) {
//                String name = list2.get(i).getBook().getName();
//                list.add(name);
//            }
//        }

        return list;
    }

}
