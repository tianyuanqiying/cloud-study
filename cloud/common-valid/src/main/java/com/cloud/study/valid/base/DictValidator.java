package com.cloud.study.valid.base;

/**
 * 正则校验
 * @author user
 */
public interface DictValidator extends Validator{
    /**
     * 数据字典JSON文件名；XXX /  XXX.json
     * @return
     */
    String getDictName();
}
