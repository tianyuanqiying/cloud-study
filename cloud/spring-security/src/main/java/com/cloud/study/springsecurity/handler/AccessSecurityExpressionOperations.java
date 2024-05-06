package com.cloud.study.springsecurity.handler;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author user
 */
@Component
public class AccessSecurityExpressionOperations implements ISecurityExpressionOperations {
    private static final List<String> permissions = CollectionUtil.newArrayList("admin");

    @Override
    public boolean hasPermission(HttpServletRequest httpServletRequest) {
        //权限判断方法

        //1. 从登录用户会话中拿出其的访问权限、
        //2. 权限比对；
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("user must be login");
        }
        AtomicBoolean hasPermission = new AtomicBoolean(false);
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        authorities.forEach(authority -> {
            permissions.forEach(permission -> {
                if (authority.getAuthority().equals(permission)) {
                    hasPermission.set(true);
                }
            });
        });
        return hasPermission.get();
    }
}
