package ${names.domainPackage};


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.feinno.framework.common.domain.AbstractEntity;

/**
 * ${table.comment} Entity
 *
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 *
 * @author Acooly Code Generator
 */
@Entity
@Table(name = "${table.name}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${names.domainClassName} extends AbstractEntity {
<#list table.columnMetadatas as entity>
	/** ${entity.common} */
	private ${entity.javaDataType} ${entity.propertyName};
	
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
