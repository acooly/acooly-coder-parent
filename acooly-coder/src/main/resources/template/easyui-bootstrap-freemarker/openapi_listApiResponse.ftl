/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.openapiMessage.packageName}.response;

import ${nameScheme.dto.packageName}.*;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.comment} 列表查询 响应报文
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Getter
@Setter
public class ${nameScheme.domainClassName}ListApiResponse extends PageApiResponse<${nameScheme.domainClassName}ListInfo> {

}