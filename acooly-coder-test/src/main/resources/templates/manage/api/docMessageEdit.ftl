<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docMessage_editform" action="/manage/api/docMessage/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docMessage" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>manual_note：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入manual_note..." name="manualNote" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>message_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入message_no..." name="messageNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>message_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入message_type..." name="messageType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>note：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入note..." name="note" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>service_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入service_no..." name="serviceNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>signatrue：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入signatrue..." name="signatrue" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>sort_time：</th>
				<td><input type="text" name="sortTime" placeholder="请输入sort_time..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
