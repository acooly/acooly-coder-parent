/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.dto.packageName};

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
<#list table.importDeclares as declare>
import ${declare};
</#list>

/**
* ${table.comment} Info Dto
* For facade and openApi
*
* @author ${configuration.codeAuthor}
* @date ${datetime("yyyy-MM-dd HH:mm:ss")}
*/
@Getter
@Setter
public class ${nameScheme.domainClassName}CreateInfo extends InfoBase {
<#assign index=1>
<#list table.columns as entity>
    <#assign javaDataType="${entity.dataType.javaTypeName}">
    <#if entity.dataType.enum><#assign javaDataType="${entity.dataType.javaName}"></#if>
    <#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
    /**
     * ${entity.common}
     */
    <#if !entity.nullable>
    <#if entity.dataType.string>@NotBlank<#else>@NotNull</#if>
    </#if>
    <#if entity.length != 0 && !entity.dataType.bigObject && entity.dataType.string>
    @Size(max = ${entity.length?c})
    </#if>
    @OpenApiField(desc = "${entity.propertyName}", constraint = "${entity.common}", demo = "${entity.demo}", ordinal = ${index})
    private ${javaDataType} ${entity.propertyName};
    <#assign index=index+1>
    </#if>
</#list>
}
