package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.List;

public class CommonSelectProvider<T> extends MapperTemplate {
    String TABLE_NAME = "${TABLE}";
    String PRIMARY_KEY = "${PRIMARY_KEY}";
    String SELECT_BY_BIZID = "SELECT * FROM " + TABLE_NAME + " WHERE BIZ_ID = #{BIZ_ID}";
    String SELECT_BY_BIZID_AND_UNIQUEID = "SELECT * FROM " + TABLE_NAME + " WHERE BIZ_ID = #{bizId} AND UNIQUE_ID = #{uniqueId}";
    String SELECT_BY_BIZID_AND_VERSION = "SELECT * FROM " + TABLE_NAME + " WHERE BIZ_ID = #{bizId} AND VERSION = #{version}";
    String SELECT_BY_PRIMARY_KEY = "SELECT * FROM " + TABLE_NAME + " " + PRIMARY_KEY;
    public CommonSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectByBizId(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        this.setResultType(mappedStatement, entityClass);
        String sql = SELECT_BY_BIZID.replace(TABLE_NAME, this.tableName(entityClass));
        return sql;
    }

    public String selectByBizIdAndUniqueId(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        this.setResultType(mappedStatement, entityClass);
        String sql = SELECT_BY_BIZID_AND_UNIQUEID.replace(TABLE_NAME, this.tableName(entityClass));
        return sql;
    }

    public String selectByBizIdAndVersion(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        this.setResultType(mappedStatement, entityClass);
        String sql = SELECT_BY_BIZID_AND_VERSION.replace(TABLE_NAME, this.tableName(entityClass));
        return sql;
    }

    public String selectByPrimaryKey(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        this.setResultType(mappedStatement, entityClass);
        String sql = SELECT_BY_PRIMARY_KEY.replace(TABLE_NAME, this.tableName(entityClass)).replace(PRIMARY_KEY, SqlHelper.wherePKColumns(entityClass));
        return sql;
    }
}
