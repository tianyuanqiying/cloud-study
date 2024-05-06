package com.cloud.study.springsecurity.controller;

import cn.hutool.core.util.RandomUtil;
import com.cloud.study.springsecurity.service.UserService;
import com.cloud.study.springsecurity.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author user
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/index")
    public String index() {
        return "redirect:/index.html";
    }

    @PostMapping("/toerror")
    public String error() {
        return "redirect:/error.html";
    }


    @GetMapping("/showLogin")
    public String login3() {
//        String randomString = RandomUtil.randomString(12);
//        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("token", randomString);
//        model.addAttribute("_csrf", objectObjectHashMap);
        return "login3";
    }
}
