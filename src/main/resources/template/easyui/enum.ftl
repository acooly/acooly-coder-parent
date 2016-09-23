/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.enumPackage};

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

<#assign enumName=data.enumColumn.propertyName?cap_first>
/**
 * ${table.comment} ${enumName} 枚举定义
 * 
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
public enum ${nameScheme.domainClassName}${enumName} implements Messageable {

	<#list data.enumColumn.options?keys as key>
		${key}("${key}", "${data.enumColumn.options[key]}"),
	</#list>
	;

	private final String code;
	private final String message;

	private ${enumName}(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}

	public static Map<String, String> mapping() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (${enumName} type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

	/**
	 * 通过枚举值码查找枚举值。
	 * 
	 * @param code
	 *            查找枚举值的枚举值码。
	 * @return 枚举值码对应的枚举值。
	 * @throws IllegalArgumentException
	 *             如果 code 没有对应的 Status 。
	 */
	public static ${enumName} find(String code) {
		for (${enumName} status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("${enumName} not legal:" + code);
	}

	/**
	 * 获取全部枚举值。
	 * 
	 * @return 全部枚举值。
	 */
	public static List<${enumName}> getAll() {
		List<${enumName}> list = new ArrayList<${enumName}>();
		for (${enumName} status : values()) {
			list.add(status);
		}
		return list;
	}

	/**
	 * 获取全部枚举值码。
	 * 
	 * @return 全部枚举值码。
	 */
	public static List<String> getAllCode() {
		List<String> list = new ArrayList<String>();
		for (${enumName} status : values()) {
			list.add(status.code());
		}
		return list;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", this.code, this.message);
	}

}
