<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
<div class="card-body">
	<dl class="row">
<#list table.columns as entity>
		<dt class="col-sm-3">${entity.common}:</dt>
	<#if entity.dataType.date>
		<dd class="col-sm-9">${r"${"}(${entityVariable}.${entity.propertyName}?string('yyyy-MM-dd'))!}</dd>
	<#elseif entity.dataType.dateTime>
		<dd class="col-sm-9">${r"${"}(${entityVariable}.${entity.propertyName}?string('yyyy-MM-dd HH:mm:ss'))!}</dd>
	<#elseif entity.dataType.enum>
		<dd class="col-sm-9">${r"${"}(${entityVariable}.${entity.propertyName}.message())!}</dd>
	<#elseif entity.options??>
		<dd class="col-sm-9">${r"${"}all${entity.propertyName?cap_first}s?api.get(${entityVariable}.${entity.propertyName})}</dd>
	<#else>
		<dd class="col-sm-9">${r"${"}${entityVariable}.${entity.propertyName}}</dd>
	</#if>
</#list>
	</dl>
</div>
