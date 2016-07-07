/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.domainPackage};

<#assign existJavaEnum=false>
<#list table.columns as entity>
<#if entity.dataType.enum><#assign existJavaEnum=true></#if>
</#list>

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
<#if existJavaEnum>
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
</#if>
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${nameScheme.domainClassName} extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
<#list table.columns as entity>
	/** ${entity.common} */
	<#assign javaDataType="${entity.dataType.javaTypeName}">
	<#if entity.dataType.enum><#assign javaDataType="${entity.propertyName?cap_first}"></#if>
	<#if entity.name?lower_case = 'id'>
	@Id	
	${entityIdDeclare}
	</#if>
	<#if entity.dataType.enum>
	@Enumerated(EnumType.STRING)
	</#if>	
	private ${javaDataType} ${entity.propertyName}<#if (entity.defaultValue)??> = <#if entity.dataType.long>${entity.defaultValue}l<#elseif entity.dataType.integer>1<#elseif entity.dataType.date>new Date()<#else>"${entity.defaultValue}"</#if></#if>;
</#list>
	
<#list table.columns as entity>
	<#assign javaDataType="${entity.dataType.javaTypeName}">
	<#if entity.dataType.enum><#assign javaDataType="${entity.propertyName?cap_first}"></#if>
	public ${javaDataType} get${entity.propertyName?cap_first}(){
		return this.${entity.propertyName};
	}
	
	public void set${entity.propertyName?cap_first}(${javaDataType} ${entity.propertyName}){
		this.${entity.propertyName} = ${entity.propertyName};
	}
</#list>	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
