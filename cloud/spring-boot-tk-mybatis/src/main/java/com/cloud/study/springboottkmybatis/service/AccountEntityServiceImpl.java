package com.cloud.study.springboottkmybatis.service;

import com.cloud.study.springboottkmybatis.entity.Account;
import com.cloud.study.springboottkmybatis.mapper.AccountBaseMapper;
import com.cloud.study.springboottkmybatis.mapper.AccountMapper;
import io.mybatis.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountEntityServiceImpl extends AbstractService<Account, Integer ,AccountBaseMapper> implements AccountEntityService{

    @Override
    public List<Account> queryList() {
        return this.findAll();
    }
    @Override
    public List<Account> queryList2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);

        return this.findByFieldList(Account::getId, list);
    }
}
