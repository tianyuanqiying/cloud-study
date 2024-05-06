package com.cloud.study.springsecurity.config;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurity.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class WebSecurityAnnotationConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserServiceInDB userServiceInDB;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                ArrayList<SimpleGrantedAuthority> permission = CollectionUtil.newArrayList(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("ROLE_ADMIN"));
//                return new User(username, passwordEncoder.encode("123"), AuthorityUtils.createAuthorityList("admin", "ROLE_ADMIN"));
//            }
//        });
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                List<SimpleGrantedAuthority> permission = CollectionUtil.newArrayList(new SimpleGrantedAuthority("admin"), new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(username, passwordEncoder.encode("123"), permission);
            }
        });
//        auth.inMemoryAuthentication()
//                .withUser("fox")
//                .password(passwordEncoder.encode("123456"))
//                .authorities("admin","ROLE_ADMIN").and()
//                .withUser("fox2")
//                .password(passwordEncoder.encode("123456"))
//                .authorities("admin","ROLE_USER");
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


        //非登录的URL需要认证
        http.authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
