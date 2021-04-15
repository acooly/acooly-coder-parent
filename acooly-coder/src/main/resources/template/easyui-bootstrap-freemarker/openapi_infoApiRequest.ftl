/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.openapiMessage.packageName}.request;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.comment} 详情查询 请求报文
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Getter
@Setter
public class ${nameScheme.domainClassName}InfoApiRequest extends ApiRequest {

    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
