package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class CommonDeleteProvider extends MapperTemplate {
    public CommonDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String deleteByPrimaryKey(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SqlHelper.deleteFromTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append(SqlHelper.wherePKColumns(entityClass));
        return sqlBuilder.toString();
    }

    public String deleteByBizIds(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SqlHelper.deleteFromTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append("WHERE BIZ_ID in ");
        sqlBuilder.append("<foreach item=\"bizId\" index=\"index\" collection=\"bizIds\" open=\"(\" separator=\",\" close=\")\">");
        sqlBuilder.append("#{bizId}");
        sqlBuilder.append("</foreach>");
        return sqlBuilder.toString();
    }
}
