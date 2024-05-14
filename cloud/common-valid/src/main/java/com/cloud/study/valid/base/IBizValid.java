package com.cloud.study.valid.base;

import java.util.Map;

/**
 * 业务验证
 * @author user
 */
public interface IBizValid {
    /**
     * 业务验证接口
     * @param errorInfo
     */
    void businessValidate(Map<String, Object> errorInfo);
}
