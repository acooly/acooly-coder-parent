<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_partner_editform" action="/manage/test/partner/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="partner" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">合作方编码：</th>
				<td><input type="text" name="partnerId" placeholder="请输入合作方编码..." class="easyui-validatebox" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>合作方名称：</th>
				<td><input type="text" name="partnerName" placeholder="请输入合作方名称..." class="easyui-validatebox" data-options="validType:['length[1,32]'],required:true"/></td>
			</tr>					
			<tr>
				<th>安全方案：</th>
				<td><select name="secretType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allSecretTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>签名类型：</th>
				<td><select name="signType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allSignTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>秘钥：</th>
				<td><input type="text" name="secretKey" placeholder="请输入秘钥..." class="easyui-validatebox" data-options="validType:['length[1,45]'],required:true"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
