/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 */
package com.acooly.coder.demo.openapi.test;

import com.acooly.coder.demo.openapi.message.request.*;
import com.acooly.coder.demo.openapi.message.response.*;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * 代码生成客户信息 OpenApi单元测试
 *
 * @author acooly
 * Date: 2023-06-16 15:23:11
 */
@Slf4j
public class CoderCustomerApiServiceTest extends AbstractApiServieTests {

    {
        // 注意修改：你的OpenApi服务网关的端口号
        gatewayUrl = "http://127.0.0.1:8093/gateway.do";
    }

    @Test
    public void testCoderCustomerInfo() {
        CoderCustomerInfoApiRequest request = new CoderCustomerInfoApiRequest();
        request.setId(1L);
        CoderCustomerInfoApiResponse response = request(request, CoderCustomerInfoApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }

    @Test
    public void testCoderCustomerList() {
        CoderCustomerListApiRequest request = new CoderCustomerListApiRequest();
        CoderCustomerListApiResponse response = request(request, CoderCustomerListApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }


}
