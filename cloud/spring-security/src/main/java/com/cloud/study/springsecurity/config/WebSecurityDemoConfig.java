package com.cloud.study.springsecurity.config;

import com.cloud.study.springsecurity.service.UserServiceInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author user
 */

//@Configuration
public class WebSecurityDemoConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceInMemory userServiceInMemory;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceInMemory);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许表单登录
        http.formLogin();

        //1. 所有请求都要认证,思考： 为什么登录请求不会被拦截？  答案：由于登录的Filter比认证的Filter先执行，因此请求不被拦截；
        http.authorizeRequests().anyRequest().authenticated();

        //2.放行请求；

        //关闭csrf
        http.csrf().disable();
    }
}
