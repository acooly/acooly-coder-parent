/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.openapi.service;

import com.acooly.coder.demo.common.dto.CoderCustomerListInfo;
import com.acooly.coder.demo.openapi.message.request.CoderCustomerListApiRequest;
import com.acooly.coder.demo.openapi.message.response.CoderCustomerListApiResponse;
import com.acooly.coder.demo.facade.api.CoderCustomerRemoteService;

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
* 代码生成客户信息 列表查询 接口服务
*
* @author acooly
* Date: 2023-06-16 15:23:11
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("代码生成客户信息的分页列表查询，可支持多条件，多属性排序。")
@OpenApiService(name = "coderCustomerList", desc = "代码生成客户信息列表", owner = "acooly", busiType = ApiBusiType.Query)
public class CoderCustomerListApiService extends BaseApiService<CoderCustomerListApiRequest, CoderCustomerListApiResponse> {

    @Autowired
    private CoderCustomerRemoteService coderCustomerRemoteService;

    @Override
    protected void doService(CoderCustomerListApiRequest request, CoderCustomerListApiResponse response) {
        PageOrder order = OpenApiFacades.order(PageOrder.class, request);
        PageResult<CoderCustomerListInfo> result = coderCustomerRemoteService.list(order);
        OpenApiFacades.result(result, response);
    }
}