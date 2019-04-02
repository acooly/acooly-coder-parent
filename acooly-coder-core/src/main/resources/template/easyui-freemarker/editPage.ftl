<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
<${r"#"}assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_${entityVariable}_editform" action="${entityContextPath}/<${r"#"}if action=='create'>saveJson<${r"#"}else>updateJson</${r"#"}if>.html" method="post">
		<${r"@"}jodd.form bean="${entityVariable}" scope="request">
        <input name="id" type="hidden" />
        <table class="editForm" width="100%">
		<#list table.columns as entity>
			<#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
			<tr>
				<th<#if entity_index == 1> width="25%"</#if>>${entity.common}：</th>
				<#if entity.options??>
				<td><select name="${entity.propertyName}" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" <#if !entity.nullable>data-options="required:true"</#if>>
					<${r"#"}list all${entity.propertyName?cap_first}s as k,v><option value="${r"${"}k}">${r"${"}v}</option></${r"#"}list>
				</select></td>
				<#else>
				<#if entity.dataType.date>
				<td><input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox" value="<${r"#"}if ${entityVariable}.${entity.propertyName}??>${r"${"}${entityVariable}.${entity.propertyName}?string('yyyy-MM-dd HH:mm:ss')}</${r"#"}if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" <#if !entity.nullable>data-options="required:true"</#if> /></td>
				<#elseif entity.dataType.number>
				<td><input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/></td>
				<#else>
				<#if entity.length gte 128>
				<td><textarea rows="3" cols="40" placeholder="请输入${entity.common}..." name="${entity.propertyName}" class="easyui-validatebox" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"></textarea></td>
				<#else>
				<td><input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/></td>
				</#if>
				</#if>
				</#if>
			</tr>					
			</#if>
		</#list>        
        </table>
      </${r"@"}jodd.form>
    </form>
</div>
