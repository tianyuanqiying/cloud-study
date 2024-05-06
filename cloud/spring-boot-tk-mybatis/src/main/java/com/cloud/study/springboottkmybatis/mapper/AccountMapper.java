package com.cloud.study.springboottkmybatis.mapper;


import com.cloud.study.springboottkmybatis.entity.Account;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
@Repository
public interface AccountMapper extends Mapper<Account>, MySqlMapper<Account> {
}
