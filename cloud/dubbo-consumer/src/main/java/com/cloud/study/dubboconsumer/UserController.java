package com.cloud.study.dubboconsumer;

import com.cloud.study.common.R;
import com.cloud.study.dto.UserInfo;
import com.cloud.study.dubbo.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author user
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @DubboReference
    UserService userService;

    @GetMapping("/list")
    public R list() {
        System.out.println("list");
        R r = new R();
        List<UserInfo> infos = userService.list();
        r.put("users", infos);
        return r;
    }

    @GetMapping("getById")
    public R getById(Integer id) {
        System.out.println("getById");
        R r = new R();
        UserInfo userInfo = userService.getById(id);
        r.put("user", userInfo);
        return r;
    }
}
