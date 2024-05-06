package com.cloud.study.springboottkmybatis.common;

import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface CommonMapper<T> extends CommonSelectMapper<T>, CommonInsertMapper<T>, CommonUpdateMapper<T>, CommonDeleteMapper<T> {
}
