package com.cloud.study.springsecurity.config;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurity.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author user
 */
//@Configuration
public class WebSecurityAuthorityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceInDB userServiceInDB;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                ArrayList<SimpleGrantedAuthority> permission = CollectionUtil.newArrayList(new SimpleGrantedAuthority("admin"));
                return new User(username, passwordEncoder.encode("123"), permission);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面、登录成功，登录失败； /user/index必须是POST请求
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login")
                .successForwardUrl("/user/index") //重定向到index.html, 因此登录成功，无需放行
                .failureForwardUrl("/user/toerror"); //重定向到error.html, 因此登录失败，需要放行/error.html，否则因为没有权限，再次调到登录页面

        //登录的URL放行
//        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html").permitAll();

        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html").permitAll();
        //如果用户具备给某个权限admin，就允许访问。
        http.authorizeRequests().antMatchers("/Authority/haveAuthority").hasAuthority("admin");
        //如果用户具备给某个权限user，就允许访问。
        http.authorizeRequests().antMatchers("/Authority/notAuthority").hasAuthority("user");
        //如果用户具备给定权限中某一个，就允许访问。
        http.authorizeRequests().antMatchers("/Authority/hasAnyAuthority").hasAnyAuthority("user","admin");
        //非登录的URL需要认证
        http.authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
    }
}
