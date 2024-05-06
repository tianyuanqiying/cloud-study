package com.cloud.study.springsecurity.config;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurity.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author user
 */
//@Configuration
public class WebSecurityUserServiceConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                ArrayList<SimpleGrantedAuthority> admin = CollectionUtil.newArrayList(new SimpleGrantedAuthority("admin"));
                return new UserInfo(username, passwordEncoder.encode("123"), admin);
            }
        });
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


        //关闭csrf
        http.csrf().disable();
    }
}
