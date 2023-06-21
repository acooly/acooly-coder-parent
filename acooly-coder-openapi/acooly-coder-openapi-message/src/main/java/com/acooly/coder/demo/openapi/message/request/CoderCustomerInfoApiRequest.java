/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.openapi.message.request;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成客户信息 详情查询 请求报文
 *
 * @author acooly
 * Date: 2023-06-16 15:23:11
 */
@Getter
@Setter
public class CoderCustomerInfoApiRequest extends ApiRequest {

    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
