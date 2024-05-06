package com.cloud.study.provider;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
class EchoController {
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @SneakyThrows
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(HttpServletResponse httpServletResponse) throws IOException {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write("1".getBytes());
        outputStream.flush();
    }
}