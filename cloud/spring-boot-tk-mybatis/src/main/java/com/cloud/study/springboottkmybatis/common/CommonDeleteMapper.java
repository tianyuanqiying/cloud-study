package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface CommonDeleteMapper<T> {

    @SelectProvider(type = CommonDeleteProvider.class, method = "dynamicSQL")
    void deleteByPrimaryKey(Object id);

    @SelectProvider(type = CommonDeleteProvider.class, method = "dynamicSQL")
    void deleteByBizIds(@Param("bizIds") List<Object> bizIds);
}
