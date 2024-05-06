package com.cloud.study.springsecurity.config;

import com.cloud.study.springsecurity.handler.LoginAuthenticationFailHandler;
import com.cloud.study.springsecurity.handler.LoginAuthenticationSuccessHandler;
import com.cloud.study.springsecurity.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author user
 */
//@Configuration
public class WebSecurityLoginHandlerConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserServiceInDB userServiceInDB;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceInDB);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面、登录成功，登录失败； /user/index必须是POST请求
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .successHandler(new LoginAuthenticationSuccessHandler("/user/index"))
                .failureHandler(new LoginAuthenticationFailHandler("/user/toerror"));
//                .successForwardUrl("/user/index") //重定向到index.html, 因此登录成功，无需放行
//                .failureForwardUrl("/user/toerror"); //重定向到error.html, 因此登录失败，需要放行/error.html，否则因为没有权限，再次调到登录页面

        //登录的URL放行
//        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html").permitAll();

        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html").permitAll();
        //非登录的URL需要认证
        http.authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
    }
}
