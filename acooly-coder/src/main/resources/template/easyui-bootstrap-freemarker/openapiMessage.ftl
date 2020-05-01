/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.openApiMessagePackage};

<#assign existJavaEnum=false>
<#list table.columns as entity>
   <#if entity.dataType.enum><#assign existJavaEnum=true></#if>
</#list>

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

<#list table.importDeclares as declare>
import ${declare};
</#list>

/**
 * ${table.comment} ApiMessage
 * 注意：本类自定生成，不能直接使用，请开发人员根据业务选择下面的属性用于快速定义OpenApi的报文，
 * 主要减轻定义@OpenApiField和JSR303的工作。
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Getter
@Setter
public class ${nameScheme.domainClassName}ApiRequest extends ApiRequest {
<#list table.columns as entity>
    <#assign javaDataType="${entity.dataType.javaTypeName}">
    <#if entity.dataType.enum><#assign javaDataType="${entity.dataType.javaName}"></#if>
    <#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
    /**
     * ${entity.common}
     */
    <#if !entity.nullable>
    <#if entity.dataType.string>@NotEmpty<#else>@NotNull</#if>
    </#if>
    <#if entity.dataType.string>
	@Size(max = ${entity.length?c})
    </#if>
    <#if entity.dataType.number>
	@Max(${entity.length?c})
    </#if>
    @OpenApiField(desc = "${entity.common}", constraint = "${entity.common}"<#if !entity.dataType.object>, demo = ""</#if>, ordinal = ${entity_index + 1})
    private ${javaDataType} ${entity.propertyName};

    </#if>
</#list>
}
