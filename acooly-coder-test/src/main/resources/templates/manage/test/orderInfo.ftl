<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_orderInfo_searchform','manage_orderInfo_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_orderInfo_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					访问key: <input type="text" class="text" size="15" name="search_LIKE_accessKey"/>
					商户ID: <input type="text" class="text" size="15" name="search_LIKE_partnerId"/>
					统一流水: <input type="text" class="text" size="15" name="search_LIKE_gid"/>
					请求号: <input type="text" class="text" size="15" name="search_LIKE_requestNo"/>
					服务名: <input type="text" class="text" size="15" name="search_LIKE_service"/>
					版本号: <input type="text" class="text" size="15" name="search_LIKE_version"/>
					签名类型: <input type="text" class="text" size="15" name="search_LIKE_signType"/>
					协议: <input type="text" class="text" size="15" name="search_LIKE_protocol"/>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_rawAddTime"/>
					修改时间: <input type="text" class="text" size="15" name="search_LIKE_rawUpdateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_orderInfo_searchform','manage_orderInfo_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_orderInfo_datagrid" class="easyui-datagrid" url="/manage/test/orderInfo/listJson.html" toolbar="#manage_orderInfo_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">ID</th>
			<th field="accessKey">访问key</th>
			<th field="partnerId">商户ID</th>
			<th field="gid">统一流水</th>
			<th field="requestNo">请求号</th>
			<th field="service">服务名</th>
			<th field="version">版本号</th>
			<th field="signType">签名类型</th>
			<th field="protocol">协议</th>
			<th field="notifyUrl" formatter="contentFormatter">通知地址</th>
			<th field="returnUrl" formatter="contentFormatter">返回地址</th>
			<th field="businessInfo" formatter="contentFormatter">扩展信息</th>
			<th field="context" formatter="contentFormatter">会话信息</th>
		    <th field="rawAddTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="rawUpdateTime" formatter="dateTimeFormatter">修改时间</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_orderInfo_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_orderInfo_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/test/orderInfo/edit.html',id:'{0}',entity:'orderInfo',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/test/orderInfo/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/test/orderInfo/deleteJson.html','{0}','manage_orderInfo_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_orderInfo_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/test/orderInfo/create.html',entity:'orderInfo',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/test/orderInfo/deleteJson.html','manage_orderInfo_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_orderInfo_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_orderInfo_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/test/orderInfo/exportXls.html','manage_orderInfo_searchform','请求信息表')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/test/orderInfo/exportCsv.html','manage_orderInfo_searchform','请求信息表')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/test/orderInfo/importView.html',uploader:'manage_orderInfo_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
