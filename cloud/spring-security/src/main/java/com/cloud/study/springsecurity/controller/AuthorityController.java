package com.cloud.study.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/Authority")
public class AuthorityController {
    @GetMapping("/haveAuthority")
    public String haveAuthority() {
        System.out.println("haveAuthority");
        return "haveAuthority";
    }

    @GetMapping("/notAuthority")
    public String notAuthority() {
        System.out.println("notAuthority");
        return "notAuthority";
    }

    @GetMapping("/hasAnyAuthority")
    public String hasAnyAuthority() {
        System.out.println("hasAnyAuthority");
        return "hasAnyAuthority";
    }


}
