<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_docItem_searchform','manage_docItem_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_docItem_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					数据类型: <input type="text" class="text" size="15" name="search_LIKE_dataType"/>
					可选状态: <input type="text" class="text" size="15" name="search_LIKE_status"/>
				是否加密: <select style="width:80px;height:27px;" name="search_EQ_encryptstatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allEncryptstatuss as k,v><option value="${k}">${v}</option></#list></select>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_createTime"/>
					修改时间: <input type="text" class="text" size="15" name="search_LIKE_updateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_docItem_searchform','manage_docItem_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_docItem_datagrid" class="easyui-datagrid" url="/manage/api/docItem/listJson.html" toolbar="#manage_docItem_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">id</th>
			<th field="parentId" sortable="true" sum="true">parent_id</th>
			<th field="itemNo" formatter="contentFormatter">字段编号</th>
			<th field="name" formatter="contentFormatter">字段名称</th>
			<th field="title" formatter="contentFormatter">字段标题</th>
			<th field="dataLength" sortable="true">字段长度</th>
			<th field="dataType">数据类型</th>
			<th field="descn" formatter="contentFormatter">字段描述</th>
			<th field="demo" formatter="contentFormatter">字段示例</th>
			<th field="status">可选状态</th>
			<th field="encryptstatus" formatter="mappingFormatter">是否加密</th>
			<th field="comments" formatter="contentFormatter">备注</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">修改时间</th>
			<th field="max" sortable="true">max</th>
			<th field="messageNo" formatter="contentFormatter">message_no</th>
			<th field="min" sortable="true">min</th>
			<th field="parentNo" formatter="contentFormatter">parent_no</th>
			<th field="signatrue" formatter="contentFormatter">signatrue</th>
			<th field="sortTime" sortable="true" sum="true">sort_time</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_docItem_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_docItem_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/api/docItem/edit.html',id:'{0}',entity:'docItem',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/api/docItem/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/api/docItem/deleteJson.html','{0}','manage_docItem_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_docItem_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/api/docItem/create.html',entity:'docItem',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/api/docItem/deleteJson.html','manage_docItem_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_docItem_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_docItem_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/api/docItem/exportXls.html','manage_docItem_searchform','报文字段')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/api/docItem/exportCsv.html','manage_docItem_searchform','报文字段')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/api/docItem/importView.html',uploader:'manage_docItem_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
