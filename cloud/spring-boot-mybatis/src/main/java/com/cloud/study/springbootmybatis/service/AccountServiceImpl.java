package com.cloud.study.springbootmybatis.service;

import com.cloud.study.springbootmybatis.entity.Account;
import com.cloud.study.springbootmybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Account> selectByBizId(Integer id) {

        return null;
    }

    @Override
    public List<Account> selectByBizId2(Integer id) {
        return accountMapper.selectByBizId(id);
    }

    @Override
    public List<Account> selectByBizId3(Integer id) {
        return accountMapper.selectByBizId(id);
    }

    @Override
    public List<Account> selectByBizIdAndUniqueId(Integer id, Integer uniqueId) {
        return accountMapper.selectByBizIdAndUniqueId(id, uniqueId);
    }


//
//    @Autowired
//    CommonMapper<Account> commonMapper;
//
//    @Override
//    public List<Account> selectByBizId2(Long id) {
//        ArrayList<Long> arrayList = new ArrayList<>();
//        arrayList.add(id);
//        return commonMapper.selectByBizId(id);
//    }
//
//    @Autowired
//    ICommonService iCommonService;
//
//    @Override
//    public List<Account> selectByBizId3(Long id) {
//        ArrayList<Long> arrayList = new ArrayList<>();
//        arrayList.add(id);
//        return commonMapper.selectByBizId(id);
//    }
}
