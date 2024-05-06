package com.cloud.study.springsecurityoauth01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/product")
public class PrdController {
    @GetMapping("get")
    public Object get() {
        return "product:1";
    }

}
