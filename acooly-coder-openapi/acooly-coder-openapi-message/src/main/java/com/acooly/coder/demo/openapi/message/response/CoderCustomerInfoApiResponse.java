/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.openapi.message.response;

import com.acooly.coder.demo.common.dto.*;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成客户信息 详情查询 响应报文
 *
 * @author acooly
 * Date: 2023-06-16 15:23:11
 */
@Getter
@Setter
public class CoderCustomerInfoApiResponse extends ApiResponse {

    @OpenApiField(desc = "代码生成客户信息详情", constraint = "代码生成客户信息详情", ordinal = 1)
    private CoderCustomerInfo coderCustomerInfo;

}
