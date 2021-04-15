/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-15
*/
package com.acooly.coder.test.openapi.service;

import com.acooly.coder.test.common.dto.CodeCustomerInfo;
import com.acooly.coder.test.openapi.message.request.CodeCustomerInfoApiRequest;
import com.acooly.coder.test.openapi.message.response.CodeCustomerInfoApiResponse;
import com.acooly.coder.test.facade.api.CodeCustomerRemoteService;

import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.utils.OpenApiFacades;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
* acoolycoder测试 详情 接口服务
*
* @author acooly
* Date: 2021-04-15 17:29:16
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("acoolycoder测试详情查询接口，根据项目ID进行查询。")
@OpenApiService(name = "codeCustomerInfo", desc = "acoolycoder测试详情", owner = "acooly", busiType = ApiBusiType.Query)
public class CodeCustomerInfoApiService extends BaseApiService<CodeCustomerInfoApiRequest, CodeCustomerInfoApiResponse> {

    @Autowired
    private CodeCustomerRemoteService codeCustomerRemoteService;

    @Override
    protected void doService(CodeCustomerInfoApiRequest request, CodeCustomerInfoApiResponse response) {
        SingleOrder<Long> order = OpenApiFacades.order(SingleOrder.class, request);
        order.setDto(request.getId());
        SingleResult<CodeCustomerInfo> result = codeCustomerRemoteService.read(order);
        OpenApiFacades.result(result, response);
        if (result.success() && result.getDto() != null) {
            // 如果成功，则按需拷贝
            response.setCodeCustomerInfo(BeanCopier.copy(result.getDto(), CodeCustomerInfo.class));
        }
    }
}