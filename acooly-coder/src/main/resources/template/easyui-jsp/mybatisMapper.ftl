<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${nameScheme.daoPackage}.${nameScheme.daoClassName}">

<#assign entityVariable="${nameScheme.domainClassName?uncap_first}" />
    <resultMap id="${entityVariable}ResultMap" type="${nameScheme.domainPackage}.${nameScheme.domainClassName}">
        <id property="id" column="${entityVariable}_id"/>
    <#list table.columns as entity>
        <#if entity.name?lower_case != 'id'>
        <result property="${entity.propertyName}" column="${entityVariable}_${entity.name}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="${entityVariable}SqlSelect">
        <#list table.columns as entity>
            ${entityVariable}.${entity.name} as ${entityVariable}_${entity.name}<#if entity_index+1 < table.columns?size>,</#if>
        </#list>
    </sql>

</mapper>