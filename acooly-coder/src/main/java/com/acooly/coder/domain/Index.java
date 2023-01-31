/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-01-29 23:44
 */
package com.acooly.coder.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * 唯一索引
 *
 * @author zhangpu
 * @date 2023-01-29 23:44
 */
public class Index {

    /**
     * 索引名称
     */
    private String name;

    /**
     * 索引列
     */
    private List<Column> columns = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    /**
     * 生成DAO的SQL部分
     *
     * @return
     */
    public String getDaoSqlWhere() {
        StringBuilder sb = new StringBuilder();
        // 循环生成驼峰方法名称
        int i = 0;
        for (Column column : columns) {
            if (i > 0) {
                sb.append(" AND ");
            }
            sb.append(column.getName()).append("=#{").append(column.getPropertyName()).append("}");
            i = i + 1;
        }
        return sb.toString();
    }

    /**
     * 生成DAO的方法主体部分
     * <p>
     * uniqueBy`UsernameAndKey(@Param("username") String username,@Param("key") Integer key)`;
     *
     * @return
     */
    public String getDaoMethodDeclare() {
        return doMethodDeclare(true);
    }


    public String getServiceMethodDeclare() {
        return doMethodDeclare(false);
    }

    public String getDaoMethodCall(){
        StringBuilder sb = new StringBuilder();
        // 循环生成驼峰方法名称
        int i = 0;
        for (Column column : columns) {
            if (i > 0) {
                sb.append("And");
            }
            sb.append(column.getPascalName());
            i = i + 1;
        }
        // 循环生成方法调用
        i = 0;
        sb.append("(");
        for (Column column : columns) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(column.getPropertyName());
            i = i + 1;
        }
        sb.append(")");
        return sb.toString();
    }

    public String doMethodDeclare(boolean dao) {
        StringBuilder sb = new StringBuilder();
        // 循环生成驼峰方法名称
        int i = 0;
        for (Column column : columns) {
            if (i > 0) {
                sb.append("And");
            }
            sb.append(column.getPascalName());
            i = i + 1;
        }
        // 循环生成方法参数声明
        i = 0;
        sb.append("(");
        for (Column column : columns) {
            if (i > 0) {
                sb.append(", ");
            }
            if (dao) {
                sb.append("@Param(\"").append(column.getPropertyName()).append("\") ");
            }
            sb.append(column.getDataType().getJavaType() == JavaType.Enum ? column.getDataType().getJavaName() : column.getDataType().getJavaType().name()).append(" ").append(column.getPropertyName());
            i = i + 1;
        }
        sb.append(")");
        return sb.toString();
    }


}
