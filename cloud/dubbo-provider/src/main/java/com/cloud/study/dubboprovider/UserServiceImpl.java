package com.cloud.study.dubboprovider;

import com.cloud.study.dto.UserInfo;
import com.cloud.study.dubbo.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public List<UserInfo> list() {
        ArrayList<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("user1", "12138"));
        list.add(new UserInfo("user2", "12139"));

        return list;
    }

    @Override
    public UserInfo getById(Integer id) {
        return new UserInfo("user1", "12138");
    }
}
