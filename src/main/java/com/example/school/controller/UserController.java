package com.example.school.controller;

import com.example.school.model.User;
import com.example.school.service.UserService;
import com.example.school.util.MD5Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Value("${filepath}")
    private String filepath;

    @GetMapping("/profile")
    public String profile(ModelMap modelMap, Principal principal){
        User user = userService.findByUsername(principal.getName());

        modelMap.addAttribute("user", user);

        return "userprofile";
    }


    @GetMapping("/getIcon")
    @ResponseBody
    public void getIcon(Principal principal, ModelMap modelMap){
        User user = userService.findByUsername(principal.getName());
        modelMap.addAttribute("name", user.getUsername());

        String md5 = new MD5Generator().generateMD5(user.getEmail().toLowerCase().trim());
        modelMap.addAttribute("md5", md5);


    }

}
