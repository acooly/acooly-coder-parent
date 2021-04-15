/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by acooly
 * date:2021-04-15
 */
package com.acooly.coder.test.openapi.test;

import com.acooly.coder.test.openapi.message.request.*;
import com.acooly.coder.test.openapi.message.response.*;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * acoolycoder测试 OpenApi单元测试
 *
 * @author acooly
 * Date: 2021-04-15 17:29:16
 */
@Slf4j
public class CodeCustomerApiServiceTest extends AbstractApiServieTests {

    {
        // 注意修改：你的OpenApi服务网关的端口号
        gatewayUrl = "http://127.0.0.1:8094/gateway.do";
    }

    @Test
    public void testCodeCustomerInfo() {
        CodeCustomerInfoApiRequest request = new CodeCustomerInfoApiRequest();
        request.setId(1L);
        CodeCustomerInfoApiResponse response = request(request, CodeCustomerInfoApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }

    @Test
    public void testCodeCustomerList() {
        CodeCustomerListApiRequest request = new CodeCustomerListApiRequest();
        CodeCustomerListApiResponse response = request(request, CodeCustomerListApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }


}
