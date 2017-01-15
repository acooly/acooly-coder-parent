<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}" />
<div>
    <form id="manage_${entityVariable}_editform" action="${r"${"}pageContext.request.contextPath}${entityContextPath}/${r"${"}action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="${entityVariable}" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
		<#list table.columns as entity>
			<#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
			<tr>
				<th<#if entity_index == 1> width="25%"</#if>>${entity.common}：</th>
				<#if entity.options??>
				<td><select name="${entity.propertyName}" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" <#if !entity.nullable>data-options="required:true"</#if>>
					<c:forEach items="${r"${"}all${entity.propertyName?cap_first}s}" var="e">
						<option value="${r"${"}e.key}">${r"${"}e.value}</option>
					</c:forEach>
				</select></td>
				<#else>
				<#if entity.dataType.date>
				<td><input type="text" name="${entity.propertyName}" size="20" class="easyui-validatebox text" value="<fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" <#if !entity.nullable>data-options="required:true"</#if> /></td>
				<#elseif entity.dataType.number>
				<td><input type="text" name="${entity.propertyName}" size="48" class="easyui-numberbox text" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"/></td>
				<#else>
				<#if entity.length gt 128>
				<td><textarea rows="3" cols="40" style="width:300px;" name="${entity.propertyName}" class="easyui-validatebox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"></textarea></td>
				<#else>
				<td><input type="text" name="${entity.propertyName}" size="48" class="easyui-validatebox text" <#if !entity.nullable>data-options="required:true"</#if> <#if entity.length gt 0>validType="byteLength[1,${entity.length}]"</#if>/></td>
				</#if>
				</#if>
				</#if>
			</tr>					
			</#if>
		</#list>        
        </table>
      </jodd:form>
    </form>
</div>
