<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docItem_editform" action="/manage/api/docItem/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docItem" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th width="25%">parent_id：</th>
				<td><input type="text" name="parentId" placeholder="请输入parent_id..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
			<tr>
				<th>字段编号：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入字段编号..." name="itemNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>字段名称：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入字段名称..." name="name" class="easyui-validatebox" data-options="validType:['length[1,128]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>字段标题：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入字段标题..." name="title" class="easyui-validatebox" data-options="validType:['length[1,128]'],required:true"></textarea></td>
			</tr>					
			<tr>
				<th>字段长度：</th>
				<td><input type="text" name="dataLength" placeholder="请输入字段长度..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']"/></td>
			</tr>					
			<tr>
				<th>数据类型：</th>
				<td><input type="text" name="dataType" placeholder="请输入数据类型..." class="easyui-validatebox" data-options="validType:['length[1,16]'],required:true"/></td>
			</tr>					
			<tr>
				<th>字段描述：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入字段描述..." name="descn" class="easyui-validatebox" data-options="validType:['length[1,1024]']"></textarea></td>
			</tr>					
			<tr>
				<th>字段示例：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入字段示例..." name="demo" class="easyui-validatebox" data-options="validType:['length[1,1024]']"></textarea></td>
			</tr>					
			<tr>
				<th>可选状态：</th>
				<td><input type="text" name="status" placeholder="请输入可选状态..." class="easyui-validatebox" data-options="validType:['length[1,16]']"/></td>
			</tr>					
			<tr>
				<th>是否加密：</th>
				<td><select name="encryptstatus" editable="false" panelHeight="auto" class="easyui-combobox" style="min-width: 235px;" >
					<#list allEncryptstatuss as k,v><option value="${k}">${v}</option></#list>
				</select></td>
			</tr>					
			<tr>
				<th>备注：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>max：</th>
				<td><input type="text" name="max" placeholder="请输入max..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']"/></td>
			</tr>					
			<tr>
				<th>message_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入message_no..." name="messageNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>min：</th>
				<td><input type="text" name="min" placeholder="请输入min..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']"/></td>
			</tr>					
			<tr>
				<th>parent_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入parent_no..." name="parentNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
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
