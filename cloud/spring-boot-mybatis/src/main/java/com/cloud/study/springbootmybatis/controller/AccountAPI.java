package com.cloud.study.springbootmybatis.controller;

import com.cloud.study.springbootmybatis.common.BaseCommonService;
import com.cloud.study.springbootmybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAPI {
    @Autowired
    AccountService accountService;
    @GetMapping("/get")
    public Object get() {
        return accountService.selectByBizId(1);
    }


    @GetMapping("/get2")
    public Object get2() {
        return accountService.selectByBizId2(1);
    }
    @GetMapping("/get3")
    public Object get3() {
        return accountService.selectByBizId3(1);
    }


    @GetMapping("/get4")
    public Object get4() {
        return accountService.selectByBizIdAndUniqueId(1, 1);
    }

}
