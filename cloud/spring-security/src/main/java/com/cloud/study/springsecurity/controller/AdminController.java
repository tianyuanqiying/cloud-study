package com.cloud.study.springsecurity.controller;

import com.cloud.study.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/get")
    public String get() {
        return "get";
    }

    @GetMapping("/getSession")
    public String getSession() {
        return userService.getSession();
    }
}
