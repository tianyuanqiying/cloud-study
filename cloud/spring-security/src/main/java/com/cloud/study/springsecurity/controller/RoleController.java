package com.cloud.study.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @GetMapping("/have")
    public String have() {
        System.out.println("have");
        return "have";
    }

    @GetMapping("/not")
    public String not() {
        System.out.println("not");
        return "not";
    }

    @GetMapping("/haveAny")
    public String haveAny() {
        System.out.println("haveAny");
        return "haveAny";
    }
}
