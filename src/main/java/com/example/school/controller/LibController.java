package com.example.school.controller;

import com.example.school.dto.LendedBook;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/myBook")
    public String getMybook(){
        return "mybook";
    }

    @PostMapping("/lend")
    @ResponseBody
    public String postLend(@RequestParam Map<String, String> map, Principal principal){
        Book book = bookService.findByName(map.get("name"));
        User user = userService.findByUsername(principal.getName());

        List<UserXBook> list = userXBookService.findByUser(user);

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBook().getId() == book.getId()){
                return "不能借同一本";
            }
        }

        book.setNum(book.getNum() - 1);
        UserXBook userXBook = new UserXBook();
        userXBook.setBook(book);
        userXBook.setUser(user);
        userXBook.setLendedDate(new Date(System.currentTimeMillis()));

        bookService.save(book);
        userXBookService.save(userXBook);

        return "success";
    }

    @GetMapping("/lend")
    @ResponseBody
    public List<LendedBook> getLend(Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<UserXBook> list = userXBookService.findAll();

        List<LendedBook> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            UserXBook aList = list.get(i);
            if (aList.getUser().getUsername() == user.getUsername()) {
                LendedBook lendedBook = new LendedBook(aList.getBook().getName(), aList.getLendedDate());
                list1.add(lendedBook);
            }
        }

        return list1;
    }

    @DeleteMapping("/lend/{name}")          //还书
    @ResponseBody
    public String ReturnBook(@PathVariable String name, Principal principal){
        int days = -1;
        Book book = bookService.findByName(name);
        book.setNum(book.getNum() + 1);
        List<UserXBook> list = userXBookService.findByBook(book);

        User user = userService.findByUsername(principal.getName());

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser() == user){

                Calendar lendedDate = Calendar.getInstance();
                lendedDate.setTime((list.get(i).getLendedDate()));
                Date nowDate = new Date(System.currentTimeMillis());
                Calendar now = Calendar.getInstance();
                now.setTime(nowDate);

                Long lendDays = now.getTime().getTime() - lendedDate.getTime().getTime();
                days = (int) (lendDays/1000/60/60/24);
            }
        }

        userXBookService.deleteUserXBookByBook(book);
        bookService.save(book);

        if(days >= 60){
            return "you've over " + (days-60) + " days";
        }else {
            return "you've lended this book for " + days + "days";
        }

    }

}
