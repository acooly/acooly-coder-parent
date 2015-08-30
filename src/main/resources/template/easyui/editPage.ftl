<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}" />
<div>
    <form id="manage_${entityVariable}_editform" action="${r"${"}pageContext.request.contextPath}${entityContextPath}/${r"${"}action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="${entityVariable}" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
		<#list table.columnMetadatas as entity>
			<#if entity.name?lower_case != 'id'>
			<tr>
				<th<#if entity_index == 1> width="25%"</#if>>${entity.common}：</th>
			<#if entity.dataType == 2>
				<td><input type="text" name="${entity.propertyName}" size="15" value="<fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" <#if !entity.nullable>data-options="required:true"</#if> /></td>
			<#elseif entity.dataType == 1 || entity.dataType == 4>
				<#if entity.options??>
				<td><select name="${entity.propertyName}" editable="false" panelHeight="auto" class="easyui-combobox" <#if !entity.nullable>data-options="required:true"</#if>>
					<c:forEach items="${r"${"}all${entity.propertyName?cap_first}s}" var="e">
						<option value="${r"${"}e.key}">${r"${"}e.value}</option>
					</c:forEach>
				</select></td>
				<#else>
				<td><input type="text" name="${entity.propertyName}" class="easyui-numberbox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"/></td>
				</#if>
			<#else>
				<#if entity.length gte 128>
				<td><textarea rows="3" cols="40" name="${entity.propertyName}" class="easyui-validatebox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"></textarea></td>
				<#else>
				<td><input type="text" name="${entity.propertyName}" class="easyui-validatebox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"/></td>
				</#if>
			</#if>
			</tr>					
			</#if>
		</#list>        
        </table>
      </jodd:form>
    </form>
</div>
