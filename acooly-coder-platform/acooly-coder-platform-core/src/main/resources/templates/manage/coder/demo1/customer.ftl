
<script type="text/javascript">
$(function() {
	$.acooly.framework.initPage('manage_customer_searchform','manage_customer_datagrid');
});

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_customer_searchform" onsubmit="return false" class="form-inline ac-form-search">
            <div class="form-group">
                <label class="col-form-label">用户名：</label>
                <input type="text" name="search_LIKE_username" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">生日：</label>
                <input id="search_GTE_birthday" name="search_GTE_birthday" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">至：</label>
                <input id="search_LTE_birthday" name="search_LTE_birthday" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">年龄：</label>
                <input type="text" name="search_EQ_age" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">性别：</label>
                <input type="text" name="search_LIKE_gender" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">生肖：</label>
                <input type="text" name="search_LIKE_animal" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">姓名：</label>
                <input type="text" name="search_LIKE_realName" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">证件类型：</label>
                <select name="search_EQ_idcardType" class="form-control input-sm select2bs4"><option value="">所有</option><#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list></select>
            </div>
            <div class="form-group">
                <label class="col-form-label">身份证号码：</label>
                <input type="text" name="search_LIKE_idcardNo" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">银行卡卡号：</label>
                <input type="text" name="search_LIKE_bankCardNo" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">手机号码：</label>
                <input type="text" name="search_LIKE_mobileNo" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">邮件：</label>
                <input type="text" name="search_LIKE_mail" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">客户类型：</label>
                <select name="search_EQ_customerType" class="form-control input-sm select2bs4"><option value="">所有</option><#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list></select>
            </div>
            <div class="form-group">
                <label class="col-form-label">注册渠道：</label>
                <input type="text" name="search_LIKE_registryChannel" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">推送广告：</label>
                <input type="text" name="search_LIKE_pushAdv" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">数字类型：</label>
                <select name="search_EQ_numStatus" class="form-control input-sm select2bs4"><option value="">所有</option><#list allNumStatuss as k,v><option value="${k}">${v}</option></#list></select>
            </div>
            <div class="form-group">
                <label class="col-form-label">状态：</label>
                <input type="text" name="search_LIKE_status" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">创建时间：</label>
                <input type="text" name="search_LIKE_createTime" class="form-control form-control-sm">
            </div>
            <div class="form-group">
                <label class="col-form-label">更新时间：</label>
                <input type="text" name="search_LIKE_updateTime" class="form-control form-control-sm">
            </div>

            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_customer_searchform','manage_customer_datagrid');"><i class="fa fa-search fa-fw fa-col"></i> 查询</button>
            </div>
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
      <div class="btn-group btn-group-xs">
        <button class="btn btn-outline-primary btn-xs" type="button" onclick="$.acooly.framework.edit({url:'/manage/coder/demo1/customer/edit.html',id:'{0}',entity:'customer',width:500,height:500});"><i class="fa fa-pencil fa-fw fa-col"></i> 编辑</button>
        <button class="btn btn-outline-primary btn-xs" type="button" onclick="$.acooly.framework.show('/manage/coder/demo1/customer/show.html?id={0}',500,500);"><i class="fa fa-info fa-fw fa-col"></i> 查看</button>
        <button class="btn btn-outline-primary btn-xs" type="button" onclick="$.acooly.framework.remove('/manage/coder/demo1/customer/deleteJson.html','{0}','manage_customer_datagrid');"><i class="fa fa-trash fa-fw fa-col"></i> 删除</button>
      </div>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_customer_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/coder/demo1/customer/create.html',entity:'customer',width:500,height:500})"><i class="fa fa-plus-circle fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/coder/demo1/customer/deleteJson.html','manage_customer_datagrid')"><i class="fa fa-trash fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_customer_exports_menu'"><i class="fa fa-cloud-download fa-fw fa-col"></i>批量导出</a>
      <div id="manage_customer_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/coder/demo1/customer/exportXls.html','manage_customer_searchform','代码生成客户信息')"><i class="fa fa-file-excel-o fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/coder/demo1/customer/exportCsv.html','manage_customer_searchform','代码生成客户信息')"><i class="fa fa-file-text-o fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/coder/demo1/customer/importView.html',uploader:'manage_customer_import_uploader_file'});"><i class="fa fa-cloud-upload fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>

</div>
