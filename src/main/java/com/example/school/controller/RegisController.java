package com.example.school.controller;

import com.example.school.dto.EmailDto;
import com.example.school.event.OnEmailEvent;
import com.example.school.model.Role;
import com.example.school.model.User;
import com.example.school.service.RoleService;
import com.example.school.service.UserService;
import com.example.school.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
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

        String subject = "完成邮箱验证";
        String validCode = new ValidateCodeUtil().getValidCode();
        String content = "点击下面链接激活账号: http://localhost:8077/validatePage/" + validCode;;

        String rolename = "ROLE_" + map.get("role").toUpperCase();
        Role role = roleService.findByName(rolename);

        User user = new User();
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setRole(role);

        EmailDto emailDto = new EmailDto();
        emailDto.setTo(map.get("email"));
        emailDto.setSubject(subject);
        emailDto.setMessage(content);
        emailDto.setFrom(usermailname);

        eventPublisher.publishEvent(new OnEmailEvent(emailDto));

        user.setVaridateCode(validCode);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(usermailname);
//        message.setTo(map.get("email"));
//        message.setSubject("完成邮箱验证");
//        String content = "点击下面链接激活账号:" +
//                "http://localhost:8077/validatePage/" + validCode;

//        message.setText(content);

//        javaMailSender.send(message);
        userService.save(user);

        return "unvalidate";
    }


    @GetMapping(value = "/validatePage/{validCode}")
    public String valid(@PathVariable String validCode){
        User user = userService.findByVaridateCode(validCode);
        user.setActive(true);
        userService.save(user);

        return "validateSuccess";
    }

}
