package com.cloud.study.springsecurityjwtrestfullogin.controller;

import com.cloud.study.springsecurityjwtrestfullogin.common.AuthConfigs;
import com.cloud.study.springsecurityjwtrestfullogin.common.HttpHeadersCommon;
import com.cloud.study.springsecurityjwtrestfullogin.common.RestResult;
import com.cloud.study.springsecurityjwtrestfullogin.security.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.config.AuthConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author user
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @PostMapping("/login")
    public RestResult login(@RequestParam String username, @RequestParam String password, HttpServletResponse response,
                            HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //认证成功、将会话放入SecurityContextHiolder;
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //根据username生成token：
        String token = jwtTokenManager.createToken(username);
        String authorization = "bearer " + token;
        response.setHeader(HttpHeadersCommon.AUTHORIZATION_HEADER_NAME, authorization);
        RestResult<Object> restResult = new RestResult<>();
        restResult.setCode(HttpStatus.OK.value());
        restResult.setData(authorization);
        return restResult;
    }
}
