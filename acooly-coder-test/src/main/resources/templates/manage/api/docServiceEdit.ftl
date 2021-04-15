<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docService_editform" action="/manage/api/docService/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docService" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>busi_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入busi_type..." name="busiType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>manual_note：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入manual_note..." name="manualNote" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>name：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入name..." name="name" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>服务介绍：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入服务介绍..." name="note" class="easyui-validatebox" data-options="validType:['length[1,4000]']"></textarea></td>
			</tr>					
			<tr>
				<th>owner：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入owner..." name="owner" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>service_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入service_no..." name="serviceNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>service_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入service_type..." name="serviceType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>signature：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入signature..." name="signature" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>sort_time：</th>
				<td><input type="text" name="sortTime" placeholder="请输入sort_time..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
			<tr>
				<th>title：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入title..." name="title" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>version：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入version..." name="version" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
