package com.cloud.study.springbootmybatis.common.temp;

import java.io.Serializable;
import java.util.List;

public interface ICommonService<T> {
    List<T> selectByBizId(Serializable id);
}
