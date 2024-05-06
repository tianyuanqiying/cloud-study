package com.cloud.study.springsecurityjwtrestfullogin.service;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurityjwtrestfullogin.dao.PermissionMapper;
import com.cloud.study.springsecurityjwtrestfullogin.dao.UserMapper;
import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysPermission;
import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author user
 */
@Service
public class UserServiceInDB implements UserService, UserDetailsService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public SysUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public String getSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            return "null";
        }

        User principal = (User) authentication.getPrincipal();
        System.out.println(principal);

        String username = principal.getUsername();
        System.out.println(username);
        return username;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = getByUsername(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not exist");
        }
        List<GrantedAuthority> authorities = CollectionUtil.newArrayList();
        List<SysPermission> permissions = permissionMapper.selectByUserId(user.getId());
        permissions.forEach(permission -> {
            if (Objects.isNull(permission.getUrl())) {
                return;
            }
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.getUrl());
            authorities.add(simpleGrantedAuthority);
        });

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
