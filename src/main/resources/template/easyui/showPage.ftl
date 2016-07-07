<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}" />
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
<#list table.columns as entity>
	<#if entity.name?lower_case != 'id'>
	<tr>
		<th<#if entity_index == 1> width="25%"</#if>>${entity.common}:</th>
	<#if entity.dataType.date>
		<td><fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<#elseif entity.dataType.number>
		<#if entity.options??>
		<td>${r"${"}all${entity.propertyName?cap_first}s[${entityVariable}.${entity.propertyName}]}</td>
		<#else>
		<td>${r"${"}${entityVariable}.${entity.propertyName}}</td>
		</#if>
	<#else>
		<td>${r"${"}${entityVariable}.${entity.propertyName}}</td>
	</#if>
	</tr>					
	</#if>
</#list>        
</table>
</div>
