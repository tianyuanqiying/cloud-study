package com.cloud.study.valid.base;

import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.base.Validator;
import com.cloud.study.valid.pojo.BaseDTO;
import com.cloud.study.valid.pojo.ValidationInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * 参数校验处理器、有正则、枚举字段等
 * @author user
 */
public interface IValidHandler {

    /**
     * @param baseDTO
     * @return
     */
    Boolean support(BaseDTO baseDTO);

    /**
     * @param baseDTO
     * @return
     */
    Boolean handle(BaseDTO baseDTO);

    /**
     * 类型匹配
     * @param clazz
     * @return
     */
    Boolean typeMatch(Class clazz);

    interface ICache<T> {
        /**
         * 根据key获取缓存
         * @param key
         * @return
         */
        T get(String key);
    }
}
