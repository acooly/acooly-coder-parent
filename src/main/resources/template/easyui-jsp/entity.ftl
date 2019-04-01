/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.domainPackage};

<#assign existJavaEnum=false>
<#list table.columns as entity>
<#if entity.dataType.enum><#assign existJavaEnum=true></#if>
</#list>

import javax.persistence.Entity;
<#if existJavaEnum>
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
</#if>
import javax.persistence.Table;

<#if entityIdDeclare?index_of('GenericGenerator') != -1>
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
</#if>
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import com.acooly.core.common.domain.AbstractEntity;
<#list table.importDeclares as declare>
import ${declare};
</#list>

/**
 * ${table.comment} Entity
 *
 * @author ${configuration.codeAuthor}
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Entity
@Table(name = "${table.name}")
@Getter
@Setter
public class ${nameScheme.domainClassName} extends AbstractEntity {

<#list table.columns as entity>
	<#assign javaDataType="${entity.dataType.javaTypeName}">
	<#if entity.dataType.enum><#assign javaDataType="${entity.dataType.javaName}"></#if>
	<#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>
	/** ${entity.common} */
	<#if entity.dataType.enum>
    @Enumerated(EnumType.STRING)
    </#if>
	<#if !entity.nullable>
	<#if entity.dataType.string>@NotEmpty<#else>@NotNull</#if>
	</#if>
	<#if entity.dataType.string>
	@Size(max=${entity.length?c})
	</#if>
    private ${javaDataType} ${entity.propertyName}<#if (entity.defaultValue)??> = <#if entity.dataType.long>${entity.defaultValue}l<#elseif entity.dataType.integer>${entity.defaultValue}<#elseif entity.dataType.double>${entity.defaultValue}d<#elseif entity.dataType.date>new Date()<#elseif entity.dataType.enum>${javaDataType}.${entity.defaultValue}<#else>"${entity.defaultValue}"</#if></#if>;

	</#if>
</#list>
<#--<#list table.columns as entity>-->
	<#--<#assign javaDataType="${entity.dataType.javaTypeName}">-->
	<#--<#assign accesserMethodName="${entity.propertyName?cap_first}">-->
	<#--<#if entity.dataType.enum><#assign javaDataType="${entity.dataType.javaName}"></#if>-->

	<#--<#if entity.name?lower_case != 'id' && entity.name?lower_case != 'create_time' && entity.name?lower_case != 'update_time'>-->
	<#--public ${javaDataType} get${accesserMethodName}(){-->
		<#--return this.${entity.propertyName};-->
	<#--}-->
	<#---->
	<#--public void set${accesserMethodName}(${javaDataType} ${entity.propertyName}){-->
		<#--this.${entity.propertyName} = ${entity.propertyName};-->
	<#--}-->
    <#--</#if>-->
<#--</#list>	-->
}
