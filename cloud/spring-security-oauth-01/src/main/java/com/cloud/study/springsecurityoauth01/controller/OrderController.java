package com.cloud.study.springsecurityoauth01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/get")
    public Object get() {
        return "order:1";
    }
}
