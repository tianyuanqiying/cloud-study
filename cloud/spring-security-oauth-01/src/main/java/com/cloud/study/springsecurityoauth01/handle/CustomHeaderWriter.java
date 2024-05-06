package com.cloud.study.springsecurityoauth01.handle;


import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author user
 */
public class CustomHeaderWriter extends StaticHeadersWriter {
    public CustomHeaderWriter() {
        super("X-Content-Type-Options", "nosniff");
    }

    public CustomHeaderWriter(List<Header> headers) {
        super(headers);
    }
}
