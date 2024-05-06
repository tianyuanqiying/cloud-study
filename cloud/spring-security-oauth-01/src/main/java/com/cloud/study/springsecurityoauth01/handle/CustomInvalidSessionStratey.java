package com.cloud.study.springsecurityoauth01.handle;

import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class CustomInvalidSessionStratey implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        //侦测到会话不合法、过期则跳转到登录页面；
        httpServletResponse.sendRedirect("/login");
    }
}
