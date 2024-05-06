package com.cloud.study.springbootmybatis.service;

import com.cloud.study.springbootmybatis.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> selectByBizId(Integer id);
    List<Account> selectByBizId2(Integer id);
    List<Account> selectByBizId3(Integer id);

    List<Account> selectByBizIdAndUniqueId(Integer id, Integer uniqueId);
}
