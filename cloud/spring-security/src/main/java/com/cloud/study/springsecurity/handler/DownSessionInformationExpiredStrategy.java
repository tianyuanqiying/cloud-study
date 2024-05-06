package com.cloud.study.springsecurity.handler;

import cn.hutool.json.JSONObject;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

public class DownSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("reason", "被其他挤下线");
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(jsonObject.toString());
    }
}
