package com.cloud.study.springsecurity.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    private String forwardUrl;

    public UserLogoutSuccessHandler() {
    }

    public UserLogoutSuccessHandler(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //清理用户数据；
        System.out.println("user [" + JSONUtil.toJsonStr(authentication)+ "] logout success" );
        request.getRequestDispatcher(forwardUrl).forward(request,response);
    }
}
