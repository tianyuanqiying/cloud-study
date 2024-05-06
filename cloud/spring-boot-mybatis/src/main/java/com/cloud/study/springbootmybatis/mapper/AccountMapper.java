package com.cloud.study.springbootmybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.study.springbootmybatis.common.mapper.BaseCommonMapper;
import com.cloud.study.springbootmybatis.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseCommonMapper<Account> {
}
