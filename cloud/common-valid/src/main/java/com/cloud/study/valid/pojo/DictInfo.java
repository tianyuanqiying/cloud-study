package com.cloud.study.valid.pojo;

import java.util.Map;

/**
 * 枚举信息类
 * @author user
 */
public class DictInfo implements BaseDTO{
    private Map<String, Object> data;
    private String dictName;
    private String dictFilePath;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictFilePath() {
        return dictFilePath;
    }

    public void setDictFilePath(String dictFilePath) {
        this.dictFilePath = dictFilePath;
    }
}
