<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/admin/style/script/style.js" type="text/javascript"></script>
<%@ include file="/admin/common/includeCssStyle.jsp"%>
<%@ include file="/admin/scripts/calendar/calendar.jsp"%>
<script type="text/javascript" src="<c:url value="/admin/scripts/prototype.js"/>"></script>
<script type="text/javascript" src="<c:url value="/admin/scripts/common.js"/>"></script>
<script language="JavaScript">
	function checkSubmit() {
	<#list table.columnMetadatas as entity>
	<#if entity.name?lower_case != 'id' && !entity.nullable>
		<#if entity.dataType == 2>
		if ($F("${entity.propertyName}") == "") { alert("[${entity.common}]不能为空"); return false;}
		<#else>
		if ($F("${entity.propertyName}") == "" || ByteLength($F("${entity.propertyName}")) > ${entity.length}) { alert("[${entity.common}]不能为空和长度不能超过${entity.length}字符"); $("${entity.propertyName}").focus(); return false;}
		</#if>
	</#if>
	</#list>
		return true;
	}
</script>
</head>


<body class="mainframe">
	<div class="maintitle">
		<img src="/admin/images/icon/path.gif" align="absmiddle">&nbsp;
		<framwork:navigation url="${configuration.pagePath}/${names.domainClassName?uncap_first}/index.mvc" />
	</div>
	<div>
		<ul class="maintab">
			<li class="ADmenuOn" id="ADm0" onmouseover="ADMenu(0);"><img src="/admin/images/icon/menu18.gif" align="absmiddle">
				${r"${"}action=='craete'?'添加':'修改'}${table.comment}</li>
		</ul>
	</div>
	
	<form id="${names.domainClassName?uncap_first}Form" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/${r"${"}action=='create'?'save':'update'}.mvc" method="post">
		<jodd:form bean="${names.domainClassName?uncap_first}" scope="request">	
		<input type="hidden" value="${r"${"}${names.domainClassName?uncap_first}.id}" name="id" />
		<input type="hidden" value="<%=request.getAttribute("ec_p")%>" name="ec_p" />
		<div class="ctrltable" id="ADcon0">
			<div class="ctrltitle">${table.comment}详细资料</div>
			<div class="ctrlform">
				<table>
				<#list table.columnMetadatas as entity>
					<#if entity.name?lower_case != 'id'>
					<tr>
						<th style="width: 200px"><#if !entity.nullable><span>*</span></#if>${entity.common}：</th>
					<#if entity.dataType == 2>
						<td><input type="text" id="${entity.propertyName}" name="${entity.propertyName}" readonly="readonly" /><img id='calButton_${entity.propertyName}' src='<c:url value='/admin/scripts/calendar/skins/aqua/cal.gif'/>' border='0' alt='选择日期' style='cursor: pointer;'/><script type='text/javascript'>new calendar('${entity.propertyName}', 'calButton_${entity.propertyName}', '%Y-%m-%d');</script></td>
					<#elseif entity.dataType == 1 && entity.options??>
						<td>
							<select id="${entity.propertyName}" name="${entity.propertyName}">
									<c:forEach items="${r"${"}all${entity.propertyName?cap_first}s}" var="e">
										<option value="${r"${"}e.key}">${r"${"}e.value}</option>
									</c:forEach>
							</select>
						</td>			
					<#else>	
						<td><input type="text" id="${entity.propertyName}" name="${entity.propertyName}" /></td>
					</#if>
					</tr>					
					</#if>
				</#list>				
					<tr>
						<th></th>
						<td>
							<img src="/admin/images/icon/menu12.gif" align="absmiddle"> 
							<input type="submit" value="确 定" onclick="return checkSubmit()" />&nbsp;&nbsp; 
							<img src="/admin/images/icon/menu13.gif" align="absmiddle"> <input name="" type="button" value="返 回" onclick="history.back();" border="0">						
						</td>
					</tr>
				</table>
			</div>
		</div>
		</jodd:form>
	</form>
	<%@ include file="/admin/common/messages.jsp"%>
</body>
</html>
