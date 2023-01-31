/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 */
 package ${nameScheme.daoPackage};

import com.acooly.module.mybatis.EntityMybatisDao;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
<#if table.indexes?? && (table.indexes?size > 0) >
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
<#list table.indexes as i>
    <#list i.columns as c>
        <#if c.dataType.javaType == 'Enum'>
import ${c.dataType.javaDeclare};
        </#if>
    </#list>
</#list>
</#if>

/**
 * ${table.comment} Mybatis Dao
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${nameScheme.daoClassName} extends EntityMybatisDao<${nameScheme.domainClassName}> {

<#if table.indexes?? && (table.indexes?size > 0) >
<#list table.indexes as i>
    /**
     * 唯一索引查询: ${i.name}
     *
     <#list i.columns as c>
     * @param ${c.propertyName}
     </#list>
     * @return
     */
    @Select("select * from ${table.name} where ${i.daoSqlWhere}")
    ${nameScheme.domainClassName} uniqueBy${i.daoMethodDeclare};

</#list>
</#if>
}
