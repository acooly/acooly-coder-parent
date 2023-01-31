package com.acooly.coder.domain;

import com.acooly.coder.enums.Func;
import com.acooly.coder.support.BitPermissions;
import com.acooly.coder.support.Demos;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 列元数据
 *
 * @author zhangpu
 * @date 2012年6月21日
 */
public class Column {

    /**
     * 字符类型
     */
    public static final int DATATYPE_STRING = 0;
    /**
     * 数字类型
     */
    public static final int DATATYPE_LONG = 1;
    /**
     * 日期时间类型
     */
    public static final int DATATYPE_DATE = 2;
    /**
     * 对象类型
     */
    public static final int DATATYPE_LOB = 3;
    public static final int DATATYPE_INT = 4;

    /**
     * 精确小数
     */
    public static final int BIG_DECIMAL = 7;

    /**
     * 枚举类型
     */
    public static final int DATATYPE_ENUM = 10;

    /**
     * 列名称
     */
    private String name;
    /**
     * 列类型
     */
    private ColumnDataType dataType;
    /**
     * 业务类型
     */
    private ColumnType columnType;

    /**
     * 业务类型别名
     */
    private ColumnAlias columnAlias;

    /**
     * 列长度
     */
    private int length;
    /**
     * 列小数长度
     */
    private int scale = 0;
    /**
     * 是否可以为空
     */
    private boolean nullable;
    /**
     * 默认值
     */
    private Object defaultValue;
    /**
     * 列备注(label)
     */
    private String common;

    /**
     * 列提示
     */
    private String tip;

    /**
     * 常用数字分类字段可选值
     */
    private Map<String, String> options;

    private String validations;

    private ColumnComment columnComment;

    public String getPropertyName() {

        String segment[] = name.toLowerCase().split("_");
        String propertyName = segment[0];
        if (segment.length > 1) {
            for (int i = 1; i < segment.length; i++) {
                propertyName += StringUtils.capitalize(segment[i]);
            }
        }
        return propertyName;
    }

    /**
     * 大驼峰命名方式的属性名
     *
     * @return
     */
    public String getPascalName() {
        return StringUtils.capitalize(getPropertyName());
    }


    public Long getMax() {
        return Double.valueOf(Math.pow(10d, getLength())).longValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnDataType getDataType() {
        return dataType;
    }

    public void setDataType(ColumnDataType dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getValidations() {
        return validations;
    }

    public void setValidations(String validations) {
        this.validations = validations;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void appendOption(String key, String value) {
        synchronized (this.options) {
            if (this.options == null) {
                this.options = new HashMap<String, String>();
            }
        }
        this.options.put(key, value);
    }

    public ColumnComment getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(ColumnComment columnComment) {
        this.columnComment = columnComment;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public ColumnAlias getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(ColumnAlias columnAlias) {
        this.columnAlias = columnAlias;
    }


    public String getDemo() {
        return Demos.demo(this);
    }

    public String getOptionValueDemo() {
        return Demos.firstEnumValue(this);
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


    public boolean isSearch() {
        return BitPermissions.hasPerm(this.columnComment.getFunc(), Func.Search.getBit());
    }

    public boolean isList() {
        return BitPermissions.hasPerm(this.columnComment.getFunc(), Func.List.getBit());
    }

    public boolean isSave() {
        return BitPermissions.hasPerm(this.columnComment.getFunc(), Func.Save.getBit());
    }

    public boolean isShow() {
        return BitPermissions.hasPerm(this.columnComment.getFunc(), Func.Show.getBit());
    }

    public boolean isExport() {
        return BitPermissions.hasPerm(this.columnComment.getFunc(), Func.Export.getBit());
    }
}
