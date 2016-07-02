/*
 * acooly.cn Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by zhangpu 
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.controllerPackage};

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
<#list table.columnMetadatas as entity>
<#if entity.dataType == 10>
import ${nameScheme.enumPackage}.${entity.propertyName?cap_first};
</#if>
</#list>

import com.google.common.collect.Maps;

/**
 * ${table.comment} 管理控制器
 * 
 * @author Acooly Code Generator
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Controller
@RequestMapping(value = "${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}")
public class ${nameScheme.controllerClassName} extends AbstractJQueryEntityController<${nameScheme.domainClassName}, ${nameScheme.serviceClassName}> {

<#assign existOptions=false>
<#list table.columnMetadatas as entity>
<#if entity.options??>
	<#assign existOptions=true>
	<#if entity.dataType == 1 || entity.dataType == 4>
	private static Map<Integer, String> all${entity.propertyName?cap_first}s = Maps.newLinkedHashMap();
	static {
	<#list entity.options?keys as key>
		all${entity.propertyName?cap_first}s.put(${key}, "${entity.options[key]}");
	</#list>
	}
	</#if>
</#if>
</#list>

	@Autowired
	private ${nameScheme.serviceClassName} ${nameScheme.serviceClassName?uncap_first};

	
<#if existOptions>
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
	<#list table.columnMetadatas as entity>
	<#if entity.options??>
		<#if entity.dataType == 1 || entity.dataType == 4>
		model.put("all${entity.propertyName?cap_first}s", all${entity.propertyName?cap_first}s);
		<#else>
		model.put("all${entity.propertyName?cap_first}s", ${entity.propertyName?cap_first}.mapping());
		</#if>
	</#if>
	</#list>	
	}
</#if>

}
