package com.cloud.study.valid.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonUtil {
    public final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readValue(InputStream inputStream, Class<T> mapClass) {
        try {
            T value = objectMapper.readValue(inputStream, mapClass);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
