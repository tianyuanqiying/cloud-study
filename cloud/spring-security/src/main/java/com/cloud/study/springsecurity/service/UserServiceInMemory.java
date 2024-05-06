package com.cloud.study.springsecurity.service;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurity.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceInMemory implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<SimpleGrantedAuthority> admin = CollectionUtil.newArrayList(new SimpleGrantedAuthority("admin"));
        return new UserInfo(username, passwordEncoder.encode("123"), admin);
    }
}
