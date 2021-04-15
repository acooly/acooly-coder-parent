<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_metaService_editform" action="/manage/test/metaService/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="metaService" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">服务名称：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务名称..." name="serviceName" class="easyui-validatebox" data-options="validType:['length[1,128]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>版本号：</th>
				<td><input type="text" name="version" placeholder="请输入版本号..." class="easyui-validatebox" data-options="validType:['length[1,32]']"/></td>
			</tr>					
			<tr>
				<th>服务描述：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务描述..." name="serviceDesc" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>服务响应类型：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务响应类型..." name="responseType" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>所属系统：</th>
				<td><input type="text" name="owner" placeholder="请输入所属系统..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>服务名称：</th>
				<td><input type="text" name="busiType" placeholder="请输入服务名称..." class="easyui-validatebox" data-options="validType:['length[1,64]']"/></td>
			</tr>					
			<tr>
				<th>服务介绍：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务介绍..." name="note" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>服务类：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务类..." name="serviceClass" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>请求类：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入请求类..." name="requestClass" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>同步响应类：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入同步响应类..." name="responseClass" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
			<tr>
				<th>异步响应类：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入异步响应类..." name="notifyClass" class="easyui-validatebox" data-options="validType:['length[1,128]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
