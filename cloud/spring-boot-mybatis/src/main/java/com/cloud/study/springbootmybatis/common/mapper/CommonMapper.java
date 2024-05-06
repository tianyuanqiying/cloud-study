package com.cloud.study.springbootmybatis.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;


public interface CommonMapper<T> {
    List<T> selectByBizId(Serializable id);
    List<T> selectByBizIdAndUniqueId(@Param("bizId") Serializable id, @Param("uniqueId") Serializable uniqueId);
}
