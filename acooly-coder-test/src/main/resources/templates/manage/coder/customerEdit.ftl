<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_customer_editform" class="form-horizontal" action="/manage/coder/customer/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="customer" scope="request">
        <input name="id" type="hidden" />
		<div class="card-body">
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">用户名</label>
				<div class="col-sm-9">
					<input type="text" name="username" placeholder="请输入用户名..." class="easyui-validatebox form-control" data-inputmask="'alias':'account'" data-mask data-options="validType:['account','length[1,32]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">年龄</label>
				<div class="col-sm-9">
					<input type="text" name="age" placeholder="请输入年龄..." class="easyui-validatebox form-control" data-options="validType:['number[0,127]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">生日</label>
				<div class="col-sm-9">
					<input type="text" name="birthday" placeholder="请输入生日..." class="easyui-validatebox form-control" value="<#if customer.birthday??>${customer.birthday?string('yyyy-MM-dd')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">性别</label>
				<div class="col-sm-9">
					<select name="gender" class="form-control select2bs4" data-options="required:true">
						<#list allGenders as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">生肖</label>
				<div class="col-sm-9">
					<select name="animal" class="form-control select2bs4" >
						<#list allAnimals as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">姓名</label>
				<div class="col-sm-9">
					<input type="text" name="realName" placeholder="请输入姓名..." class="easyui-validatebox form-control" data-options="validType:['chinese','length[1,16]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">证件类型</label>
				<div class="col-sm-9">
					<select name="idcardType" class="form-control select2bs4" data-options="required:true">
						<#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">身份证号码</label>
				<div class="col-sm-9">
					<input type="text" name="idcardNo" placeholder="请输入身份证号码..." class="easyui-validatebox form-control" data-inputmask="'alias':'idcard'" data-mask data-options="validType:['idcard','length[1,48]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">手机号码</label>
				<div class="col-sm-9">
					<input type="text" name="mobileNo" placeholder="请输入手机号码..." class="easyui-validatebox form-control" data-inputmask="'alias':'mobile'" data-mask data-options="validType:['mobile','length[1,11]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">邮件</label>
				<div class="col-sm-9">
					<input type="text" name="mail" placeholder="请输入邮件..." class="easyui-validatebox form-control" data-inputmask="'alias':'email'" data-mask data-options="validType:['email','length[1,64]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">客户类型</label>
				<div class="col-sm-9">
					<select name="customerType" class="form-control select2bs4" >
						<#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">摘要</label>
				<div class="col-sm-9">
					<input type="text" name="subject" placeholder="请输入摘要..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,128]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">详情</label>
				<div class="col-sm-9">
					<textarea rows="3" cols="40" placeholder="请输入详情..." name="content" class="easyui-validatebox form-control" ></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">完成度</label>
				<div class="col-sm-9">
					<input type="text" name="doneRatio" placeholder="请输入完成度..." class="easyui-validatebox form-control" data-inputmask="'alias':'percent'" data-mask data-options="validType:['percent']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">薪水</label>
				<div class="col-sm-9">
					<input type="text" name="salary" placeholder="请输入薪水..." class="easyui-validatebox form-control" data-inputmask="'alias':'money','min':0,'max':999999999" data-mask data-options="validType:['money[0,999999999]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">注册渠道</label>
				<div class="col-sm-9">
					<select name="registryChannel" class="form-control select2bs4" >
						<#list allRegistryChannels as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">推送广告</label>
				<div class="col-sm-9">
					<select name="pushAdv" class="form-control select2bs4" >
						<#list allPushAdvs as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">数字类型</label>
				<div class="col-sm-9">
					<select name="numStatus" class="form-control select2bs4" >
						<#list allNumStatuss as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">状态</label>
				<div class="col-sm-9">
					<select name="status" class="form-control select2bs4" data-options="required:true">
						<#list allStatuss as k,v><option value="${k}">${v}</option></#list>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">备注</label>
				<div class="col-sm-9">
					<textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox form-control" ></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">网址</label>
				<div class="col-sm-9">
					<input type="text" name="website" placeholder="请输入网址..." class="easyui-validatebox form-control" data-inputmask="'alias':'url'" data-mask data-options="validType:['url','length[1,128]']"/>
				</div>
			</div>
        </div>
      </@jodd.form>
    </form>
</div>
