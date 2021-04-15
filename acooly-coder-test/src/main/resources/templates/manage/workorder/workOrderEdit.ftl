<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_workOrder_editform" class="form-horizontal" action="/manage/workorder/workOrder/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="workOrder" scope="request">
        <input name="id" type="hidden" />
		<div class="card-body">
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">单号</label>
				<div class="col-sm-9">
					<input type="text" name="orderNo" placeholder="请输入单号..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">业务线</label>
				<div class="col-sm-9">
					<select name="busiLine" class="form-control select2bs4" data-options="required:true">
						<#list allBusiLines as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">产品线</label>
				<div class="col-sm-9">
					<select name="productLine" class="form-control select2bs4" data-options="required:true">
						<#list allProductLines as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">工单标题</label>
				<div class="col-sm-9">
					<input type="text" name="title" placeholder="请输入工单标题..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,127]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">工单类型</label>
				<div class="col-sm-9">
					<select name="orderType" class="form-control select2bs4" data-options="required:true">
						<#list allOrderTypes as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">要求完成时间</label>
				<div class="col-sm-9">
					<input type="text" name="planTime" placeholder="请输入要求完成时间..." class="easyui-validatebox form-control" value="<#if workOrder.planTime??>${workOrder.planTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">解决时间</label>
				<div class="col-sm-9">
					<input type="text" name="fixedTime" placeholder="请输入解决时间..." class="easyui-validatebox form-control" value="<#if workOrder.fixedTime??>${workOrder.fixedTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">工单优先级</label>
				<div class="col-sm-9">
					<select name="orderPriority" class="form-control select2bs4" data-options="required:true">
						<#list allOrderPrioritys as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户编码（冗余预留）</label>
				<div class="col-sm-9">
					<input type="text" name="customerNo" placeholder="请输入客户编码（冗余预留）..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户名称</label>
				<div class="col-sm-9">
					<input type="text" name="customerName" placeholder="请输入客户名称..." class="easyui-validatebox form-control" data-options="validType:['chinese','length[1,45]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户电话</label>
				<div class="col-sm-9">
					<input type="text" name="customerPhone" placeholder="请输入客户电话..." class="easyui-validatebox form-control" data-inputmask="'alias':'mobile'" data-mask data-options="validType:['mobile','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户邮箱</label>
				<div class="col-sm-9">
					<input type="text" name="customerEmail" placeholder="请输入客户邮箱..." class="easyui-validatebox form-control" data-inputmask="'alias':'email'" data-mask data-options="validType:['email','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户其他联系信息</label>
				<div class="col-sm-9">
					<input type="text" name="customerInfo" placeholder="请输入客户其他联系信息..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">受理人用户</label>
				<div class="col-sm-9">
					<input type="text" name="acceptUser" placeholder="请输入受理人用户..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">受理人姓名</label>
				<div class="col-sm-9">
					<input type="text" name="acceptName" placeholder="请输入受理人姓名..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">处理人用户</label>
				<div class="col-sm-9">
					<input type="text" name="handlerUser" placeholder="请输入处理人用户..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">处理人姓名</label>
				<div class="col-sm-9">
					<input type="text" name="handlerName" placeholder="请输入处理人姓名..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">历史处理人</label>
				<div class="col-sm-9">
					<input type="text" name="handlers" placeholder="请输入历史处理人..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,45]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">完成时间</label>
				<div class="col-sm-9">
					<input type="text" name="completeTime" placeholder="请输入完成时间..." class="easyui-validatebox form-control" value="<#if workOrder.completeTime??>${workOrder.completeTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">工单状态</label>
				<div class="col-sm-9">
					<select name="orderStatus" class="form-control select2bs4" data-options="required:true">
						<#list allOrderStatuss as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">备注</label>
				<div class="col-sm-9">
					<textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox form-control" ></textarea>
				</div>
			</div>
        </div>
      </@jodd.form>
    </form>
</div>
