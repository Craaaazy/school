package com.example.school.dto;

import java.sql.Date;

public class BookManage {

    private String username;
    private String bookname;
    private Date LendedDate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Date getLendedDate() {
        return LendedDate;
    }

    public void setLendedDate(Date lendedDate) {
        LendedDate = lendedDate;
    }

    public BookManage(String username, String bookname, Date lendedDate) {
        this.username = username;
        this.bookname = bookname;
        LendedDate = lendedDate;
    }
}
