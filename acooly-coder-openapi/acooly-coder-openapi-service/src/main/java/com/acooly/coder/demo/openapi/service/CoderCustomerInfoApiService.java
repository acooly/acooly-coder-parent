/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.openapi.service;

import com.acooly.coder.demo.common.dto.CoderCustomerInfo;
import com.acooly.coder.demo.openapi.message.request.CoderCustomerInfoApiRequest;
import com.acooly.coder.demo.openapi.message.response.CoderCustomerInfoApiResponse;
import com.acooly.coder.demo.facade.api.CoderCustomerRemoteService;

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
* 代码生成客户信息 详情 接口服务
*
* @author acooly
* Date: 2023-06-16 15:23:11
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("代码生成客户信息详情查询接口，根据项目ID进行查询。")
@OpenApiService(name = "coderCustomerInfo", desc = "代码生成客户信息详情", owner = "acooly", busiType = ApiBusiType.Query)
public class CoderCustomerInfoApiService extends BaseApiService<CoderCustomerInfoApiRequest, CoderCustomerInfoApiResponse> {

    @Autowired
    private CoderCustomerRemoteService coderCustomerRemoteService;

    @Override
    protected void doService(CoderCustomerInfoApiRequest request, CoderCustomerInfoApiResponse response) {
        SingleOrder<Long> order = OpenApiFacades.order(SingleOrder.class, request);
        order.setDto(request.getId());
        SingleResult<CoderCustomerInfo> result = coderCustomerRemoteService.read(order);
        OpenApiFacades.result(result, response);
        if (result.success() && result.getDto() != null) {
            // 如果成功，则按需拷贝
            response.setCoderCustomerInfo(BeanCopier.copy(result.getDto(), CoderCustomerInfo.class));
        }
    }
}