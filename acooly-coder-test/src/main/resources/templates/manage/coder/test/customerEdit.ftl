<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_customer_editform" action="/manage/coder/test/customer/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="customer" scope="request">
        <input name="id" type="hidden" />
        <table class="editForm" width="100%">
			<tr>
				<th width="25%">用户名：</th>
				<td><input type="text" name="username" placeholder="请输入用户名..." class="easyui-validatebox" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>					
			<tr>
				<th>年龄：</th>
				<td><input type="text" name="age" placeholder="请输入年龄..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['length[1,10]']"/></td>
			</tr>					
			<tr>
				<th>生日：</th>
				<td><input type="text" name="birthday" placeholder="请输入生日..." class="easyui-validatebox" value="<#if customer.birthday??>${customer.birthday?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>性别：</th>
				<td><select name="gender" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allGenders as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>姓名：</th>
				<td><input type="text" name="realName" placeholder="请输入姓名..." class="easyui-validatebox" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>					
			<tr>
				<th>证件类型：</th>
				<td><select name="idcardType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>身份证号码：</th>
				<td><input type="text" name="idcardNo" placeholder="请输入身份证号码..." class="easyui-validatebox" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>手机号码：</th>
				<td><input type="text" name="mobileNo" placeholder="请输入手机号码..." class="easyui-validatebox" data-options="validType:['length[1,24]']"/></td>
			</tr>					
			<tr>
				<th>邮件：</th>
				<td><input type="text" name="mail" placeholder="请输入邮件..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>摘要：</th>
				<td><input type="text" name="subject" placeholder="请输入摘要..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>客户类型：</th>
				<td><select name="customerType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" >
					<#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>手续费：</th>
				<td><input type="text" name="fee" placeholder="请输入手续费..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['length[1,12]']"/></td>
			</tr>					
			<tr>
				<th>状态：</th>
				<td><select name="status" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allStatuss as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,4000]']"></textarea></td>
			</tr>					
			<tr>
				<th>薪水：</th>
				<td><input type="text" name="salary" placeholder="请输入薪水..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['length[1,19]']"/></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
