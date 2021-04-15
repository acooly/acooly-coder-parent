/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-15
*/
package com.acooly.coder.test.openapi.message.response;

import com.acooly.coder.test.common.dto.*;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * acoolycoder测试 详情查询 响应报文
 *
 * @author acooly
 * Date: 2021-04-15 17:29:16
 */
@Getter
@Setter
public class CodeCustomerInfoApiResponse extends ApiResponse {

    @OpenApiField(desc = "acoolycoder测试详情", constraint = "acoolycoder测试详情", ordinal = 1)
    private CodeCustomerInfo codeCustomerInfo;

}
