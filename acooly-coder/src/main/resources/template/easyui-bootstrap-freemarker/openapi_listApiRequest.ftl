/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.openapiMessage.packageName}.request;

import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * ${table.comment} 列表查询 请求报文
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Getter
@Setter
public class ${nameScheme.domainClassName}ListApiRequest extends PageApiRequest {
}
