package com.cloud.study.common;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.cloud.study.dto.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class FeignFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userInfo = request.getHeader("user");
        if (userInfo != null && userInfo.length()!= 0) {
            UserKit.set(JSONUtil.toBean(new String(Base64.decode(userInfo)), UserInfo.class));
        }
        filterChain.doFilter(request, response);
    }
}
