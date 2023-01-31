/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 */
package ${nameScheme.serviceImplPackage};

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
import ${nameScheme.daoPackage}.${nameScheme.daoClassName};
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
 * ${table.comment} Service实现
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Service("${nameScheme.serviceClassName?uncap_first}")
public class ${nameScheme.serviceImplClassName} extends EntityServiceImpl<${nameScheme.domainClassName}, ${nameScheme.daoClassName}> implements ${nameScheme.serviceClassName} {

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
    @Override
    public ${nameScheme.domainClassName} uniqueBy${i.serviceMethodDeclare} {
        return getEntityDao().uniqueBy${i.daoMethodCall};
    }

 </#list>
</#if>
}
