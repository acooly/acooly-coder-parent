/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
<#assign entity="${nameScheme.domainClassName}">
<#assign entityVar="${nameScheme.domainClassName?uncap_first}">
package ${nameScheme.openapiService.packageName};

import ${nameScheme.dto.packageName}.${entity}Info;
import ${nameScheme.openapiMessage.packageName}.request.${entity}InfoApiRequest;
import ${nameScheme.openapiMessage.packageName}.response.${entity}InfoApiResponse;
import ${nameScheme.facade.packageName}.${entity}RemoteService;

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
* ${table.comment} 详情 接口服务
*
* @author ${configuration.codeAuthor}
* Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("${table.comment}详情查询接口，根据项目ID进行查询。")
@OpenApiService(name = "${entityVar}Info", desc = "${table.comment}详情", owner = "${configuration.codeAuthor}", busiType = ApiBusiType.Query)
public class ${entity}InfoApiService extends BaseApiService<${entity}InfoApiRequest, ${entity}InfoApiResponse> {

    @Autowired
    private ${entity}RemoteService ${entityVar}RemoteService;

    @Override
    protected void doService(${entity}InfoApiRequest request, ${entity}InfoApiResponse response) {
        SingleOrder<Long> order = OpenApiFacades.order(SingleOrder.class, request);
        order.setDto(request.getId());
        SingleResult<${entity}Info> result = ${entityVar}RemoteService.read(order);
        OpenApiFacades.result(result, response);
        if (result.success() && result.getDto() != null) {
            // 如果成功，则按需拷贝
            response.set${entity}Info(BeanCopier.copy(result.getDto(), ${entity}Info.class));
        }
    }
}