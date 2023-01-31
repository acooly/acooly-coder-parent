/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.servicePackage};

import com.acooly.core.common.service.EntityService;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
<#if table.indexes?? && (table.indexes?size > 0) >
 <#list table.indexes as i>
  <#list i.columns as c>
   <#if c.dataType.javaType == 'Enum'>
    import ${c.dataType.javaDeclare};
   </#if>
  </#list>
 </#list>
</#if>
/**
 * ${table.comment} Service接口
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${nameScheme.serviceClassName} extends EntityService<${nameScheme.domainClassName}> {

<#if table.indexes?? && (table.indexes?size > 0) >
 <#list table.indexes as i>
    /**
     * 唯一性查询
     * ${i.name}
     *
     <#list i.columns as c>
     * @param ${c.propertyName}
     </#list>
     * @return
     */
    ${nameScheme.domainClassName} uniqueBy${i.serviceMethodDeclare};

 </#list>
</#if>
}
