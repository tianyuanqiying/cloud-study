package com.cloud.study.springbootmybatis.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.study.springbootmybatis.common.mapper.BaseCommonMapper;
import com.cloud.study.springbootmybatis.common.mapper.CommonMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author user
 */

public class BaseCommonSelectServiceImpl<M extends BaseCommonMapper<T>, T> extends ServiceImpl<M, T> implements BaseCommonService<T>{

    @Override
    public List<T> selectByBizId(Serializable id) {
        return this.getBaseMapper().selectByBizId(id);
    }

    @Override
    public List<T> selectByBizIdAndUniqueId(Serializable id, Serializable uniqueId) {
        return this.getBaseMapper().selectByBizIdAndUniqueId(id, uniqueId);
    }

}
