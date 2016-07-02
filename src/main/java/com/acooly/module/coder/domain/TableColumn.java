package com.acooly.module.coder.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.support.GenerateUtils;

/**
 * 列元数据
 * 
 * @author zhangpu
 * @date 2012年6月21日
 */
public class TableColumn {

	/** 字符类型 */
	public static final int DATATYPE_STRING = 0;
	/** 数字类型 */
	public static final int DATATYPE_LONG = 1;
	/** 日期时间类型 */
	public static final int DATATYPE_DATE = 2;
	/** 对象类型 */
	public static final int DATATYPE_LOB = 3;
	public static final int DATATYPE_INT = 4;
	/** 枚举类型 */
	public static final int DATATYPE_ENUM = 10;

	/** 列名称 */
	private String name;
	/** 列类型 */
	private int dataType;

	private String javaType;

	/** 列精度 */
	private int length;
	/** 是否可以为空 */
	private boolean nullable;
	/** 默认值 */
	private Object defaultValue;
	/** 列备注 */
	private String common;
	/** 常用数字分类字段可选值 */
	private Map<String, String> options;

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

	public String getJavaDataType() {
		switch (dataType) {
		case DATATYPE_STRING:
			return "String";
		case DATATYPE_LONG:
			if (getLength() > 4) {
				return "Long";
			} else {
				return "int";
			}
		case DATATYPE_INT:
			return "int";
		case DATATYPE_DATE:
			return "Date";
		case DATATYPE_LOB:
			return "String";
		case DATATYPE_ENUM:
			return GenerateUtils.getCanonicalClassName(this.name);
		default:
			return "String";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
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

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public void appendOption(String key, String value) {
		synchronized (this.options) {
			if (this.options == null) {
				this.options = new HashMap<String, String>();
			}
		}
		this.options.put(key, value);
	}

}
