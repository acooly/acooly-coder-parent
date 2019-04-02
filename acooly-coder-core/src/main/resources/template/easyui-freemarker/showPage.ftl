<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
<div style="padding: 5px;font-family:微软雅黑;">
<table class="tableForm" width="100%">
<#list table.columns as entity>
	<tr>
		<th<#if entity_index == 1> width="25%"</#if>>${entity.common}:</th>
	<#if entity.dataType.date>
		<td>${r"${"}${entityVariable}.${entity.propertyName}?string('yyyy-MM-dd HH:mm:ss')}</td>
	<#elseif entity.dataType.enum>
		<td>${r"${"}${entityVariable}.${entity.propertyName}.message()}</td>
	<#elseif entity.options??>
		<td>${r"${"}all${entity.propertyName?cap_first}s?api.get(${entityVariable}.${entity.propertyName})}</td>
	<#else>
		<td>${r"${"}${entityVariable}.${entity.propertyName}}</td>
	</#if>
	</tr>					
</#list>        
</table>
</div>
