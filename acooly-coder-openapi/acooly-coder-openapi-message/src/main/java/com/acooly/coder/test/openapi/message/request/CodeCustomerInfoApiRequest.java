/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-18
*/
package com.acooly.coder.test.openapi.message.request;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * acoolycoder测试 详情查询 请求报文
 *
 * @author acooly
 * Date: 2021-04-18 00:31:50
 */
@Getter
@Setter
public class CodeCustomerInfoApiRequest extends ApiRequest {

    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
