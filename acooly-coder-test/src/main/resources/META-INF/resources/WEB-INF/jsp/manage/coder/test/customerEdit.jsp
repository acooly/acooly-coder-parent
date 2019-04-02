<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/manage/common/taglibs.jsp"%>
<div>
    <form id="manage_customer_editform" action="${pageContext.request.contextPath}/manage/coder/test/customer/${action=='create'?'saveJson':'updateJson'}.html" method="post">
      <jodd:form bean="customer" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">用户名：</th>
				<td><input type="text" name="username" size="48" placeholder="请输入用户名..." class="easyui-validatebox text" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>					
			<tr>
				<th>年龄：</th>
				<td><input type="text" name="age" size="48" placeholder="请输入年龄..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,10]']"/></td>
			</tr>					
			<tr>
				<th>生日：</th>
				<td><input type="text" name="birthday" size="20" placeholder="请输入生日..." class="easyui-validatebox text" value="<fmt:formatDate value="${customer.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>性别：</th>
				<td><select name="gender" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allGenders}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>姓名：</th>
				<td><input type="text" name="realName" size="48" placeholder="请输入姓名..." class="easyui-validatebox text" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>					
			<tr>
				<th>证件类型：</th>
				<td><select name="idcardType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allIdcardTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>身份证号码：</th>
				<td><input type="text" name="idcardNo" size="48" placeholder="请输入身份证号码..." class="easyui-validatebox text" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>手机号码：</th>
				<td><input type="text" name="mobileNo" size="48" placeholder="请输入手机号码..." class="easyui-validatebox text" data-options="validType:['length[1,24]']"/></td>
			</tr>					
			<tr>
				<th>邮件：</th>
				<td><input type="text" name="mail" size="48" placeholder="请输入邮件..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>摘要：</th>
				<td><input type="text" name="subject" size="48" placeholder="请输入摘要..." class="easyui-validatebox text" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>客户类型：</th>
				<td><select name="customerType" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" >
					<c:forEach items="${allCustomerTypes}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>手续费：</th>
				<td><input type="text" name="fee" size="48" placeholder="请输入手续费..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,12]']"/></td>
			</tr>					
			<tr>
				<th>状态：</th>
				<td><select name="status" editable="false" style="height:27px;" panelHeight="auto" class="easyui-combobox" data-options="required:true">
					<c:forEach items="${allStatuss}" var="e">
						<option value="${e.key}">${e.value}</option>
					</c:forEach>
				</select></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." style="width:300px;" name="comments" class="easyui-validatebox" data-options="validType:['length[1,4000]']"></textarea></td>
			</tr>					
			<tr>
				<th>薪水：</th>
				<td><input type="text" name="salary" size="48" placeholder="请输入薪水..." style="height: 27px;line-height: 27px;" class="easyui-numberbox text" data-options="validType:['length[1,19]']"/></td>
			</tr>					
        </table>
      </jodd:form>
    </form>
</div>
