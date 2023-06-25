<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_customer_editform" class="form-horizontal" action="/manage/coder/demo1/customer/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post">
        <@jodd.form bean="customer" scope="request">
            <input name="id" type="hidden"/>
            <div class="card-body">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">用户名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="username" placeholder="请输入用户名..." class="easyui-validatebox form-control" data-options="validType:['length[1,32]'],required:true">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">生日：</label>
                    <div class="col-sm-10">
                        <input type="text" name="birthday" placeholder="请输入生日..." class="easyui-validatebox form-control" value="<#if customer.birthday??>${customer.birthday?string('yyyy-MM-dd')}</#if>" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">年龄：</label>
                    <div class="col-sm-10">
                        <input type="text" name="age" placeholder="请输入年龄..." class="easyui-validatebox form-control" data-inputmask="'alias':'integer'" data-mask data-options="validType:['number[0,127]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">性别：</label>
                    <div class="col-sm-10">
                        <input type="text" name="gender" placeholder="请输入性别..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">生肖：</label>
                    <div class="col-sm-10">
                        <input type="text" name="animal" placeholder="请输入生肖..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="realName" placeholder="请输入姓名..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]'],required:true">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">证件类型：</label>
                    <div class="col-sm-4">
                        <select name="idcardType" class="form-control select2bs4"> <#list allIdcardTypes as k,v>
                                <option value="${k}">${v}</option></#list> </select>
                    </div>
                    <label class="col-sm-2 col-form-label">身份证号码：</label>
                    <div class="col-sm-4">
                        <input type="text" name="idcardNo" placeholder="请输入身份证号码..." class="easyui-validatebox form-control" data-options="validType:['length[1,48]'],required:true">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">银行卡卡号：</label>
                    <div class="col-sm-10">
                        <input type="text" name="bankCardNo" placeholder="请输入银行卡卡号..." class="easyui-validatebox form-control" data-options="validType:['length[1,48]'],required:true">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">手机号码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="mobileNo" placeholder="请输入手机号码..." class="easyui-validatebox form-control" data-options="validType:['length[1,11]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">邮件：</label>
                    <div class="col-sm-10">
                        <input type="text" name="mail" placeholder="请输入邮件..." class="easyui-validatebox form-control" data-options="validType:['length[1,64]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">客户类型：</label>
                    <div class="col-sm-10">
                        <select name="customerType" class="form-control select2bs4"> <#list allCustomerTypes as k,v>
                                <option value="${k}">${v}</option></#list> </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">摘要：</label>
                    <div class="col-sm-10">
                        <textarea rows="3" cols="40" placeholder="请输入摘要..." name="subject" class="easyui-validatebox form-control" data-options="validType:['length[1,128]']"></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">详情：</label>
                    <div class="col-sm-10">
                        <textarea rows="3" cols="40" placeholder="请输入详情..." name="content" class="easyui-validatebox form-control" data-options="validType:['length[1,999999999]']"></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">完成度：</label>
                    <div class="col-sm-10">
                        <input type="text" name="doneRatio" placeholder="请输入完成度..." class="easyui-validatebox form-control" data-inputmask="'alias':'integer'" data-mask style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">付款率：</label>
                    <div class="col-sm-10">
                        <input type="text" name="payRate" placeholder="请输入付款率..." class="easyui-validatebox form-control" data-inputmask="'alias':'integer'" data-mask style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,2147483646]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">薪水：</label>
                    <div class="col-sm-10">
                        <input type="text" name="salary" placeholder="请输入薪水..." class="easyui-validatebox form-control" data-inputmask="'alias':'integer'" data-mask style="height: 30px;width: 260px;line-height: 1.3em;" data-options="validType:['number[0,999999999]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">注册渠道：</label>
                    <div class="col-sm-10">
                        <input type="text" name="registryChannel" placeholder="请输入注册渠道..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">推送广告：</label>
                    <div class="col-sm-10">
                        <input type="text" name="pushAdv" placeholder="请输入推送广告..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]']">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">数字类型：</label>
                    <div class="col-sm-10">
                        <select name="numStatus" class="form-control select2bs4">
                            <#list allNumStatuss as k,v>
                                <option value="${k}">${v}</option></#list>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">网址：</label>
                    <div class="col-sm-10">
                        <textarea rows="3" cols="40" placeholder="请输入网址..." name="website" class="easyui-validatebox form-control" data-options="validType:['length[1,128]']"></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">照片：</label>
                    <div class="col-sm-10">
                        <textarea rows="3" cols="40" placeholder="请输入照片..." name="photoPath" class="easyui-validatebox form-control" data-options="validType:['length[1,128]']"></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">状态：</label>
                    <div class="col-sm-10">
                        <input type="text" name="status" placeholder="请输入状态..." class="easyui-validatebox form-control" data-options="validType:['length[1,16]'],required:true">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">备注：</label>
                    <div class="col-sm-10">
                        <textarea rows="3" cols="40" placeholder="请输入备注..." name="comments" class="easyui-validatebox form-control" data-options="validType:['length[1,255]']"></textarea>
                    </div>
                </div>
            </div>
        </@jodd.form>
    </form>
</div>
