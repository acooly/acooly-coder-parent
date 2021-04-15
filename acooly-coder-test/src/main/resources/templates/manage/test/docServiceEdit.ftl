<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docService_editform" action="/manage/test/docService/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docService" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">服务编号：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务编号..." name="serviceNo" class="easyui-validatebox" data-options="validType:['length[1,255]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>服务名称：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务名称..." name="name" class="easyui-validatebox" data-options="validType:['length[1,128]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>服务版本：</th>
				<td><input type="text" name="version" placeholder="请输入服务版本..." class="easyui-validatebox" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>服务标题：</th>
				<td><input type="text" name="title" placeholder="请输入服务标题..." class="easyui-validatebox" data-options="validType:['length[1,64]'],required:true"/></td>
			</tr>					
			<tr>
				<th>所属系统：</th>
				<td><input type="text" name="owner" placeholder="请输入所属系统..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>服务说明：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务说明..." name="note" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
			</tr>					
			<tr>
				<th>手工说明：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入手工说明..." name="manualNote" class="easyui-validatebox" data-options="validType:['length[1,512]']"></textarea></td>
			</tr>					
			<tr>
				<th>服务类型：</th>
				<td><select name="serviceType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" data-options="required:true">
					<#list allServiceTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>业务类型：</th>
				<td><select name="busiType" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" >
					<#list allBusiTypes as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>排序值：</th>
				<td><input type="text" name="sortTime" placeholder="请输入排序值..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>签名：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入签名..." name="signature" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
