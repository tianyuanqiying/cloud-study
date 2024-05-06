package com.cloud.study.seata_multipart_source.config;

import lombok.Getter;

/**
 * @author Fox
 */
@Getter
public enum DataSourceKey {
    /**
     * Order data source key.
     */
    ORDER,
    /**
     * Storage data source key.
     */
    STORAGE,
    /**
     * Account data source key.
     */
    ACCOUNT,
}