<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}" />
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
<#list table.columns as entity>
	<tr>
		<th<#if entity_index == 1> width="25%"</#if>>${entity.common}:</th>
	<#if entity.dataType.date>
		<td><fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<#elseif entity.dataType.enum>
		<td>${r"${"}${entityVariable}.${entity.propertyName}.message}</td>
	<#elseif entity.options??>
		<td>${r"${"}all${entity.propertyName?cap_first}s[${entityVariable}.${entity.propertyName}]}</td>
	<#else>
		<td>${r"${"}${entityVariable}.${entity.propertyName}}</td>
	</#if>
	</tr>					
</#list>        
</table>
</div>
