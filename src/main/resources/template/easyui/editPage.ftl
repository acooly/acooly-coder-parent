<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>
<#assign entityVariable="${names.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${names.domainClassName?uncap_first}" />
<div>
    <form id="manage_${entityVariable}_editform" action="${r"${"}pageContext.request.contextPath}${entityContextPath}/${r"${"}action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="${entityVariable}" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
		<#list table.columnMetadatas as entity>
			<#if entity.name?lower_case != 'id'>
			<tr>
				<th width="20%">${entity.common}：</th>
				<td>
			<#if entity.dataType == 2>
				<input type="text" name="${entity.propertyName}" size="15" value="<fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" <#if !entity.nullable>data-options="required:true"</#if> />
			<#elseif entity.dataType == 1 || entity.dataType == 4>
				<#if entity.options??>
				<select name="${entity.propertyName}" editable="false" panelHeight="auto" class="easyui-combobox" <#if !entity.nullable>data-options="required:true"</#if>>
					<c:forEach items="${r"${"}all${entity.propertyName?cap_first}s}" var="e">
						<option value="${r"${"}e.key}">${r"${"}e.value}</option>
					</c:forEach>
				</select>
				<#else>
				<input type="text" name="${entity.propertyName}" class="easyui-numberbox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"/>	
				</#if>
			<#else>	
				<input type="text" name="${entity.propertyName}" class="easyui-validatebox" <#if !entity.nullable>data-options="required:true"</#if> validType="byteLength[1,${entity.length}]"/>
			</#if>
				</td>
			</tr>					
			</#if>
		</#list>        
        </table>
      </jodd:form>
    </form>
</div>
