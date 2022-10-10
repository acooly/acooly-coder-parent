<#if configuration.sso><${r"#"}if ssoEnable><${r"#"}include "/manage/common/ssoInclude.ftl"></${r"#"}if></#if>
<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_${entityVariable}_searchform" class="form-inline ac-form-search" onsubmit="return false">
			<#list table.columns as entity>
				<#if entity.name?lower_case != 'id' && entity.length < 128>
				<#if entity.options?? || entity.dataType.enum>
                    <div class="form-group">
                        <label class="col-form-label">${entity.common}：</label>
                        <select name="search_EQ_${entity.propertyName}" class="form-control input-sm select2bs4">
                            <option value="">所有</option>${r"<#list"} all${entity.propertyName?cap_first}s as k,v><option value="${r"${"}k}">${r"${"}v}</option>${r"</#list>"}
                        </select>
                    </div>
				<#else>
				<#if entity.dataType.date || entity.dataType.dateTime>
                    <div class="form-group">
                        <label class="col-form-label">${entity.common}：</label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_${entity.propertyName}" name="search_GTE_${entity.propertyName}" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_${entity.propertyName}" name="search_LTE_${entity.propertyName}" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
				<#else>
                    <div class="form-group">
                        <label class="col-form-label">${entity.common}：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_${entity.propertyName}"/>
                    </div>
				</#if>
				</#if>
				</#if>
			</#list>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_${entityVariable}_searchform','manage_${entityVariable}_datagrid');"><i class="fa fa-search fa-fw fa-col"></i> 查询</button>
            </div>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_${entityVariable}_datagrid" class="easyui-datagrid" url="${entityContextPath}/listJson.html" toolbar="#manage_${entityVariable}_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
		<#list table.columns as entity>
		<#if entity.options?? || entity.dataType.enum>
			<th field="${entity.propertyName}" formatter="mappingFormatter">${entity.common}</th>
		<#else>
		<#if entity.dataType.dateTime>
		    <th field="${entity.propertyName}" formatter="dateTimeFormatter">${entity.common}</th>
        <#elseif entity.dataType.date>
		    <th field="${entity.propertyName}" formatter="dateFormatter">${entity.common}</th>
        <#elseif entity.dataType.number>
            <th field="${entity.propertyName}"<#if entity.columnType == 'money'> formatter="centMoneyFormatter"<#elseif entity.columnType == 'percent'> formatter="percentFormatter"<#elseif entity.columnType == 'centPercent'>formatter="centPercentFormatter"</#if> sortable="true" <#if entity.propertyName != 'id'>sum="true"</#if>>${entity.common}</th>
		<#else>
            <#if entity.columnType=='file'>
            <th field="${entity.propertyName}" formatter="fileFormatter">${entity.common}</th>
            <#else>
			<th field="${entity.propertyName}"<#if entity.length gte 128> formatter="contentFormatter"</#if>>${entity.common}</th>
			</#if>
		</#if>
		</#if>
		</#list>
        </tr>
      </thead>
      <thead frozen="true">
        <tr>
            <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_${entityVariable}_action',value,row)}">动作</th>
        </tr>
      </thead>

    </table>

      <!-- 每行的Action动作模板 -->
      <div id="manage_${entityVariable}_action" style="display: none;">
          <div class="btn-group btn-group-xs">
              <button onclick="$.acooly.framework.show('${entityContextPath}/show.html?id={0}',500,500);" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-info fa-fw fa-col"></i>查看</button>
              <button onclick="$.acooly.framework.edit({url:'${entityContextPath}/edit.html',id:'{0}',entity:'${entityVariable}',width:500,height:500});" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-pencil fa-fw fa-col"></i>编辑</button>
              <#if table.moveFunc>
              <button onclick="$.acooly.framework.move('${entityContextPath}/upJson.html','{0}','manage_${entityVariable}_datagrid');" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-arrow-up fa-fw fa-col"></i>上移</button>
              <button onclick="$.acooly.framework.move('${entityContextPath}/topJson.html','{0}','manage_${entityVariable}_datagrid');" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-step-forward fa-rotate-270 fa-fw fa-col"></i>置顶</button>
              </#if>
              <button onclick="$.acooly.framework.remove('${entityContextPath}/deleteJson.html','{0}','manage_${entityVariable}_datagrid');" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-trash fa-fw fa-col"></i>删除</button>
          </div>
      </div>

      <!-- 表格的工具栏 -->
      <div id="manage_${entityVariable}_toolbar">
          <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'${entityContextPath}/create.html',entity:'${entityVariable}',width:500,height:500})"><i class="fa fa-plus-circle fa-fw fa-col"></i>添加</a>
          <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('${entityContextPath}/deleteJson.html','manage_${entityVariable}_datagrid')"><i class="fa fa-trash fa-fw fa-col"></i>批量删除</a>
          <a href="#" class="easyui-menubutton" data-options="menu:'#manage_${entityVariable}_exports_menu'"><i class="fa fa-cloud-download fa-fw fa-col"></i>批量导出</a>
          <div id="manage_${entityVariable}_exports_menu" style="width:150px;">
              <div onclick="$.acooly.framework.exports('${entityContextPath}/exportXls.html','manage_${entityVariable}_searchform','${table.comment}')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
              <div onclick="$.acooly.framework.exports('${entityContextPath}/exportCsv.html','manage_${entityVariable}_searchform','${table.comment}')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
          </div>
          <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'${entityContextPath}/importView.html',uploader:'manage_${entityVariable}_import_uploader_file'});"><i class="fa fa-cloud-upload fa-fw fa-col"></i>批量导入</a>
      </div>
  </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_${entityVariable}_searchform', 'manage_${entityVariable}_datagrid');
        });
    </script>
</div>
