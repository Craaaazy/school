package com.example.school.dto;

import java.sql.Date;

public class LendedBook {

    private String name;
    private Date LendedDate;

    public LendedBook(String name, Date lendedDate) {
        this.name = name;
        LendedDate = lendedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLendedDate() {
        return LendedDate;
    }

    public void setLendedDate(Date lendedDate) {
        LendedDate = lendedDate;
    }

}
