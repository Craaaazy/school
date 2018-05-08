package com.example.school.service.impl;

import com.example.school.model.Book;
import com.example.school.model.User;
import com.example.school.model.UserXBook;
import com.example.school.repository.UserXBookRepository;
import com.example.school.service.UserXBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserXBookServiceImpl implements UserXBookService {

    @Autowired
    UserXBookRepository userXBookRepository;

    @Override
    public UserXBook save(UserXBook userXBook) {
        return userXBookRepository.save(userXBook);
    }

    @Override
    public List<UserXBook> findByUser(User user) {
        return userXBookRepository.findByUser(user);
    }

    @Override
    public List<UserXBook> findAll() {
        return userXBookRepository.findAll();
    }

    @Override
    @Transactional
    @Modifying
    public void deleteUserXBookByBook(Book book) {
        userXBookRepository.deleteUserXBookByBook(book);
    }


}
