<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_auth_editform" action="/manage/api/auth/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="auth" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">访问帐号：</th>
				<td><input type="text" name="accessKey" placeholder="请输入访问帐号..." class="easyui-validatebox" data-options="validType:['length[1,45]'],required:true"/></td>
			</tr>					
			<tr>
				<th>访问秘钥：</th>
				<td><input type="text" name="secretKey" placeholder="请输入访问秘钥..." class="easyui-validatebox" data-options="validType:['length[1,45]'],required:true"/></td>
			</tr>					
			<tr>
				<th>访问权限：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入访问权限..." name="permissions" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
