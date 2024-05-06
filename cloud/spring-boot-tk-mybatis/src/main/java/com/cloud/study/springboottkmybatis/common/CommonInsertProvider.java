package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

public class CommonInsertProvider extends MapperTemplate{
    public CommonInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insert(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append(SqlHelper.insertColumns(entityClass, true, false, false));
        sqlBuilder.append(SqlHelper.insertValuesColumns(entityClass, true, false, false));

        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), mappedStatement);
        return sqlBuilder.toString();
    }

    public String insertBatch(MappedStatement mappedStatement) {
        Class<?> entityClass = this.getEntityClass(mappedStatement);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("<bind name=\"listNotEmptyCheck\" value=\"@tk.mybatis.mapper.util.OGNL@notEmptyCollectionCheck(list, '" + mappedStatement.getId() + " 方法参数为空')\"/>");
        sqlBuilder.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sqlBuilder.append(SqlHelper.insertColumns(entityClass, true, false, false));

        sqlBuilder.append(" VALUES ");

        sqlBuilder.append(" <foreach item=\"record\" index=\"index\"  separator=\",\" collection=\"list\">");
        Set<EntityColumn> entityColumns = EntityHelper.getColumns(entityClass);
        sqlBuilder.append(" ( ");
        entityColumns.stream().filter(column -> !column.isId() && column.isInsertable()).forEach(column -> {
            sqlBuilder.append(column.getColumnHolder("record")).append(",");
        });
        sqlBuilder.replace(sqlBuilder.lastIndexOf(","), sqlBuilder.lastIndexOf(",") + 1, "");
        sqlBuilder.append(" ) ");
        sqlBuilder.append("</foreach>");

        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), mappedStatement);
        return sqlBuilder.toString();
    }
}
