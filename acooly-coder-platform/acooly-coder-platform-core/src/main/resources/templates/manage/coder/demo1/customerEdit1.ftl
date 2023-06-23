<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_customer_editform" action="/manage/coder/demo1/customer/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="customer" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">用户名：</th>
				<td><input type="text" name="username" placeholder="请输入用户名..." class="easyui-validatebox" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>
			<tr>
				<th>生日：</th>
				<td><input type="text" name="birthday" placeholder="请输入生日..." class="easyui-validatebox" value="<#if customer.birthday??>${customer.birthday?string('yyyy-MM-dd')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"  /></td>
			</tr>
			<tr>
				<th>年龄：</th>
				<td><input type="text" name="age" placeholder="请输入年龄..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,127]']"/></td>
			</tr>
			<tr>
				<th>性别：</th>
				<td><input type="text" name="gender" placeholder="请输入性别..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
			</tr>
			<tr>
				<th>生肖：</th>
				<td><input type="text" name="animal" placeholder="请输入生肖..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
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
				<th>身份证号码：</th>
				<td><input type="text" name="idcardNo" placeholder="请输入身份证号码..." class="easyui-validatebox" data-options="validType:['length[1,48]'],required:true"/></td>
			</tr>
			<tr>
				<th>银行卡卡号：</th>
				<td><input type="text" name="bankCardNo" placeholder="请输入银行卡卡号..." class="easyui-validatebox" data-options="validType:['length[1,48]'],required:true"/></td>
			</tr>
			<tr>
				<th>手机号码：</th>
				<td><input type="text" name="mobileNo" placeholder="请输入手机号码..." class="easyui-validatebox" data-options="validType:['length[1,11]']"/></td>
			</tr>
			<tr>
				<th>邮件：</th>
				<td><input type="text" name="mail" placeholder="请输入邮件..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>
			<tr>
				<th>客户类型：</th>
				<td><select name="customerType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" >
					<#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>
			<tr>
				<th>摘要：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入摘要..." name="subject" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>
			<tr>
				<th>详情：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入详情..." name="content" class="easyui-validatebox" data-options="validType:['length[1,999999999]']"></textarea></td>
			</tr>
			<tr>
				<th>完成度：</th>
				<td><input type="text" name="doneRatio" placeholder="请输入完成度..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']"/></td>
			</tr>
			<tr>
				<th>付款率：</th>
				<td><input type="text" name="payRate" placeholder="请输入付款率..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>
			<tr>
				<th>薪水：</th>
				<td><input type="text" name="salary" placeholder="请输入薪水..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']"/></td>
			</tr>
			<tr>
				<th>注册渠道：</th>
				<td><input type="text" name="registryChannel" placeholder="请输入注册渠道..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
			</tr>
			<tr>
				<th>推送广告：</th>
				<td><input type="text" name="pushAdv" placeholder="请输入推送广告..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
			</tr>
			<tr>
				<th>数字类型：</th>
				<td><select name="numStatus" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" >
					<#list allNumStatuss as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>
			<tr>
				<th>网址：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入网址..." name="website" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>
			<tr>
				<th>照片：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入照片..." name="photoPath" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>
			<tr>
				<th>状态：</th>
				<td><input type="text" name="status" placeholder="请输入状态..." class="easyui-validatebox" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>
        </table>
      </@jodd.form>
    </form>
</div>
