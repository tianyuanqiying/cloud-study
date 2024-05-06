package com.cloud.study.springbootmybatis.common.temp;

import com.cloud.study.springbootmybatis.common.BaseCommonSelectServiceImpl;
import com.cloud.study.springbootmybatis.common.mapper.CommonMapper;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


public class CommonService<T> extends BaseCommonSelectServiceImpl implements ICommonService<T> {
    @Setter
    CommonMapper<T> commonMapper;

    @Override
    public List<T> selectByBizId(Serializable id) {
        return commonMapper.selectByBizId(id);
    }
}
