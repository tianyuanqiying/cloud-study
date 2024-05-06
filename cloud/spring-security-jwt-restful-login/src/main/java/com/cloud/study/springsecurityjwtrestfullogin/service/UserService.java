package com.cloud.study.springsecurityjwtrestfullogin.service;
import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysUser;

public interface UserService {
    SysUser getByUsername(String username);

    String getSession();
}
