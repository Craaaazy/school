package com.example.school.repository;

import com.example.school.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositroy extends JpaRepository<Role, String> {

    Role findByName(String name);
}
