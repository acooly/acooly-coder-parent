<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
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
				<td><input type="text" name="${entity.propertyName}" size="20" placeholder="请输入${entity.common}..." class="easyui-validatebox text" value="<fmt:formatDate value="${r"${"}${entityVariable}.${entity.propertyName}}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" <#if !entity.nullable>data-options="required:true"</#if> /></td>
				<#elseif entity.dataType.number>
				<td><input type="text" name="${entity.propertyName}" size="48" placeholder="请输入${entity.common}..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/></td>
				<#else>
				<#if entity.length gt 128>
				<td><textarea rows="3" cols="40" placeholder="请输入${entity.common}..." style="width:300px;" name="${entity.propertyName}" class="easyui-validatebox" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"></textarea></td>
				<#else>
				<td><input type="text" name="${entity.propertyName}" size="48" placeholder="请输入${entity.common}..." class="easyui-validatebox text" data-options="validType:['length[1,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/></td>
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
