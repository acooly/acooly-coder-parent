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
    <table id="manage_docService_datagrid" class="easyui-datagrid" url="/manage/api/docService/listJson.html" toolbar="#manage_docService_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">id</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
			<th field="busiType" formatter="contentFormatter">busi_type</th>
			<th field="comments" formatter="contentFormatter">comments</th>
			<th field="manualNote" formatter="contentFormatter">manual_note</th>
			<th field="name" formatter="contentFormatter">name</th>
			<th field="note" formatter="contentFormatter">服务介绍</th>
			<th field="owner" formatter="contentFormatter">owner</th>
			<th field="serviceNo" formatter="contentFormatter">service_no</th>
			<th field="serviceType" formatter="contentFormatter">service_type</th>
			<th field="signature" formatter="contentFormatter">signature</th>
			<th field="sortTime" sortable="true" sum="true">sort_time</th>
			<th field="title" formatter="contentFormatter">title</th>
			<th field="version" formatter="contentFormatter">version</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_docService_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_docService_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/api/docService/edit.html',id:'{0}',entity:'docService',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/api/docService/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/api/docService/deleteJson.html','{0}','manage_docService_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_docService_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/api/docService/create.html',entity:'docService',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/api/docService/deleteJson.html','manage_docService_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_docService_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_docService_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/api/docService/exportXls.html','manage_docService_searchform','api_doc_service')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/api/docService/exportCsv.html','manage_docService_searchform','api_doc_service')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/api/docService/importView.html',uploader:'manage_docService_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
