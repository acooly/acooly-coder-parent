<div class="easyui-layout" data-options="fit : true,border : false">
  <!-- 查询条件 -->
  <div data-options="region:'north',border:false" style="padding:5px; overflow: hidden;" align="left">
    <form id="manage_workOrder_searchform" class="form-inline ac-form-search" onsubmit="return false">
                    <div class="form-group">
                        <label class="col-form-label">单号：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_orderNo"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">业务线:</label>
                        <select name="search_EQ_busiLine" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allBusiLines as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">产品线:</label>
                        <select name="search_EQ_productLine" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allProductLines as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">工单标题：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_title"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">工单类型:</label>
                        <select name="search_EQ_orderType" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allOrderTypes as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">要求完成时间: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_planTime" name="search_GTE_planTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_planTime" name="search_LTE_planTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">解决时间: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_fixedTime" name="search_GTE_fixedTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_fixedTime" name="search_LTE_fixedTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">工单优先级:</label>
                        <select name="search_EQ_orderPriority" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allOrderPrioritys as k,v><option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户编码（冗余预留）：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_customerNo"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户名称：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_customerName"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户电话：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_customerPhone"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户邮箱：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_customerEmail"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">客户其他联系信息：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_customerInfo"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">受理人用户：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_acceptUser"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">受理人姓名：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_acceptName"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">处理人用户：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_handlerUser"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">处理人姓名：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_handlerName"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">历史处理人：</label>
                        <input type="text" class="form-control form-control-sm" name="search_EQ_handlers"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">完成时间: </label>
                        <input type="text" class="form-control form-control-sm" id="search_GTE_completeTime" name="search_GTE_completeTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                        <span class="mr-1 ml-1">至</span> <input type="text" class="form-control form-control-sm" id="search_LTE_completeTime" name="search_LTE_completeTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">工单状态:</label>
                        <select name="search_EQ_orderStatus" class="form-control input-sm select2bs4">
                            <option value="">所有</option><#list allOrderStatuss as k,v><option value="${k}">${v}</option></#list>
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
                <button class="btn btn-sm btn-primary" type="button" onclick="$.acooly.framework.search('manage_workOrder_searchform','manage_workOrder_datagrid');"><i class="fa fa-search fa-lg fa-fw fa-col"></i> 查询</button>
            </div>
    </form>
  </div>

  <!-- 列表和工具栏 -->
  <div data-options="region:'center',border:false">
    <table id="manage_workOrder_datagrid" class="easyui-datagrid" url="/manage/workorder/workOrder/listJson.html" toolbar="#manage_workOrder_toolbar" fit="true" border="false" fitColumns="false"
      pagination="true" idField="id" pageSize="20" pageList="[ 10, 20, 30, 40, 50 ]" sortName="id" sortOrder="desc" checkOnSelect="true" selectOnCheck="true" singleSelect="true">
      <thead>
        <tr>
        	<th field="showCheckboxWithId" checkbox="true" formatter="idFormatter">编号</th>
            <th field="id" sortable="true" >ID</th>
			<th field="orderNo">单号</th>
			<th field="busiLine" formatter="mappingFormatter">业务线</th>
			<th field="productLine" formatter="mappingFormatter">产品线</th>
			<th field="title">工单标题</th>
			<th field="orderType" formatter="mappingFormatter">工单类型</th>
		    <th field="planTime" formatter="dateTimeFormatter">要求完成时间</th>
		    <th field="fixedTime" formatter="dateTimeFormatter">解决时间</th>
			<th field="orderPriority" formatter="mappingFormatter">工单优先级</th>
			<th field="customerNo">客户编码（冗余预留）</th>
			<th field="customerName">客户名称</th>
			<th field="customerPhone">客户电话</th>
			<th field="customerEmail">客户邮箱</th>
			<th field="customerInfo">客户其他联系信息</th>
			<th field="acceptUser">受理人用户</th>
			<th field="acceptName">受理人姓名</th>
			<th field="handlerUser">处理人用户</th>
			<th field="handlerName">处理人姓名</th>
			<th field="handlers">历史处理人</th>
		    <th field="completeTime" formatter="dateTimeFormatter">完成时间</th>
			<th field="orderStatus" formatter="mappingFormatter">工单状态</th>
		    <th field="createTime" formatter="dateTimeFormatter">创建时间</th>
		    <th field="updateTime" formatter="dateTimeFormatter">更新时间</th>
			<th field="comments" formatter="contentFormatter">备注</th>
          	<th field="rowActions" data-options="formatter:function(value, row, index){return formatAction('manage_workOrder_action',value,row)}">动作</th>
        </tr>
      </thead>
    </table>

    <!-- 每行的Action动作模板 -->
    <div id="manage_workOrder_action" style="display: none;">
      <a onclick="$.acooly.framework.edit({url:'/manage/workorder/workOrder/edit.html',id:'{0}',entity:'workOrder',width:500,height:500});" href="#" title="编辑"><i class="fa fa-pencil fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.show('/manage/workorder/workOrder/show.html?id={0}',500,500);" href="#" title="查看"><i class="fa fa-file-o fa-lg fa-fw fa-col"></i></a>
      <a onclick="$.acooly.framework.remove('/manage/workorder/workOrder/deleteJson.html','{0}','manage_workOrder_datagrid');" href="#" title="删除"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i></a>
    </div>

    <!-- 表格的工具栏 -->
    <div id="manage_workOrder_toolbar">
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.create({url:'/manage/workorder/workOrder/create.html',entity:'workOrder',width:500,height:500})"><i class="fa fa-plus-circle fa-lg fa-fw fa-col"></i>添加</a>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.removes('/manage/workorder/workOrder/deleteJson.html','manage_workOrder_datagrid')"><i class="fa fa-trash-o fa-lg fa-fw fa-col"></i>批量删除</a>
      <a href="#" class="easyui-menubutton" data-options="menu:'#manage_workOrder_exports_menu'"><i class="fa fa-arrow-circle-o-down fa-lg fa-fw fa-col"></i>批量导出</a>
      <div id="manage_workOrder_exports_menu" style="width:150px;">
        <div onclick="$.acooly.framework.exports('/manage/workorder/workOrder/exportXls.html','manage_workOrder_searchform','客服工单')"><i class="fa fa-file-excel-o fa-lg fa-fw fa-col"></i>Excel</div>
        <div onclick="$.acooly.framework.exports('/manage/workorder/workOrder/exportCsv.html','manage_workOrder_searchform','客服工单')"><i class="fa fa-file-text-o fa-lg fa-fw fa-col"></i>CSV</div>
      </div>
      <a href="#" class="easyui-linkbutton" plain="true" onclick="$.acooly.framework.imports({url:'/manage/workorder/workOrder/importView.html',uploader:'manage_workOrder_import_uploader_file'});"><i class="fa fa-arrow-circle-o-up fa-lg fa-fw fa-col"></i>批量导入</a>
    </div>
  </div>
    <script type="text/javascript">
        $(function () {
            $.acooly.framework.initPage('manage_customer_searchform', 'manage_customer_datagrid');
        });
    </script>
</div>
