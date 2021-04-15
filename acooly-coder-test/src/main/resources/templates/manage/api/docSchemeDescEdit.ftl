<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_docSchemeDesc_editform" action="/manage/api/docSchemeDesc/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
		<@jodd.form bean="docSchemeDesc" scope="request">
        <input name="id" type="hidden" />
        <table class="tableForm" width="100%">
			<tr>
				<th>scheme_desc：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入scheme_desc..." name="schemeDesc" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
			<tr>
				<th>scheme_no：</th>
				<td><textarea rows="3" cols="40" placeholder="请输入scheme_no..." name="schemeNo" class="easyui-validatebox" data-options="validType:['length[1,255]']"></textarea></td>
			</tr>					
        </table>
      </@jodd.form>
    </form>
</div>
