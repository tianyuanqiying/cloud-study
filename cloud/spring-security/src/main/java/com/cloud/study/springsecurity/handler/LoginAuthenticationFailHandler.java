package com.cloud.study.springsecurity.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class LoginAuthenticationFailHandler implements AuthenticationFailureHandler {
    private String forwardUrl;

    public LoginAuthenticationFailHandler() {
    }

    public LoginAuthenticationFailHandler(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.getRequestDispatcher(forwardUrl).forward(request, response);
    }
}
