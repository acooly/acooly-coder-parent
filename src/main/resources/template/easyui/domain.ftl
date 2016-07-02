package ${nameScheme.domainPackage};

<#assign existJavaDate=false>
<#assign existJavaEnum=false>
<#list table.columnMetadatas as entity>
	<#if entity.dataType == 2><#assign existJavaDate=true></#if>
	<#if entity.dataType == 10><#assign existJavaEnum=true></#if>
</#list>
<#if existJavaDate>
import java.util.Date;
</#if>
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
<#list table.columnMetadatas as entity>
<#if entity.dataType == 10>
import ${nameScheme.enumPackage}.${entity.propertyName?cap_first};
</#if>
</#list>
/**
 * ${table.comment} Entity
 *
 * @author Acooly Code Generator
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Entity
@Table(name = "${table.name}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${nameScheme.domainClassName} extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
<#list table.columnMetadatas as entity>
	/** ${entity.common} */
	<#assign javaDataType="${entity.javaDataType}">
	<#if entity.dataType = 10><#assign javaDataType="${entity.propertyName?cap_first}"></#if>
	<#if entity.name?lower_case = 'id'>
	@Id	
	${entityIdDeclare}
	</#if>
	<#if entity.dataType = 10>
	@Enumerated(EnumType.STRING)
	</#if>	
	private ${javaDataType} ${entity.propertyName}<#if (entity.defaultValue)??> = <#if entity.dataType = 1>${entity.defaultValue}l<#elseif entity.dataType = 4>${entity.defaultValue}<#elseif entity.dataType = 2>new Date()<#else>"${entity.defaultValue}"</#if></#if>;
</#list>
	
<#list table.columnMetadatas as entity>
	<#assign javaDataType="${entity.javaDataType}">
	<#if entity.dataType = 10><#assign javaDataType="${entity.propertyName?cap_first}"></#if>
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
