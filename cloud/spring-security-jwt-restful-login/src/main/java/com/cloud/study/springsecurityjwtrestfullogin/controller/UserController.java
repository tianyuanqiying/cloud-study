package com.cloud.study.springsecurityjwtrestfullogin.controller;

import com.cloud.study.springsecurityjwtrestfullogin.common.HttpStatusCommon;
import com.cloud.study.springsecurityjwtrestfullogin.common.RestResult;
import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysUser;
import com.cloud.study.springsecurityjwtrestfullogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/get")
    public RestResult get(@RequestParam("username") String username) {
        SysUser user = userService.getByUsername(username);

        RestResult<SysUser> restResult = new RestResult<>();
        restResult.setCode(HttpStatusCommon.OK);
        restResult.setData(user);
        return  restResult;

    }
}
