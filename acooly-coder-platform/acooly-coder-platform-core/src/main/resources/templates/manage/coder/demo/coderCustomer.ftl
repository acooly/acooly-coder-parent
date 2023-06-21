<div class="easyui-layout" data-options="fit : true,border : false">
    <!-- 查询条件 -->
    <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;">
        <form id="manage_coderCustomer_searchform" class="form-inline ac-form-search" onsubmit="return false">
            <div class="form-group">
                <label class="col-form-label">用户名：</label>
                <input type="text" class="form-control form-control-sm" name="search_EQ_username"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">生日：</label>
                <input type="text" class="form-control form-control-sm" id="search_GTE_birthday" name="search_GTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_birthday" name="search_LTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">手机号码：</label>
                <input type="text" class="form-control form-control-sm" name="search_EQ_mobileNo"/>
            </div>
            <div class="form-group">
                <label class="col-form-label">客户类型：</label>
                <select name="search_EQ_customerType" class="form-control input-sm select2bs4">
                    <option value="">所有</option><#list allCustomerTypes as k,v>
                    <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">注册渠道：</label>
                <select name="search_EQ_registryChannel" class="form-control input-sm select2bs4">
                    <option value="">所有</option><#list allRegistryChannels as k,v>
                    <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">数字类型：</label>
                <select name="search_EQ_numStatus" class="form-control input-sm select2bs4">
                    <option value="">所有</option><#list allNumStatuss as k,v>
                    <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">状态：</label>
                <select name="search_EQ_status" class="form-control input-sm select2bs4">
                    <option value="">所有</option><#list allStatuss as k,v>
                    <option value="${k}">${v}</option></#list>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label">创建时间：</label>
                <input type="text" class="form-control form-control-sm" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_coderCustomer_searchform','manage_coderCustomer_datagrid');"><i class="fa fa-search fa-fw fa-col"></i> 查询</button>
            </div>
        </form>
    </div>

    <!-- 列表和工具栏 -->
    <div data-options="region:'center',border:false">
        <table id="manage_coderCustomer_datagrid" class="easyui-datagrid" url="/manage/coder/demo/coderCustomer/listJson.html" toolbar="#manage_coderCustomer_toolbar" fit="true" border="false" fitColumns="false"
               pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
            <thead>
            <tr>
                <th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
                <th field="id" sortable="true">ID</th>
                <th field="username">用户名</th>
                <th field="birthday" formatter="dateFormatter">生日</th>
                <th field="age" sortable="true" sum="true">年龄</th>
                <th field="gender" formatter="mappingFormatter">性别</th>
                <th field="realName">姓名</th>
                <th field="idcardType" formatter="mappingFormatter">证件类型</th>
                <th field="mobileNo">手机号码</th>
                <th field="customerType" formatter="mappingFormatter">客户类型</th>
                <th field="doneRatio" formatter="percentFormatter" sortable="true" sum="true">完成度</th>
                <th field="payRate" formatter="centPercentFormatter" sortable="true" sum="true">付款率</th>
                <th field="salary" formatter="centMoneyFormatter" sortable="true" sum="true">薪水</th>
                <th field="registryChannel" formatter="mappingFormatter">注册渠道</th>
                <th field="pushAdv" formatter="mappingFormatter">推送广告</th>
                <th field="numStatus" formatter="mappingFormatter">数字类型</th>
                <th field="photoPath" formatter="fileFormatter">照片</th>
                <th field="status" formatter="mappingFormatter">状态</th>
                <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
                <th field="comments" formatter="contentFormatter">备注</th>
            </tr>
            </thead>
            <thead frozen="true">
            <tr>
                <th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_coderCustomer_action',value,row)}">动作</th>
            </tr>
            </thead>
        </table>
        <!-- 每行的Action动作模板 -->
        <div id="manage_coderCustomer_action" style="display: none;">
            <div class="btn-group btn-group-xs">
                <button onclick="$.acooly.framework.show('/manage/coder/demo/coderCustomer/show.html?id={0}',500,500);" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-info fa-fw fa-col"></i>查看</button>
                <button onclick="$.acooly.framework.edit({url:'/manage/coder/demo/coderCustomer/edit.html',id:'{0}',entity:'coderCustomer',width:500,height:500});" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-pencil fa-fw fa-col"></i>编辑</button>
                <button onclick="$.acooly.framework.remove('/manage/coder/demo/coderCustomer/deleteJson.html','{0}','manage_coderCustomer_datagrid');" class="btn btn-outline-primary btn-xs" type="button"><i class="fa fa-trash fa-fw fa-col"></i>删除</button>
            </div>
        </div>
        <!-- 表格的工具栏 -->
        <div id="manage_coderCustomer_toolbar">
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/coder/demo/coderCustomer/create.html',entity:'coderCustomer',width:500,height:500})"><i class="fa fa-plus-circle fa-fw fa-col"></i>添加</a>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/coder/demo/coderCustomer/deleteJson.html','manage_coderCustomer_datagrid')"><i class="fa fa-trash fa-fw fa-col"></i>批量删除</a>
            <a href="#" class="easyui-menubutton" data-options="menu:'#manage_coderCustomer_exports_menu'"><i class="fa fa-cloud-download fa-fw fa-col"></i>批量导出</a>
            <div id="manage_coderCustomer_exports_menu" style="width:150px;">
                <div onclick="$.acooly.framework.exports('/manage/coder/demo/coderCustomer/exportXls.html','manage_coderCustomer_searchform','代码生成客户信息')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
                <div onclick="$.acooly.framework.exports('/manage/coder/demo/coderCustomer/exportCsv.html','manage_coderCustomer_searchform','代码生成客户信息')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
            </div>
            <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/coder/demo/coderCustomer/importView.html',uploader:'manage_coderCustomer_import_uploader_file'});"><i class="fa fa-cloud-upload fa-fw fa-col"></i>批量导入</a>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_coderCustomer_searchform', 'manage_coderCustomer_datagrid');
        });
    </script>
</div>
