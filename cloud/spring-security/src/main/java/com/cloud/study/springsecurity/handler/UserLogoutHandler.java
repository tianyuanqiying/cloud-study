package com.cloud.study.springsecurity.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author user
 */
public class UserLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //删除用户相关的一些临时数据；
        System.out.println("delete user info ... [" + JSONUtil.toJsonStr(authentication) + "]");
    }
}
