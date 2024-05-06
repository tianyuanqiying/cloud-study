package com.cloud.study.springsecurityoauth01.handle;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.study.springsecurityoauth01.common.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        R r = new R();
        r.put("code","403");
        r.put("reason", "user not login");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(r));
    }
}
