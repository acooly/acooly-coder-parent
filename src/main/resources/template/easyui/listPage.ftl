<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/manage/common/taglibs.jsp"%>

<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.pagePath}/${nameScheme.domainClassName?uncap_first}" />

<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_${entityVariable}_searchform','manage_${entityVariable}_datagrid');
});
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_${entityVariable}_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
			<#list table.columnMetadatas as entity>
				<#if entity.name?lower_case != 'id'>
				<#if entity.options??>
				${entity.common}:<select style="width:80px;" name="search_EQ_${entity.propertyName}" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><c:forEach var="e" items="${r"${"}all${entity.propertyName?cap_first}s}"><option value="${r"${"}e.key}" ${r"${"}param.search_EQ_${entity.propertyName} == e.key?'selected':''}>${r"${"}e.value}</option></c:forEach></select>
				<#else>
				<#if entity.dataType == 2>
					${entity.common}:<input size="15" id="search_GTE_${entity.propertyName}" name="search_GTE_${entity.propertyName}" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					至<input size="15" id="search_LTE_${entity.propertyName}" name="search_LTE_${entity.propertyName}" size="15" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 			
				<#elseif entity.dataType == 1 || entity.dataType == 4>
					${entity.common}:<input type="text" size="15" name="search_EQ_${entity.propertyName}" value="${r"${"}param.search_EQ_${entity.propertyName}}"  />
				<#else>	
					${entity.common}:<input type="text" size="15" name="search_LIKE_${entity.propertyName}" value="${r"${"}param.search_LIKE_${entity.propertyName}}"  />
				</#if>	
				</#if>							
				</#if>
			</#list>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:false" onclick="$.acooly.framework.search('manage_${entityVariable}_searchform','manage_${entityVariable}_datagrid');">查询</a> 
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_${entityVariable}_datagrid" class="easyui-datagrid" url="${r"${"}pageContext.request.contextPath}${entityContextPath}/listJson.html" toolbar="#manage_${entityVariable}_toolbar" fit="true" border="false" fitColumns="true"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
		<#list table.columnMetadatas as entity>
		<#if entity.options??>
			<th field="${entity.propertyName}" data-options="formatter:function(value){ return formatRefrence('manage_${entityVariable}_datagrid','all${entity.propertyName?cap_first}s',value);} ">${entity.common}</th>
		<#else>
		<#if entity.dataType == 2>
		    <th field="${entity.propertyName}" formatter="formatDate">${entity.common}</th>
		<#elseif entity.dataType == 1 || entity.dataType == 4>
			<th field="${entity.propertyName}">${entity.common}</th>
		<#else>	
			<th field="${entity.propertyName}">${entity.common}</th>
		</#if>	
		</#if>				
		</#list>        
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_${entityVariable}_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>
    
    <!-- 每行的Action动作模板 -->
    <div id="manage_${entityVariable}_action" style="display: none;">
      <a class="line-action icon-edit" onclick="$.acooly.framework.edit({url:'${entityContextPath}/edit.html',id:'{0}',entity:'${entityVariable}',width:500,height:400});" href="#" title="编辑"></a>
      <a class="line-action icon-show" onclick="$.acooly.framework.show('${entityContextPath}/show.html?id={0}',500,400);" href="#" title="查看"></a>
      <a class="line-action icon-delete" onclick="$.acooly.framework.remove('${entityContextPath}/deleteJson.html','{0}','manage_${entityVariable}_datagrid');" href="#" title="删除"></a>
    </div>
    
    <!-- 表格的工具栏 -->
    <div id="manage_${entityVariable}_toolbar">
      <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$.acooly.framework.create({url:'${entityContextPath}/create.html',entity:'${entityVariable}',width:500,height:400})">添加</a> 
      <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="$.acooly.framework.removes('${entityContextPath}/deleteJson.html','manage_${entityVariable}_datagrid')">批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_${entityVariable}_exports_menu',iconCls:'icon-export'">批量导出</a>
      <div id="manage_${entityVariable}_exports_menu" style="width:150px;">
        <div data-options="iconCls:'icon-excel'" onclick="$.acooly.framework.exports('${entityContextPath}/exportXls.html','manage_${entityVariable}_searchform','${table.comment}')">Excel</div>  
        <div data-options="iconCls:'icon-csv'" onclick="$.acooly.framework.exports('${entityContextPath}/exportCsv.html','manage_${entityVariable}_searchform','${table.comment}')">CSV</div> 
      </div>
      <a href="#" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="$.acooly.framework.imports({url:'${entityContextPath}/importView.html',uploader:'manage_${entityVariable}_import_uploader_file'});">批量导入</a> 
    </div>
  </div>

</div>
