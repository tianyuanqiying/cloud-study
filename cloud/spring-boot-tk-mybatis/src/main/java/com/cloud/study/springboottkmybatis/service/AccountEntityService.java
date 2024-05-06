package com.cloud.study.springboottkmybatis.service;

import com.cloud.study.springboottkmybatis.entity.Account;
import io.mybatis.service.EntityService;

import java.util.List;

/**
 * @author user
 */
public interface AccountEntityService extends EntityService<Account, Integer> {
    public List<Account> queryList();

    List<Account> queryList2();
}
