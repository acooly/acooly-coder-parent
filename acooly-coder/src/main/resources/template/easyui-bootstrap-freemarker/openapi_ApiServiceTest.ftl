/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 */
<#assign entity="${nameScheme.domainClassName}">
package ${nameScheme.openapiTest.packageName};

import ${nameScheme.openapiMessage.packageName}.request.*;
import ${nameScheme.openapiMessage.packageName}.response.*;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * ${table.comment} OpenApi单元测试
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Slf4j
public class ${entity}ApiServiceTest extends AbstractApiServieTests {

    {
        // 注意修改：你的OpenApi服务网关的端口号
        gatewayUrl = "http://127.0.0.1:8093/gateway.do";
    }

    @Test
    public void test${entity}Info() {
        ${entity}InfoApiRequest request = new ${entity}InfoApiRequest();
        request.setId(1L);
        ${entity}InfoApiResponse response = request(request, ${entity}InfoApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }

    @Test
    public void test${entity}List() {
        ${entity}ListApiRequest request = new ${entity}ListApiRequest();
        ${entity}ListApiResponse response = request(request, ${entity}ListApiResponse.class);
        Assert.assertEquals(response.isSuccess(), true);
    }


}
