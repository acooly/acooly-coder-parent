package ${names.controllerPackage};

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feinno.framework.common.web.AbstractEntityController;
import ${names.domainPackage}.${names.domainClassName};
import ${names.servicePackage}.${names.serviceClassName};
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "${configuration.pagePath}/${names.domainClassName?uncap_first}")
public class ${names.controllerClassName} extends AbstractEntityController<${names.domainClassName}, ${names.serviceClassName}> {
<#assign existOptions=false>
<#list table.columnMetadatas as entity>
<#if entity.options??>
	<#assign existOptions=true>
	private static Map<Integer, String> all${entity.propertyName?cap_first}s = Maps.newTreeMap();
	static {
	<#list entity.options?keys as key>
		all${entity.propertyName?cap_first}s.put(${key}, "${entity.options[key]}");
	</#list>
	}
</#if>
</#list>

	@SuppressWarnings("unused")
	@Autowired
	private ${names.serviceClassName} ${names.serviceClassName?uncap_first};

	
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
