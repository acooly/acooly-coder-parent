/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.controllerPackage};

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
<#if data.includeFile>
import javax.servlet.http.HttpServletResponse;
import com.acooly.module.ofile.OFileProperties;
import org.springframework.ui.Model;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
<#list table.columns as column>
<#if column.dataType.enum>
import ${column.dataType.javaDeclare};
</#if>
</#list>

import com.google.common.collect.Maps;

/**
 * ${table.comment} 管理控制器
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Controller
@RequestMapping(value = "${configuration.managePath}/${nameScheme.domainClassName?uncap_first}")
public class ${nameScheme.controllerClassName} extends AbstractJsonEntityController<${nameScheme.domainClassName}, ${nameScheme.serviceClassName}> {

<#assign existOptions=false>
<#list table.columns as entity>
<#if entity.options??>
	<#assign existOptions=true>
	<#if entity.dataType.number>
	private static Map<Integer, String> all${entity.propertyName?cap_first}s = Maps.newLinkedHashMap();
	static {
	<#list entity.options?keys as key>
		all${entity.propertyName?cap_first}s.put(${key}, "${entity.options[key]}");
	</#list>
	}
	</#if>
</#if>
</#list>

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ${nameScheme.serviceClassName} ${nameScheme.serviceClassName?uncap_first};

<#-- 如果有文件上传 -->
<#if data.includeFile>
    @Autowired
    private OFileProperties oFileProperties;
</#if>


<#if data.includeFile || table.moveFunc>
    @Override
    protected ${nameScheme.domainClassName} onSave(HttpServletRequest request, HttpServletResponse response, Model model, ${nameScheme.domainClassName} entity, boolean isCreate) throws Exception {
    <#if data.includeFile>
		// 上传文件：设置上传文件的根存储路径，相对路径绑定到对应的属性；
		UploadConfig uploadConfig = getUploadConfig();
        uploadConfig.setStorageRoot(oFileProperties.getStorageRoot());
		uploadConfig.setUseMemery(false);
		doUpload(request, entity);
	</#if>
	<#if table.moveFunc>
		// 设置新增数据默认的sortTime为当前时间戳（排序到顶部）
		if (isCreate) {
			entity.setSortTime(System.currentTimeMillis());
		}
	</#if>
        return super.onSave(request, response, model, entity, isCreate);
    }
</#if>

<#if table.moveFunc>
	/**
	* 实体实现了Sortable接口，启用了移动功能，则需要固定排序方式为按sortTime倒叙排序
	*/
	@Override
	protected Map<String, Boolean> getSortMap(HttpServletRequest request) {
		Map<String, Boolean> sortMap = Maps.newHashMap();
		sortMap.put("sortTime", false);
		return sortMap;
	}
</#if>


<#if existOptions>
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
	<#list table.columns as entity>
	<#if entity.options?? && entity.dataType.number>
		model.put("all${entity.propertyName?cap_first}s", all${entity.propertyName?cap_first}s);
	</#if>
	<#if entity.dataType.enum>
		model.put("all${entity.propertyName?cap_first}s", ${entity.dataType.javaName}.mapping());
	</#if>
	</#list>
    <#if data.includeFile>
        model.put("serverRoot", oFileProperties.getServerRoot());
    </#if>
	}
</#if>

}
