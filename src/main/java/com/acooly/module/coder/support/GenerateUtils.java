/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月2日
 *
 */
package com.acooly.module.coder.support;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangpu
 */
public class GenerateUtils {

	protected static Logger logger = Logger.getLogger(GenerateUtils.class.getSimpleName());

	public static String getCanonicalClassName(String name) {
		return StringUtils.capitalize(name);
	}

	public static String getCanonicalClassFileName(String name) {
		return StringUtils.capitalize(name) + ".java";
	}

	public static Map<String, String> parseLikeJson(String comment) {
		try {
			String json = null;
			Matcher m = Pattern.compile("\\{.+\\}").matcher(comment);
			if (m.find()) {
				json = m.group();
			}
			if (StringUtils.isBlank(json)) {
				return null;
			}
			Map<String, String> data = new LinkedHashMap<String, String>();
			json = StringUtils.substring(json, 1, json.length() - 1);
			for (String item : StringUtils.split(json, ",")) {
				String[] fields = StringUtils.split(item, ":");
				data.put(fields[0], fields[1]);
			}
			return data;
		} catch (Exception e) {
			logger.warning("parse property comment to options Map fail. " + comment + "e: " + e.getMessage());
			return null;
		}
	}

}
