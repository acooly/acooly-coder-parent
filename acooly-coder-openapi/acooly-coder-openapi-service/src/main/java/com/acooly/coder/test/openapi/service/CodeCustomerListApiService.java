/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-18
*/
package com.acooly.coder.test.openapi.service;

import com.acooly.coder.test.common.dto.CodeCustomerListInfo;
import com.acooly.coder.test.openapi.message.request.CodeCustomerListApiRequest;
import com.acooly.coder.test.openapi.message.response.CodeCustomerListApiResponse;
import com.acooly.coder.test.facade.api.CodeCustomerRemoteService;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.utils.OpenApiFacades;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


/**
* acoolycoder测试 列表查询 接口服务
*
* @author acooly
* Date: 2021-04-18 00:31:50
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("acoolycoder测试的分页列表查询，可支持多条件，多属性排序。")
@OpenApiService(name = "codeCustomerList", desc = "acoolycoder测试列表", owner = "acooly", busiType = ApiBusiType.Query)
public class CodeCustomerListApiService extends BaseApiService<CodeCustomerListApiRequest, CodeCustomerListApiResponse> {

    @Autowired
    private CodeCustomerRemoteService codeCustomerRemoteService;

    @Override
    protected void doService(CodeCustomerListApiRequest request, CodeCustomerListApiResponse response) {
        PageOrder order = OpenApiFacades.order(PageOrder.class, request);
        PageResult<CodeCustomerListInfo> result = codeCustomerRemoteService.list(order);
        OpenApiFacades.result(result, response);
    }
}