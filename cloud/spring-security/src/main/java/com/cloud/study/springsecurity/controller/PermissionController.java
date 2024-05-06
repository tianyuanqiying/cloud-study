package com.cloud.study.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/Permission")
public class PermissionController {
    @GetMapping("/havePermission")
    public String havePermission() {
        System.out.println("havePermission");
        return "havePermission";
    }

    @GetMapping("/notPermission")
    public String notPermission() {
        System.out.println("notPermission");
        return "notPermission";
    }

    @GetMapping("/hasAnyPermission")
    public String hasAnyPermission() {
        System.out.println("hasAnyPermission");
        return "hasAnyPermission";
    }

    
}
