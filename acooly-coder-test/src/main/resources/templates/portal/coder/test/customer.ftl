<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer 客户信息 Portal</title>
</head>
<body>
<h3>客户信息ajax列表查询</h3>
<div class="aui-search-form">
    用户名: <input type="text" class="text" size="15" name="search_LIKE_username"/>
    年龄: <input type="text" class="text" size="15" name="search_EQ_age"/>
    生日: <input size="15" class="text" id="search_GTE_birthday" name="search_GTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    至<input size="15" class="text" id="search_LTE_birthday" name="search_LTE_birthday" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    性别: <select style="width:80px;height:27px;" name="search_EQ_gender" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allGenders as k,v><option value="${k}">${v}</option></#list></select>
    姓名: <input type="text" class="text" size="15" name="search_LIKE_realName"/>
    证件类型: <select style="width:80px;height:27px;" name="search_EQ_idcardType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allIdcardTypes as k,v><option value="${k}">${v}</option></#list></select>
    身份证号码: <input type="text" class="text" size="15" name="search_LIKE_idcardNo"/>
    手机号码: <input type="text" class="text" size="15" name="search_LIKE_mobileNo"/>
    邮件: <input type="text" class="text" size="15" name="search_LIKE_mail"/>
    摘要: <input type="text" class="text" size="15" name="search_LIKE_subject"/>
    客户类型: <select style="width:80px;height:27px;" name="search_EQ_customerType" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allCustomerTypes as k,v><option value="${k}">${v}</option></#list></select>
    手续费: <input type="text" class="text" size="15" name="search_EQ_fee"/>
    状态: <select style="width:80px;height:27px;" name="search_EQ_status" editable="false" panelHeight="auto" class="easyui-combobox"><option value="">所有</option><#list allStatuss as k,v><option value="${k}">${v}</option></#list></select>
    创建时间: <input size="15" class="text" id="search_GTE_createTime" name="search_GTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    至<input size="15" class="text" id="search_LTE_createTime" name="search_LTE_createTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    薪水: <input type="text" class="text" size="15" name="search_EQ_salary"/>
    update_time: <input size="15" class="text" id="search_GTE_updateTime" name="search_GTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    至<input size="15" class="text" id="search_LTE_updateTime" name="search_LTE_updateTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
    <button type="button">查询</button>
</div>
<table border="1">
<tr>
    <th>ID</th>
    <th>用户名</th>
    <th>姓名</th>
    <th>年龄</th>
</tr>
<#list customers as e>
    <tr>
        <td>${e.id}</td>
        <td>${e.usernmae}</td>
        <td>${e.age}</td>
    </tr>
</#list>
</table>

</body>
</html>