package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class CommonUpdateProvider extends MapperTemplate{
    public CommonUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String updateByPrimaryKey(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append(SqlHelper.updateSetColumns(entityClass, null, false, false));
        sqlBuilder.append(SqlHelper.wherePKColumns(entityClass));

        return sqlBuilder.toString();
    }

    public String updateByBizIdAndUniqueId(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append(SqlHelper.updateSetColumns(entityClass, null, false, false));
        sqlBuilder.append(" WHERE BIZ_ID = #{bizId} AND UNIQUE_ID = #{uniqueId}");
        return sqlBuilder.toString();
    }
}
