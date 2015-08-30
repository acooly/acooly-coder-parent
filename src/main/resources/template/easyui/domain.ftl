package ${nameScheme.domainPackage};

<#assign existJavaDate=false>
<#list table.columnMetadatas as entity>
	<#if entity.dataType == 2>
		<#assign existJavaDate=true>
		<#break>
	</#if>
</#list>
<#if existJavaDate>
import java.util.Date;
</#if>
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acooly.core.common.domain.AbstractEntity;

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
	private ${entity.javaDataType} ${entity.propertyName}<#if (entity.defaultValue)??> = <#if entity.dataType = 1>${entity.defaultValue}l<#elseif entity.dataType = 4>${entity.defaultValue}<#elseif entity.dataType = 2>new Date()<#else>"${entity.defaultValue}"</#if></#if>;
</#list>
	
<#list table.columnMetadatas as entity>
	<#if entity.name?lower_case = 'id'>
	@Id	
	${entityIdDeclare}
	</#if>
	public ${entity.javaDataType} get${entity.propertyName?cap_first}(){
		return this.${entity.propertyName};
	}
	
	public void set${entity.propertyName?cap_first}(${entity.javaDataType} ${entity.propertyName}){
		this.${entity.propertyName} = ${entity.propertyName};
	}
</#list>	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
