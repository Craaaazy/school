package com.example.school.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;

    private Boolean active;
    private String varidateCode;

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    private String head_icon;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserXBook> userXBook;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVaridateCode() {
        return varidateCode;
    }

    public void setVaridateCode(String varidateCode) {
        this.varidateCode = varidateCode;
    }

    public List<UserXBook> getUserXBook() {
        return userXBook;
    }

    public void setUserXBook(List<UserXBook> userXBook) {
        this.userXBook = userXBook;
    }
}
