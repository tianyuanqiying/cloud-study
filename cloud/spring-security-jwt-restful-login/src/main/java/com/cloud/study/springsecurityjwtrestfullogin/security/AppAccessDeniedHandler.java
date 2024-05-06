package com.cloud.study.springsecurityjwtrestfullogin.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.study.springsecurityjwtrestfullogin.common.HttpStatusCommon;
import com.cloud.study.springsecurityjwtrestfullogin.common.RestResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class AppAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        RestResult restResult = new RestResult();
        restResult.setCode(HttpStatusCommon.NOT_PERMISSION);
        restResult.setData(accessDeniedException.getMessage());
        restResult.setMessage(accessDeniedException.getMessage());
        response.getWriter().write(JSONUtil.toJsonStr(restResult));
    }
}
