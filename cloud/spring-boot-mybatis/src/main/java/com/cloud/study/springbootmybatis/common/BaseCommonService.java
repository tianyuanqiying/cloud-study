package com.cloud.study.springbootmybatis.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;


public interface BaseCommonService<T> extends IService<T> {
    List<T> selectByBizId(Serializable id);
    List<T> selectByBizIdAndUniqueId(Serializable id, Serializable uniqueId);
}
