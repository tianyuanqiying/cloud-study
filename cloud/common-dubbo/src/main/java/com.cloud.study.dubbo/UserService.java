package com.cloud.study.dubbo;

import com.cloud.study.dto.UserInfo;

import java.util.List;

public interface UserService {
    public List<UserInfo> list();
    public UserInfo getById(Integer id);
}
