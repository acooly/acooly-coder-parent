package com.acooly.module.coder.domain;

import org.apache.commons.lang3.StringUtils;

public enum JavaType {

	String("String"),

	Int("int"),

	Integer("Integer"),

	pLong("long"),

	Long("Long"),

	Date("Date"),

	pDouble("double"),

	Enum("Enum"),

	Object("Object");

	private String value;

	private JavaType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static JavaType of(String value) {
		for (JavaType t : values()) {
			if (StringUtils.equals(t.getValue(), value)) {
				return t;
			}
		}
		return null;
	}

}
