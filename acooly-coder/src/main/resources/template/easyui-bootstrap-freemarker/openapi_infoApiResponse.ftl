/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.openapiMessage.packageName}.response;

import ${nameScheme.dto.packageName}.*;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.comment} 详情查询 响应报文
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Getter
@Setter
public class ${nameScheme.domainClassName}InfoApiResponse extends ApiResponse {

    @OpenApiField(desc = "${table.comment}详情", constraint = "${table.comment}详情", ordinal = 1)
    private ${nameScheme.domainClassName}Info ${nameScheme.domainClassName?uncap_first}Info;

}
