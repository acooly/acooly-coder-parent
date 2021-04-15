<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_docService_searchform','manage_docService_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_docService_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					服务版本: <input type="text" class="text" size="15" name="search_LIKE_version"/>
					服务标题: <input type="text" class="text" size="15" name="search_LIKE_title"/>
					所属系统: <input type="text" class="text" size="15" name="search_LIKE_owner"/>
				服务类型: <select style="width:80px;height:27px;" name="search_EQ_serviceType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allServiceTypes as k,v><option value="${k}">${v}</option></#list></select>
				业务类型: <select style="width:80px;height:27px;" name="search_EQ_busiType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allBusiTypes as k,v><option value="${k}">${v}</option></#list></select>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_createTime"/>
					修改时间: <input type="text" class="text" size="15" name="search_LIKE_updateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_docService_searchform','manage_docService_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_docService_datagrid" class="easyui-datagrid" url="/manage/test/docService/listJson.html" toolbar="#manage_docService_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">id</th>
			<th field="serviceNo" formatter="contentFormatter">服务编号</th>
			<th field="name" formatter="contentFormatter">服务名称</th>
			<th field="version">服务版本</th>
			<th field="title">服务标题</th>
			<th field="owner">所属系统</th>
			<th field="note" formatter="contentFormatter">服务说明</th>
			<th field="manualNote" formatter="contentFormatter">手工说明</th>
			<th field="serviceType" formatter="mappingFormatter">服务类型</th>
			<th field="busiType" formatter="mappingFormatter">业务类型</th>
			<th field="sortTime" sortable="true" sum="true">排序值</th>
			<th field="comments" formatter="contentFormatter">备注</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
			<th field="signature" formatter="contentFormatter">签名</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_docService_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_docService_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/test/docService/edit.html',id:'{0}',entity:'docService',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/test/docService/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/test/docService/deleteJson.html','{0}','manage_docService_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_docService_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/test/docService/create.html',entity:'docService',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/test/docService/deleteJson.html','manage_docService_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_docService_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_docService_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/test/docService/exportXls.html','manage_docService_searchform','服务信息')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/test/docService/exportCsv.html','manage_docService_searchform','服务信息')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/test/docService/importView.html',uploader:'manage_docService_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
