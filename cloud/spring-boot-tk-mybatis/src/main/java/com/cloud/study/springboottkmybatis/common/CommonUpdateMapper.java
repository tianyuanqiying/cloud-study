package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface CommonUpdateMapper<T> {

    @SelectProvider(type = CommonUpdateProvider.class, method = "dynamicSQL")
    public void updateByPrimaryKey(T entity);

    @SelectProvider(type = CommonUpdateProvider.class, method = "dynamicSQL")
    public void updateByBizIdAndUniqueId(T entity);
}
