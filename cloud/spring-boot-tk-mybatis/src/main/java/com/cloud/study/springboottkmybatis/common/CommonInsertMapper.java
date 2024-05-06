package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @author user
 */
@RegisterMapper
public interface CommonInsertMapper<T> {

    @SelectProvider(type = CommonInsertProvider.class, method = "dynamicSQL")
    Boolean insert(T entity);

    @SelectProvider(type = CommonInsertProvider.class, method = "dynamicSQL")
    Boolean insertBatch(List<T> entities);
}
