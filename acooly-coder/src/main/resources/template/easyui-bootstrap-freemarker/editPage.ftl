<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
<#assign entityContextPath="${configuration.managePath}/${nameScheme.domainClassName?uncap_first}" />
<${r"#"}assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_${entityVariable}_editform" class="form-horizontal" action="${entityContextPath}/<${r"#"}if action=='create'>saveJson<${r"#"}else>updateJson</${r"#"}if>.html" method="post" <#if data.includeFile>enctype="multipart/form-data"</#if>>
		<${r"@"}jodd.form bean="${entityVariable}" scope="request">
        <input name="id" type="hidden" />
		<div class="card-body">
		<#list table.columns as entity>
		<#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
			<div class="form-group row">
				<#if entity.tip??>
				<label class="col-sm-3 col-form-label">${entity.common} <a href="javascript:;" data-toggle="tooltip" data-placement="right" title="${entity.tip}"><i class="fa fa-info-circle" aria-hidden="true"></i></a></label>
				<#else>
				<label class="col-sm-3 col-form-label">${entity.common}</label>
				</#if>
				<div class="col-sm-9">
					<#if entity.options?? || entity.dataType.enum>
					<#--下拉选项-->
					<select name="${entity.propertyName}" class="form-control select2bs4" <#if !entity.nullable>data-options="required:true"</#if>>
						<${r"#"}list all${entity.propertyName?cap_first}s as k,v><option value="${r"${"}k}">${r"${"}v}</option></${r"#"}list>
					</select>
					<#elseif entity.dataType.date || entity.dataType.dateTime>
					<#--日期或日期时间-->
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" value="<${r"#"}if ${entityVariable}.${entity.propertyName}??>${r"${"}${entityVariable}.${entity.propertyName}?string('yyyy-MM-dd<#if entity.dataType.dateTime> HH:mm:ss</#if>')}</${r"#"}if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd<#if entity.dataType.dateTime> HH:mm:ss</#if>'})" onblur="$(this).validatebox('isValid');" <#if !entity.nullable>data-options="required:true"</#if> />
					<#elseif entity.dataType.number>
					<#--数字类型-->
					<#if entity.columnType == 'money'>
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-inputmask="'alias':'money','min':0,'max':${entity.length?c}" data-mask data-options="validType:['money[0,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/>
					<#elseif entity.columnType == 'percent'>
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-inputmask="'alias':'percent'" data-mask data-options="validType:['percent']<#if !entity.nullable>,required:true</#if>"/>
					<#elseif entity.columnType == 'centPercent'>
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-inputmask="'alias':'centPercent'" data-mask data-options="validType:['percent']<#if !entity.nullable>,required:true</#if>"/>
					<#else>
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-options="validType:['number[0,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/>
					</#if>
					<#elseif entity.dataType.string>
					<#--文本类型-->
					<#if entity.length gt 128>
					<textarea rows="3" cols="40" placeholder="请输入${entity.common}..." name="${entity.propertyName}" class="easyui-validatebox form-control" <#if !entity.nullable>data-options="required:true"</#if>></textarea>
					<#else>
					<#-- 针对自定义类型进行分类处理 -->
					<#if entity.columnType=='chinese'>
                    <#-- 中文 -->
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-options="validType:['chinese','length[1,${entity.length?c}]']"<#if !entity.nullable> required="true"</#if>/>
                    <#elseif entity.columnType=='file'>
                    <#-- 文件上传 -->
						<${r"#"}if ${entityVariable} != null && ${entityVariable}.${entity.propertyName} != ''>
							<div class="row col-form-content">
								<div class="col-sm-6"><a href="javascript:;" onclick="$.acooly.file.play('${r"$"}{serverRoot}${r"$"}{${entityVariable}.${entity.propertyName}}');">查看</a></div>
								<div class="col-sm-6" style="text-align: right;"><a href="javascript:;" onclick="$('#manage_${entityVariable}_${entity.propertyName}_container').toggle();">重新上传</a></div>
							</div>
						</${r"#"}if>
						<div class="custom-file" id="manage_${entityVariable}_${entity.propertyName}_container" <${r"#"}if ${entityVariable} != null && ${entityVariable}.${entity.propertyName} != ''>style="display: none;"</${r"#"}if>>
							<input type="file" name="${entity.propertyName}_uploadFile" class="custom-file-input">
							<label class="custom-file-label">请选择上传的文件</label>
						</div>
                    <#else>
					<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" <#if entity.columnType != 'text' && entity.columnType != 'option'>data-inputmask="'alias':'${entity.columnType}'" data-mask</#if> data-options="validType:['${entity.columnType}','length[1,<#if entity.length?? && entity.length gt 0>${entity.length?c}<#else>999999999</#if>]']"<#if !entity.nullable> required="true"</#if>/>
					</#if>
					</#if>
					<#else>
						<input type="text" name="${entity.propertyName}" placeholder="请输入${entity.common}..." class="easyui-validatebox form-control" data-options="validType:['length[0,${entity.length?c}]']<#if !entity.nullable>,required:true</#if>"/>
					</#if>
				</div>
			</div>
		</#if>
		</#list>
        </div>
      </${r"@"}jodd.form>
    </form>
</div>
