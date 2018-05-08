package com.example.school.service.impl;

import com.example.school.model.Role;
import com.example.school.repository.RoleRepositroy;
import com.example.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepositroy roleRepositroy;

    @Override
    public Role findByName(String name) {
        return roleRepositroy.findByName(name);
    }

}
