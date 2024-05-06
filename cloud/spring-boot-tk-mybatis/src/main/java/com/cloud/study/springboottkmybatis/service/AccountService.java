package com.cloud.study.springboottkmybatis.service;

import com.cloud.study.springboottkmybatis.entity.Account;

import java.util.ArrayList;
import java.util.List;

public interface AccountService  {
    public Account queryOne(Integer id);
    public void insert(Account account);

    Account queryOne2(int i);

    List query3(int bizId);

    Account query4(int bizId, int uniqueId);

    Account query5(int bizId, int version);

    void insert2(Account account);

    void updateById(Account account);

    void deleteById(int id);

    void updateById2(Account account);

    void insert3(List<Account> account);

    void deleteByIds(ArrayList<Object> list);
}
