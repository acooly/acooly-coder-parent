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

    @Override
    protected ${nameScheme.domainClassName} onSave(HttpServletRequest request, HttpServletResponse response, Model model, ${nameScheme.domainClassName} entity, boolean isCreate) throws Exception {
        // 设置上传文件的根存储路径
		UploadConfig uploadConfig = getUploadConfig();
        uploadConfig.setStorageRoot(oFileProperties.getStorageRoot());
		uploadConfig.setUseMemery(false);
		// 上传文件，相对路径绑定到对应的属性
        doUpload(request, entity);
        return super.onSave(request, response, model, entity, isCreate);
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
