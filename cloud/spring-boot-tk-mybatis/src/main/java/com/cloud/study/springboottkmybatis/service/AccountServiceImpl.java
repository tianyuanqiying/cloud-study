package com.cloud.study.springboottkmybatis.service;

import com.cloud.study.springboottkmybatis.entity.Account;
import com.cloud.study.springboottkmybatis.mapper.AccountMapper;
import com.cloud.study.springboottkmybatis.mapper.AccountMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account queryOne(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(Account account) {
        int insert = accountMapper.insert(account);
    }

    @Autowired
    AccountMapper2 accountMapper2;
    @Override
    public Account queryOne2(int id) {
        Account account = accountMapper2.selectByPrimaryKey(id);
        return account;
    }

    @Override
    public List query3(int id) {
        return accountMapper2.selectByBizId(id);
    }

    @Override
    public Account query4(int bizId, int uniqueId) {
        return accountMapper2.selectByBizIdAndUniqueId(bizId, uniqueId);
    }

    @Override
    public Account query5(int bizId, int version) {
        return accountMapper2.selectByBizIdAndVersion(bizId, version);
    }

    @Override
    public void insert2(Account account) {
        accountMapper2.insert(account);
    }

    @Override
    public void updateById(Account account) {
        accountMapper2.updateByPrimaryKey(account);
    }

    @Override
    public void deleteById(int id) {
        accountMapper2.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById2(Account account) {
        accountMapper2.updateByBizIdAndUniqueId(account);
    }

    @Override
    public void insert3(List<Account> account) {
        accountMapper2.insertBatch(account);
    }

    @Override
    public void deleteByIds(ArrayList<Object> list) {
        accountMapper2.deleteByBizIds(list);
    }

}
