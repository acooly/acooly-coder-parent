<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
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
          	<div>
			<#list table.columns as entity>
				<#if entity.name?lower_case != 'id' && entity.length < 128>
				<#if entity.options??>
				${entity.common}: <select style="width:80px;height:27px;" name="search_EQ_${entity.propertyName}" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option>${r"<#list"} all${entity.propertyName?cap_first}s as k,v><option value="${r"${"}k}">${r"${"}v}</option>${r"</#list>"}</select>
				<#else>
				<#if entity.dataType.date>
					${entity.common}: <input size="15" class="text" id="search_GTE_${entity.propertyName}" name="search_GTE_${entity.propertyName}" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_${entity.propertyName}" name="search_LTE_${entity.propertyName}" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
				<#elseif entity.dataType.number>
					${entity.common}: <input type="text" class="text" size="15" name="search_EQ_${entity.propertyName}"/>
				<#else>
					${entity.common}: <input type="text" class="text" size="15" name="search_LIKE_${entity.propertyName}"/>
				</#if>
				</#if>
				</#if>
			</#list>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_${entityVariable}_searchform','manage_${entityVariable}_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_${entityVariable}_datagrid" class="easyui-datagrid" url="${entityContextPath}/listJson.html" toolbar="#manage_${entityVariable}_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
		<#list table.columns as entity>
		<#if entity.options??>
			<th field="${entity.propertyName}" formatter="mappingFormatter">${entity.common}</th>
		<#else>
		<#if entity.dataType.date>
		    <th field="${entity.propertyName}" formatter="dateTimeFormatter">${entity.common}</th>
		<#elseif entity.dataType.number>
			<th field="${entity.propertyName}" sortable="true"<#if entity.dataType.long> sum="true"</#if>>${entity.common}</th>
		<#else>
			<th field="${entity.propertyName}"<#if entity.length gte 128> formatter="contentFormatter"</#if>>${entity.common}</th>
		</#if>
		</#if>
		</#list>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_${entityVariable}_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_${entityVariable}_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'${entityContextPath}/edit.html',id:'{0}',entity:'${entityVariable}',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('${entityContextPath}/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('${entityContextPath}/deleteJson.html','{0}','manage_${entityVariable}_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_${entityVariable}_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'${entityContextPath}/create.html',entity:'${entityVariable}',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('${entityContextPath}/deleteJson.html','manage_${entityVariable}_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_${entityVariable}_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_${entityVariable}_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('${entityContextPath}/exportXls.html','manage_${entityVariable}_searchform','${table.comment}')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('${entityContextPath}/exportCsv.html','manage_${entityVariable}_searchform','${table.comment}')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'${entityContextPath}/importView.html',uploader:'manage_${entityVariable}_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
