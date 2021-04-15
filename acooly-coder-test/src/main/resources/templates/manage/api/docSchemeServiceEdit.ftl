<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docSchemeService_editform" action="/manage/api/docSchemeService/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docSchemeService" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>comments：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入comments..." name="comments" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>scheme_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入scheme_no..." name="schemeNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>service_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入service_no..." name="serviceNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>sort_time：</th>
				<td><input type="text" name="sortTime" placeholder="请输入sort_time..." class="easyui-numberbox" style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']"/></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
