<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_orderInfo_editform" action="/manage/test/orderInfo/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="orderInfo" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">访问key：</th>
				<td><input type="text" name="accessKey" placeholder="请输入访问key..." class="easyui-validatebox" data-options="validType:['length[1,40]'],required:true"/></td>
			</tr>					
			<tr>
				<th>商户ID：</th>
				<td><input type="text" name="partnerId" placeholder="请输入商户ID..." class="easyui-validatebox" data-options="validType:['length[1,40]'],required:true"/></td>
			</tr>					
			<tr>
				<th>统一流水：</th>
				<td><input type="text" name="gid" placeholder="请输入统一流水..." class="easyui-validatebox" data-options="validType:['length[1,40]'],required:true"/></td>
			</tr>					
			<tr>
				<th>请求号：</th>
				<td><input type="text" name="requestNo" placeholder="请输入请求号..." class="easyui-validatebox" data-options="validType:['length[1,40]'],required:true"/></td>
			</tr>					
			<tr>
				<th>服务名：</th>
				<td><input type="text" name="service" placeholder="请输入服务名..." class="easyui-validatebox" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>版本号：</th>
				<td><input type="text" name="version" placeholder="请输入版本号..." class="easyui-validatebox" data-options="validType:['length[1,8]'],required:true"/></td>
			</tr>					
			<tr>
				<th>签名类型：</th>
				<td><input type="text" name="signType" placeholder="请输入签名类型..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
			</tr>					
			<tr>
				<th>协议：</th>
				<td><input type="text" name="protocol" placeholder="请输入协议..." class="easyui-validatebox" data-options="validType:['length[1,40]']"/></td>
			</tr>					
			<tr>
				<th>通知地址：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入通知地址..." name="notifyUrl" class="easyui-validatebox" data-options="validType:['length[1,256]']"></textarea></td>
			</tr>					
			<tr>
				<th>返回地址：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入返回地址..." name="returnUrl" class="easyui-validatebox" data-options="validType:['length[1,256]']"></textarea></td>
			</tr>					
			<tr>
				<th>扩展信息：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入扩展信息..." name="businessInfo" class="easyui-validatebox" data-options="validType:['length[1,1024]']"></textarea></td>
			</tr>					
			<tr>
				<th>会话信息：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入会话信息..." name="context" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>创建时间：</th>
				<td><input type="text" name="rawAddTime" placeholder="请输入创建时间..." class="easyui-validatebox" value="<#if orderInfo.rawAddTime??>${orderInfo.rawAddTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
			<tr>
				<th>修改时间：</th>
				<td><input type="text" name="rawUpdateTime" placeholder="请输入修改时间..." class="easyui-validatebox" value="<#if orderInfo.rawUpdateTime??>${orderInfo.rawUpdateTime?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" /></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
