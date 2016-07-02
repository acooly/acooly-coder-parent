/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月2日
 *
 */
package com.acooly.module.coder.support;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangpu
 */
public class GenerateUtils {

	public static String getCanonicalClassName(String name) {
		return StringUtils.capitalize(name);
	}

	public static String getCanonicalClassFileName(String name) {
		return StringUtils.capitalize(name) + ".java";
	}

}
