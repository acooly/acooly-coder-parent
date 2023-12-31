package com.acooly.coder.domain;

import org.apache.commons.lang3.StringUtils;

public class ColumnDataType {

    /**
     * 数据库类型
     */
    private String databaseType;
    /**
     * java类型
     */
    private JavaType javaType;
    /**
     * java声明（带Package）
     */
    private String javaDeclare;
    /**
     * java类型名
     */
    private String javaName;


    public ColumnDataType() {
        super();
    }

    public ColumnDataType(String databaseType, JavaType javaType) {
        super();
        this.databaseType = databaseType;
        this.javaType = javaType;
    }

    public ColumnDataType(String databaseType, JavaType javaType, String javaDeclare) {
        super();
        this.databaseType = databaseType;
        this.javaType = javaType;
        this.javaDeclare = javaDeclare;
    }

    public ColumnDataType(String databaseType, JavaType javaType, String javaDeclare, String javaName) {
        this.databaseType = databaseType;
        this.javaType = javaType;
        this.javaDeclare = javaDeclare;
        this.javaName = javaName;
    }

    public boolean isBigObject() {
        return StringUtils.containsIgnoreCase(this.databaseType, "text") ||
                StringUtils.containsIgnoreCase(this.databaseType, "blob");
    }

    public boolean isString() {
        return javaType == JavaType.String;
    }

    public boolean isNumber() {
        return javaType == JavaType.Int || javaType == JavaType.Integer || javaType == JavaType.Long
                || javaType == JavaType.pLong || javaType == JavaType.Money;
    }

    public boolean isInteger() {
        return javaType == JavaType.Int || javaType == JavaType.Integer;
    }

    public boolean isLong() {
        return javaType == JavaType.Long || javaType == JavaType.pLong;
    }

    public boolean isEnum() {
        return javaType == JavaType.Enum;
    }

    public boolean isObject() {
        return javaType == JavaType.Enum || javaType == JavaType.Object;
    }

    public boolean isDate() {
        return javaType == JavaType.Date;
    }

    public boolean isDateTime() {
        return javaType == JavaType.DateTime;
    }

    public boolean isDouble() {
        return javaType == JavaType.pDouble;
    }

    public String getJavaTypeName() {
        if (isObject()) {
            return StringUtils.substringAfterLast(this.javaDeclare, ".");
        } else {
            return javaType.getValue();
        }

    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public JavaType getJavaType() {
        return javaType;
    }

    public void setJavaType(JavaType javaType) {
        this.javaType = javaType;
    }

    public String getJavaDeclare() {
        return javaDeclare;
    }

    public void setJavaDeclare(String javaDeclare) {
        this.javaDeclare = javaDeclare;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }
}
