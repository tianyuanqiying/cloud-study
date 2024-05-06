package com.cloud.study.springboottkmybatis.controller;

import com.cloud.study.springboottkmybatis.entity.Account;
import com.cloud.study.springboottkmybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/queryOne")
    public Account queryOne(){
        return accountService.queryOne(1);
    }

    @GetMapping("/query2")
    public Account queryOne2(){
        return accountService.queryOne2(1);
    }

    @GetMapping("/query3")
    public List query3(){
        return accountService.query3(2);
    }

    @GetMapping("/query4")
    public Account query4(){
        return accountService.query4(2, 2);
    }

    @GetMapping("/query5")
    public Account query5(){
        return accountService.query5(2, 2);
    }
    @GetMapping("/insert2")
    public void insert() {
        Account account = new Account();
        account.setMoney(10);
        account.setUserId("12138");
        account.setBizId(2);
        account.setUniqueId(2);
        account.setVersion(1);
        accountService.insert2(account);
        return;
    }
    @GetMapping("/insert3")
    public void insert3() {
        List<Account> list = new ArrayList<>();

        Account account = new Account();
        account.setMoney(10);
        account.setUserId("12139");
        account.setBizId(3);
        account.setUniqueId(3);
        account.setVersion(1);

        list.add(account);

        Account account2 = new Account();
        account2.setMoney(10);
        account2.setUserId("12139");
        account2.setBizId(4);
        account2.setUniqueId(4);
        account2.setVersion(1);
        list.add(account2);
        accountService.insert3(list);
        return;
    }
    @GetMapping("/updateById")
    public void updateById() {
        Account account = new Account();
        account.setId(3);
        account.setMoney(10);
        account.setUserId("12138");
        account.setBizId(2);
        account.setUniqueId(2);
        account.setVersion(3);
        accountService.updateById(account);
        return;
    }

    @GetMapping("/updateById2")
    public void updateById2() {
        Account account = new Account();
        account.setId(3);
        account.setMoney(10);
        account.setUserId("12138");
        account.setBizId(2);
        account.setUniqueId(2);
        account.setVersion(3);
        accountService.updateById2(account);
        return;
    }
    @GetMapping("/deleteById")
    public void deleteById(){
         accountService.deleteById(3);
    }
    @GetMapping("/deleteByIds")
    public void deleteByIds(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        accountService.deleteByIds(list);
    }
}
