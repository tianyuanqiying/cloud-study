package com.cloud.study.valid.builder;

import com.cloud.study.valid.base.Constant;
import com.cloud.study.valid.pojo.DictInfo;
import com.cloud.study.valid.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举信息类
 *
 * @author user
 */
public class DictInfoBuilder {
    private Map<String, Object> dictData;
    private String dictName;
    private String dictFilePath;

    public DictInfoBuilder(String dictName) {
        this.dictName = dictName;
        this.dictFilePath = buildDictFilePath(dictName);
        this.init();
    }

    private DictInfoBuilder dictName(String dictName) {
        this.dictName = dictName;
        return this;
    }

    private DictInfoBuilder dictFilePath(String dictFilePath) {
        this.dictFilePath = dictFilePath;
        return this;
    }

    private String buildDictFilePath(String dictName) {
        StringBuilder dictFilePathBuilder = new StringBuilder();
        dictFilePathBuilder.append(Constant.DICT_DIRECTORY_PATH);
        dictFilePathBuilder.append(dictName);
        if (!dictName.endsWith(".json")) {
            dictFilePathBuilder.append(".json");
        }
        return dictFilePathBuilder.toString();
    }

    public DictInfoBuilder init() {
        InputStream inputStream = this.getClass().getResourceAsStream(this.dictFilePath);
        Map<String, Object> dictData = JsonUtil.readValue(inputStream, Map.class);
        this.dictData(dictData);
        return this;
    }

    private DictInfoBuilder dictData(Map<String, Object> dictData) {
        this.dictData = dictData;
        return this;
    }

    public DictInfo build() {
        DictInfo dictInfo = new DictInfo();
        dictInfo.setData(dictData);
        dictInfo.setDictName(dictName);
        dictInfo.setDictFilePath(dictFilePath);
        return dictInfo;
    }
}
