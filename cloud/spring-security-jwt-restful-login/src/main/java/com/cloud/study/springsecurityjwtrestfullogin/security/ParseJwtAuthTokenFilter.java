package com.cloud.study.springsecurityjwtrestfullogin.security;

import com.cloud.study.springsecurityjwtrestfullogin.common.HttpHeadersCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author user
 */
public class ParseJwtAuthTokenFilter extends GenericFilterBean {
    JwtTokenManager jwtTokenManager;

    public ParseJwtAuthTokenFilter() {
    }

    public ParseJwtAuthTokenFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String bearerToken = req.getHeader(HttpHeadersCommon.AUTHORIZATION_HEADER_NAME);

        //Authorization请求头， 拿不到则执行下个过滤器；
        if (Objects.isNull(bearerToken)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = bearerToken.substring("bearer ".length());

        //解析JWT的Token
        Authentication authentication = jwtTokenManager.getAuthentication(jwtToken);

        //设置会话信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
