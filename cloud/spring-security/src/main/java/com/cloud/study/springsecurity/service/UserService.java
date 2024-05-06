package com.cloud.study.springsecurity.service;

import com.cloud.study.springsecurity.pojo.SysUser;

public interface UserService {
    SysUser getByUsername(String username);

    String getSession();
}
