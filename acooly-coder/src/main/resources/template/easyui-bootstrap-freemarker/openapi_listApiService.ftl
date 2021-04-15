/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
<#assign entity="${nameScheme.domainClassName}">
<#assign entityVar="${nameScheme.domainClassName?uncap_first}">
package ${nameScheme.openapiService.packageName};

import ${nameScheme.dto.packageName}.${entity}ListInfo;
import ${nameScheme.openapiMessage.packageName}.request.${entity}ListApiRequest;
import ${nameScheme.openapiMessage.packageName}.response.${entity}ListApiResponse;
import ${nameScheme.facade.packageName}.${entity}RemoteService;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.utils.OpenApiFacades;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


/**
* ${table.comment} 列表查询 接口服务
*
* @author ${configuration.codeAuthor}
* Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
*/
@Slf4j
@ApiDocType(code = "acoolyCoder", name = "代码生成")
@ApiDocNote("${table.comment}的分页列表查询，可支持多条件，多属性排序。")
@OpenApiService(name = "${entityVar}List", desc = "${table.comment}列表", owner = "${configuration.codeAuthor}", busiType = ApiBusiType.Query)
public class ${entity}ListApiService extends BaseApiService<${entity}ListApiRequest, ${entity}ListApiResponse> {

    @Autowired
    private ${entity}RemoteService ${entityVar}RemoteService;

    @Override
    protected void doService(${entity}ListApiRequest request, ${entity}ListApiResponse response) {
        PageOrder order = OpenApiFacades.order(PageOrder.class, request);
        PageResult<${entity}ListInfo> result = ${entityVar}RemoteService.list(order);
        OpenApiFacades.result(result, response);
    }
}