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

        book.setNum(book.getNum() - 1);
        UserXBook userXBook = new UserXBook();
        userXBook.setBook(book);
        userXBook.setUser(user);
        userXBook.setLendedDate(new Date(System.currentTimeMillis()));

        bookService.save(book);
        userXBookService.save(userXBook);

        return "succ";
    }

    @GetMapping("/lend")
    @ResponseBody
    public List getLend(Principal principal){//这个没写完

//        List list = userXBookService.findAll().
//                stream().
//                filter(x -> x.getUser().getUsername() == principal.getName()).
//                collect(Collectors.toList());


        User user = userService.findByUsername(principal.getName());
        List<UserXBook> list = userXBookService.findAll();
        List<UserXBook> list1 = null;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser().getUsername() == user.getUsername()){
                list1.add(list.get(i));
            }
        }
        System.out.println(list1);

        return list1;
    }

    @GetMapping("/myBook")
    public String getMybook(){
        return "mybook";
    }

}
