
<script type="text/javascript">
$(function() {
	$.acooly.framework.registerKeydown('manage_customer_searchform','manage_customer_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_customer_searchform" onsubmit="return false">
      <table class="tableForm" width="100%">
        <tr>
          <td align="left">
          	<div>
					用户名: <input type="text" class="text" size="15" name="search_LIKE_username"/>
					生日: <input size="15" class="text" id="search_GTE_birthday" name="search_GTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					至<input size="15" class="text" id="search_LTE_birthday" name="search_LTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
					年龄: <input type="text" class="text" size="15" name="search_EQ_age"/>
					性别: <input type="text" class="text" size="15" name="search_LIKE_gender"/>
					生肖: <input type="text" class="text" size="15" name="search_LIKE_animal"/>
					姓名: <input type="text" class="text" size="15" name="search_LIKE_realName"/>
				证件类型: <select style="width:80px;height:27px;" name="search_EQ_idcardType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list></select>
					身份证号码: <input type="text" class="text" size="15" name="search_LIKE_idcardNo"/>
					银行卡卡号: <input type="text" class="text" size="15" name="search_LIKE_bankCardNo"/>
					手机号码: <input type="text" class="text" size="15" name="search_LIKE_mobileNo"/>
					邮件: <input type="text" class="text" size="15" name="search_LIKE_mail"/>
				客户类型: <select style="width:80px;height:27px;" name="search_EQ_customerType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list></select>
					注册渠道: <input type="text" class="text" size="15" name="search_LIKE_registryChannel"/>
					推送广告: <input type="text" class="text" size="15" name="search_LIKE_pushAdv"/>
				数字类型: <select style="width:80px;height:27px;" name="search_EQ_numStatus" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allNumStatuss as k,v><option value="${k}">${v}</option></#list></select>
					状态: <input type="text" class="text" size="15" name="search_LIKE_status"/>
					创建时间: <input type="text" class="text" size="15" name="search_LIKE_createTime"/>
					更新时间: <input type="text" class="text" size="15" name="search_LIKE_updateTime"/>
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false" onclick="$.acooly.framework.search('manage_customer_searchform','manage_customer_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i>查询</a>
          	</div>
          </td>
        </tr>
      </table>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_customer_datagrid" class="easyui-datagrid" url="/manage/coder/demo1/customer/listJson.html" toolbar="#manage_customer_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" data-options="formatter:function(value, row, index){ return row.id }">编号</th>
			<th field="id" sortable="true" sum="true">ID</th>
			<th field="username">用户名</th>
		    <th field="birthday" formatter="dateFormatter">生日</th>
			<th field="age" sortable="true">年龄</th>
			<th field="gender">性别</th>
			<th field="animal">生肖</th>
			<th field="realName">姓名</th>
			<th field="idcardType" formatter="mappingFormatter">证件类型</th>
			<th field="idcardNo">身份证号码</th>
			<th field="bankCardNo">银行卡卡号</th>
			<th field="mobileNo">手机号码</th>
			<th field="mail">邮件</th>
			<th field="customerType" formatter="mappingFormatter">客户类型</th>
			<th field="subject" formatter="contentFormatter">摘要</th>
			<th field="content" formatter="contentFormatter">详情</th>
			<th field="doneRatio" sortable="true">完成度</th>
			<th field="payRate" sortable="true" sum="true">付款率</th>
			<th field="salary" sortable="true">薪水</th>
			<th field="registryChannel">注册渠道</th>
			<th field="pushAdv">推送广告</th>
			<th field="numStatus" formatter="mappingFormatter">数字类型</th>
			<th field="website" formatter="contentFormatter">网址</th>
			<th field="photoPath" formatter="contentFormatter">照片</th>
			<th field="status">状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
			<th field="comments" formatter="contentFormatter">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_customer_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_customer_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/coder/demo1/customer/edit.html',id:'{0}',entity:'customer',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/coder/demo1/customer/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/coder/demo1/customer/deleteJson.html','{0}','manage_customer_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_customer_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/coder/demo1/customer/create.html',entity:'customer',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/coder/demo1/customer/deleteJson.html','manage_customer_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_customer_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_customer_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/coder/demo1/customer/exportXls.html','manage_customer_searchform','代码生成客户信息')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/coder/demo1/customer/exportCsv.html','manage_customer_searchform','代码生成客户信息')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/coder/demo1/customer/importView.html',uploader:'manage_customer_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
