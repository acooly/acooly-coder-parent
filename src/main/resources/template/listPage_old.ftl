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
<script language="javascript">
	function delete_form(action) {
		if (confirm("是否确定删除?")) {
			$('ec').action = action + '&autoInc=false';
			$('ec').submit();
		}
	}
	function change_form(action) {
		$('ec').action = action + '&autoInc=false';
		$('ec').submit();
	}
</script>
</head>

<body class="mainframe">
	<div class="maintitle">
		<img src="/admin/images/icon/path.gif" align="absmiddle">&nbsp;
		<framwork:navigation url="${configuration.pagePath}/${names.domainClassName?uncap_first}/index.mvc" />
	</div>
	<div id="ADcon0" class="maintable">
		<div class="marntable_table">
			<table width="100%">
				<tr>
					<td class="toolbar">
						<form:form id="queryform" method="post" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/index.mvc">
							<img src="/admin/images/icon/menu8.gif" align="absmiddle">&nbsp; 
					<#list table.columnMetadatas as entity>
						<#if entity.name?lower_case != 'id'>
						<#if entity.dataType == 2>
							${entity.common}:<input class='inputText' size="12" type='text' name='search_GTE_${entity.propertyName}' id='search_GTE_${entity.propertyName}' readonly='readonly'/><img id='calButton_GTE_${entity.propertyName}' src='<c:url value='/admin/scripts/calendar/skins/aqua/cal.gif'/>' border='0' alt='选择日期' style='cursor: pointer;'/><script type='text/javascript'>new calendar('search_GTE_${entity.propertyName}', 'calButton_GTE_${entity.propertyName}', '%Y-%m-%d');</script>
							到:<input class='inputText' size="12" type='text' name='search_LTE_${entity.propertyName}' id='search_LTE_${entity.propertyName}' readonly='readonly'/><img id='calButton_LTE_${entity.propertyName}' src='<c:url value='/admin/scripts/calendar/skins/aqua/cal.gif'/>' border='0' alt='选择日期' style='cursor: pointer;'/><script type='text/javascript'>new calendar('search_LTE_${entity.propertyName}', 'calButton_LTE_${entity.propertyName}', '%Y-%m-%d');</script>
						<#elseif entity.dataType == 1>
							<#if entity.options??>
							${entity.common}:<select name="search_EQ_${entity.propertyName}"><option value="">所有</option><c:forEach var="e" items="${r"${"}all${entity.propertyName?cap_first}s}"><option value="${r"${"}e.key}" ${r"${"}param.search_LIKE_${entity.propertyName} == e.key?'selected':''}>${r"${"}e.value}</option></c:forEach></select>
							<#else>
							${entity.common}:<input type="text" name="search_EQ_${entity.propertyName}" value="${r"${"}param.search_EQ_${entity.propertyName}}"  />
							</#if>
						<#else>	
							${entity.common}:<input type="text" name="search_LIKE_${entity.propertyName}" value="${r"${"}param.search_LIKE_${entity.propertyName}}"  />
						</#if>								
						</#if>
					</#list>
							<input name="Submit" type="Submit" class="inputSearch" value="查询" border="0"> &nbsp;
							<input name="Submit" type="button" value="添加" onClick="location.href='${configuration.pagePath}/${names.domainClassName?uncap_first}/create.mvc';">
						</form:form>
					</td>
				</tr>
			</table>		
			<ec:table items="${names.domainClassName?uncap_first}s" var="e" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/index.mvc" rowsDisplayed="15" retrieveRowsCallback="limit">
				<ec:row>
				<#list table.columnMetadatas as entity>
				<#if entity.dataType == 2>
					<ec:column property="${entity.propertyName}" title="${entity.common}" cell="date" format="yyyy-MM-dd" />
				<#elseif entity.dataType == 1>
					<#if entity.options??>
					<ec:column property="null" title="${entity.common}" style="width:10%">${r"${"}all${entity.propertyName?cap_first}s[e.${entity.propertyName}]}</ec:column>
					<#else>
					<ec:column property="${entity.propertyName}" title="${entity.common}" />
					</#if>
				<#else>	
					<ec:column property="${entity.propertyName}" title="${entity.common}" />
				</#if>					
				</#list>
					<ec:column property="null" title="操作" style="15%" sortable="false">
						<A href="javascript:change_form('${configuration.pagePath}/${names.domainClassName?uncap_first}/edit.mvc?id=${r"${"}e.id}')" onclick="this.blur();">修改</A>&nbsp;&nbsp;
					   <A href="javascript:delete_form('${configuration.pagePath}/${names.domainClassName?uncap_first}/remove.mvc?id=${r"${"}e.id}')" onclick="this.blur();">删除</A>
					</ec:column>
				</ec:row>
			</ec:table>			
		</div>
	</div>
	<%@ include file="/admin/common/messages.jsp"%>
</body>
</html>
