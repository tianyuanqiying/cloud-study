package com.cloud.study.springsecurityjwtrestfullogin.security;

import cn.hutool.core.collection.CollectionUtil;
import com.cloud.study.springsecurityjwtrestfullogin.dao.PermissionMapper;
import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public boolean hasPermission(HttpServletRequest request) throws AuthenticationException {
        //常用权限判断方法
        //1. 从登录用户会话中拿出用户角色、根据用户角色查询角色权限表拿到url
        //2. 权限比对；

        //spring security 判断方法
        //1. 登录时、把用户能查看的url放入会话的权限集合；
        //2. 访问接口时、 拿到请求的url， 判断是否包含 用户能查看的url, 不能则抛异常
        String requestURI = request.getRequestURI();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new AccessDeniedException("user must be login");
        }
        if (!(authentication.getPrincipal() instanceof UserDetails)) {
            throw new AccessDeniedException("user must be login");
        }

        UserDetails principal = (UserDetails)authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            throw new AccessDeniedException("user must be login");
        }

        List<SysPermission> permissions = permissionMapper.getByUserName(principal.getUsername());

        AtomicBoolean hasPermission = new AtomicBoolean(false);
        permissions.forEach(authority -> {
            if (requestURI.equals(authority.getUrl())) {
                hasPermission.set(true);
            }
        });
        return hasPermission.get();
    }
}
