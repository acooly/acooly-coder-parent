
<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_codeCustomer_searchform" class="form-inline ac-form-search" onsubmit="return false">
                    <div class="form-group">
                        <label class="col-form-label">用户名：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_username"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">年龄：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_age"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">生日: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_birthday" name="search_GTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_birthday" name="search_LTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">性别:</label>
                        <select name="search_EQ_gender" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allGenders as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">生肖:</label>
                        <select name="search_EQ_animal" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allAnimals as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">姓名：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_realName"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">证件类型:</label>
                        <select name="search_EQ_idcardType" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">身份证号码：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_idcardNo"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">手机号码：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_mobileNo"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">邮件：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_mail"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户类型:</label>
                        <select name="search_EQ_customerType" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">摘要：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_subject"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">注册渠道:</label>
                        <select name="search_EQ_registryChannel" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allRegistryChannels as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">推送广告:</label>
                        <select name="search_EQ_pushAdv" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allPushAdvs as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">数字类型:</label>
                        <select name="search_EQ_numStatus" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allNumStatuss as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">网址：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_website"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">照片：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_photoPath"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">状态:</label>
                        <select name="search_EQ_status" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allStatuss as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">创建时间: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">更新时间: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_codeCustomer_searchform','manage_codeCustomer_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_codeCustomer_datagrid" class="easyui-datagrid" url="/manage/coder/codeCustomer/listJson.html" toolbar="#manage_codeCustomer_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
            <th field="id" sortable="true" >ID</th>
			<th field="username">用户名</th>
            <th field="age" sortable="true" sum="true">年龄</th>
		    <th field="birthday" formatter="dateFormatter">生日</th>
			<th field="gender" formatter="mappingFormatter">性别</th>
			<th field="animal" formatter="mappingFormatter">生肖</th>
			<th field="realName">姓名</th>
			<th field="idcardType" formatter="mappingFormatter">证件类型</th>
			<th field="idcardNo">身份证号码</th>
			<th field="mobileNo">手机号码</th>
			<th field="mail">邮件</th>
			<th field="customerType" formatter="mappingFormatter">客户类型</th>
			<th field="subject" formatter="contentFormatter">摘要</th>
			<th field="content" formatter="contentFormatter">详情</th>
            <th field="doneRatio" formatter="percentFormatter" sortable="true" sum="true">完成度</th>
            <th field="salary" formatter="centMoneyFormatter" sortable="true" sum="true">薪水</th>
			<th field="registryChannel" formatter="mappingFormatter">注册渠道</th>
			<th field="pushAdv" formatter="mappingFormatter">推送广告</th>
			<th field="numStatus" formatter="mappingFormatter">数字类型</th>
			<th field="website" formatter="contentFormatter">网址</th>
			<th field="photoPath" formatter="contentFormatter">照片</th>
			<th field="status" formatter="mappingFormatter">状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
			<th field="comments" formatter="contentFormatter">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_codeCustomer_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_codeCustomer_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/coder/codeCustomer/edit.html',id:'{0}',entity:'codeCustomer',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/coder/codeCustomer/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/coder/codeCustomer/deleteJson.html','{0}','manage_codeCustomer_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_codeCustomer_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/coder/codeCustomer/create.html',entity:'codeCustomer',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/coder/codeCustomer/deleteJson.html','manage_codeCustomer_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_codeCustomer_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_codeCustomer_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/coder/codeCustomer/exportXls.html','manage_codeCustomer_searchform','acoolycoder测试')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/coder/codeCustomer/exportCsv.html','manage_codeCustomer_searchform','acoolycoder测试')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/coder/codeCustomer/importView.html',uploader:'manage_codeCustomer_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_codeCustomer_searchform', 'manage_codeCustomer_datagrid');
        });
    </script>
</div>
