<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_docMessage_searchform','manage_docMessage_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_docMessage_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_createTime"/>
					修改时间: <input type="text" class="text" size="15" name="search_LIKE_updateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_docMessage_searchform','manage_docMessage_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_docMessage_datagrid" class="easyui-datagrid" url="/manage/api/docMessage/listJson.html" toolbar="#manage_docMessage_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">id</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
			<th field="comments" formatter="contentFormatter">comments</th>
			<th field="manualNote" formatter="contentFormatter">manual_note</th>
			<th field="messageNo" formatter="contentFormatter">message_no</th>
			<th field="messageType" formatter="contentFormatter">message_type</th>
			<th field="note" formatter="contentFormatter">note</th>
			<th field="serviceNo" formatter="contentFormatter">service_no</th>
			<th field="signatrue" formatter="contentFormatter">signatrue</th>
			<th field="sortTime" sortable="true" sum="true">sort_time</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_docMessage_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_docMessage_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/api/docMessage/edit.html',id:'{0}',entity:'docMessage',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/api/docMessage/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/api/docMessage/deleteJson.html','{0}','manage_docMessage_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_docMessage_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/api/docMessage/create.html',entity:'docMessage',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/api/docMessage/deleteJson.html','manage_docMessage_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_docMessage_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_docMessage_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/api/docMessage/exportXls.html','manage_docMessage_searchform','api_doc_message')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/api/docMessage/exportCsv.html','manage_docMessage_searchform','api_doc_message')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/api/docMessage/importView.html',uploader:'manage_docMessage_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
