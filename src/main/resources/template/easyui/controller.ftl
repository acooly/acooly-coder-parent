package ${nameScheme.controllerPackage};

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
import com.google.common.collect.Maps;

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
	<#else>
	private static Map<String, String> all${entity.propertyName?cap_first}s = Maps.newLinkedHashMap();
	static {
	<#list entity.options?keys as key>
		all${entity.propertyName?cap_first}s.put("${key}", "${entity.options[key]}");
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
		model.put("all${entity.propertyName?cap_first}s", all${entity.propertyName?cap_first}s);
	</#if>
	</#list>	
	}
</#if>

}
