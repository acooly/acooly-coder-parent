<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/admin/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/admin/style/script/style.js" type="text/javascript"></script>
<%@ include file="/admin/common/includeCssStyle.jsp"%>
<%@ include file="/admin/scripts/calendar/calendar.jsp"%>

<link rel="stylesheet" type="text/css" href="/admin/plugins/jquery-easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="/admin/plugins/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="/admin/plugins/jquery-easyui/jquery-1.6.min.js"></script>
<script type="text/javascript" src="/admin/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/admin/plugins/jquery-easyui/plugins/jquery.toolbar.js"></script>
<script type="text/javascript" src="/admin/plugins/jquery-easyui/plugins/jquery.window.js"></script>

<script language="javascript">
	$(function() {
		var $win;
		$win = $('#importWindow').window({ title : ' &nbsp;批量导入', width : 450, height : 252,
			top : ($(window).height() - 450) * 0.5, left : ($(window).width() - 252) * 0.5,
			shadow : true, modal : true, iconCls : 'icon-import', closed : true, 
			minimizable : false, maximizable : false, collapsible : false
		});
	});
	
	var contextPath = "${configuration.pagePath}/${names.domainClassName?uncap_first}";
	
	function delete_form(action) {
		if (confirm("是否确定删除?")) {
			$('#ec').attr("action", action + '&autoInc=false');
			$('#ec').submit();
		}
	}
	function change_form(action) {
		$('#ec').attr("action", action + '&autoInc=false');
		$('#ec').submit();
	}

	function submitExportExcel() {
		if (confirm("确定导出Excel?")) {
			$('#queryform').attr("action",contextPath+"/exportXls.mvc");
			$("#queryform").submit();
		}
	}
	function submitExportCsv() {
		if (confirm("确定导出CSV?")) {
			$('#queryform').attr("action",contextPath+"/exportCsv.mvc");
			$("#queryform").submit();
		}
	}
	function submitQuery() {
		$('#queryform').attr("action", contextPath+"/index.mvc");
		$("#queryform").submit();
	}

	function submitRemoves() {
		var ids = document.getElementsByName('id');
		var s = '';
		for ( var i = 0; i < ids.length; i++) {
			if (ids[i].checked)
				s += ids[i].value + ','; //如果选中，将value添加到变量s中
		}
		if (s == '') {
			alert("请先选择需要删除的数据");
			return;
		}
		if (confirm("是否确定删除选中的数据?")) {
			$('#ec').attr("action", contextPath+"/removes.mvc");
			$("#ec").submit();
		}
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
		            <div class="easyui-toolbar">
		              <a id="add" href="#" onclick="location.href='${configuration.pagePath}/${names.domainClassName?uncap_first}/create.mvc';" iconCls="icon-add"> 添加 </a> 
		              <a href="#" onclick="submitRemoves()" iconCls="icon-remove"> 批量删除 </a> 
		              <a href="#" onclick="submitExportExcel()" iconCls="icon-excel"> 导出Excel </a> 
		              <a href="#" onclick="submitExportCsv()" iconCls="icon-csv"> 导出CSV </a> 
		              <a href="#" onclick="$('#importWindow').window('open')" iconCls="icon-import"> 批量导入(Excel/CSV) </a>
		            </div>
		          </td>
		        </tr>			
				<tr>
					<td class="toolbar">
						<form:form id="queryform" method="post" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/index.mvc">
							<img src="/admin/images/icon/menu8.gif" align="absmiddle">&nbsp; 
					<#list table.columnMetadatas as entity>
						<#if entity.name?lower_case != 'id'>
						<#if entity.dataType == 2>
							${entity.common}:<input class='inputText' size="12" type='text' name='search_GTE_${entity.propertyName}' id='search_GTE_${entity.propertyName}' value="${r"${"}param.search_GTE_${entity.propertyName}}" readonly='readonly'/><img id='calButton_GTE_${entity.propertyName}' src='<c:url value='/admin/scripts/calendar/skins/aqua/cal.gif'/>' border='0' alt='选择日期' style='cursor: pointer;'/><script type='text/javascript'>new calendar('search_GTE_${entity.propertyName}', 'calButton_GTE_${entity.propertyName}', '%Y-%m-%d');</script>
							到:<input class='inputText' size="12" type='text' name='search_LTE_${entity.propertyName}' id='search_LTE_${entity.propertyName}' value="${r"${"}param.search_LTE_${entity.propertyName}}" readonly='readonly'/><img id='calButton_LTE_${entity.propertyName}' src='<c:url value='/admin/scripts/calendar/skins/aqua/cal.gif'/>' border='0' alt='选择日期' style='cursor: pointer;'/><script type='text/javascript'>new calendar('search_LTE_${entity.propertyName}', 'calButton_LTE_${entity.propertyName}', '%Y-%m-%d');</script>
						<#elseif entity.dataType == 1>
							<#if entity.options??>
							${entity.common}:<select name="search_EQ_${entity.propertyName}"><option value="">所有</option><c:forEach var="e" items="${r"${"}all${entity.propertyName?cap_first}s}"><option value="${r"${"}e.key}" ${r"${"}param.search_EQ_${entity.propertyName} == e.key?'selected':''}>${r"${"}e.value}</option></c:forEach></select>
							<#else>
							${entity.common}:<input type="text" name="search_EQ_${entity.propertyName}" value="${r"${"}param.search_EQ_${entity.propertyName}}"  />
							</#if>
						<#else>	
							${entity.common}:<input type="text" name="search_LIKE_${entity.propertyName}" value="${r"${"}param.search_LIKE_${entity.propertyName}}"  />
						</#if>								
						</#if>
					</#list>
						<a href="#" class="easyui-linkbutton" icon="icon-search" onclick="javascript:submitQuery();"> 查询 </a>
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
					<ec:column property="${entity.propertyName}" title="${entity.common}" style="width:10%">${r"${"}all${entity.propertyName?cap_first}s[e.${entity.propertyName}]}</ec:column>
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
	<!-- 批量导入视图 -->
	<div id="importWindow" class="easyui-window">
	<div class="ctrltable" id="ADcon0">
	  <div class="ctrlform">
	    <form id="importCsvEntityForm" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/importCsv.mvc" method="post" enctype="multipart/form-data">
	
	      <jodd:form bean="content" scope="request">
	        <input type="hidden" value="<%=request.getAttribute("ec_p")%>" name="ec_p" />
	        <div>CSV文件导入</div>
	        <table>
	          <tr>
	            <th style="width: 100px"><span> * </span> 选择CSV文件：</th>
	            <td colspan="5"><input type="file" size="10" name="contentFile" id="contentFile"> <input type="submit" value="导入CSV" /></td>
	          </tr>
	        </table>
	
	      </jodd:form>
	    </form>
	    <br>
	    <form id="importXlsEntityForm" action="${configuration.pagePath}/${names.domainClassName?uncap_first}/importXls.mvc" method="post" enctype="multipart/form-data">
	      <jodd:form bean="content" scope="request">
	        <input type="hidden" value="<%=request.getAttribute("ec_p")%>" name="ec_p" />
	        <div>Excel文件导入</div>
	        <table>
	          <tr>
	            <th style="width: 100px"><span> * </span> 选择Excel文件：</th>
	            <td colspan="5"><input type="file" size="10" name="contentFile" id="contentFile"> <input type="submit" value="导入Excel" /></td>
	          </tr>
	        </table>
	      </jodd:form>
	    </form>
	    <br>
	    <div style="text-align: right;">
	      <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#importWindow').window('close');"> 关闭 </a>
	    </div>
	  </div>
	</div>
	</div>	
	
</body>
</html>
