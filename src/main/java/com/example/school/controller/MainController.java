package com.example.school.controller;

import com.example.school.model.Role;
import com.example.school.model.User;
import com.example.school.service.RoleService;
import com.example.school.service.UserService;
import com.example.school.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String usermailname;

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @PostMapping(value = "/login")
    public String logined(){
        return "/index";
    }

    @GetMapping(value = "/regist")
    public String regist(){
        return "/regist";
    }

    @PostMapping(value = "/regist")
    public String postRegisterUser(@RequestParam Map<String, String> map){
        Role role = roleService.findByName(map.get("role"));

        User user = new User();
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setRole(role);

        String validCode = new ValidateCodeUtil().getValidCode();
        user.setVaridateCode(validCode);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(usermailname);
        message.setTo(map.get("email"));
        message.setSubject("完成邮箱验证");
        String content = "点击下面链接激活账号:" +
                "http://localhost:8077/validatePage/validCode=" + validCode;

        message.setText(content);

        javaMailSender.send(message);
        userService.save(user);

        return "unvalidate";
    }


    @GetMapping(value = "/validatePage/{validCode}")
    public String valid(@PathVariable String validCode){
        User user = userService.findByEmail(validCode);
        user.setActive(true);
        userService.save(user);

        return "validateSuccess";
    }

}
