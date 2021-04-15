<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docScheme_editform" action="/manage/api/docScheme/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docScheme" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>author：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入author..." name="author" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>note：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入note..." name="note" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>scheme_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入scheme_no..." name="schemeNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>scheme_type：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入scheme_type..." name="schemeType" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>sort_time：</th>
				<td><input type="text" name="sortTime" placeholder="请输入sort_time..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
			<tr>
				<th>title：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入title..." name="title" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
