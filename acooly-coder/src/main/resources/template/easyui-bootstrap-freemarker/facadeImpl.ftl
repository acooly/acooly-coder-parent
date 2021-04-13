/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.facadeImpl.packageName};

import ${nameScheme.facade.packageName}.*;
import ${nameScheme.dto.packageName}.*;
import com.acooly.core.common.facade.*;

import ${nameScheme.domainPackage}.${nameScheme.domainClassName};
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.core.utils.validate.Validators;
import com.acooly.module.appservice.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ${table.comment} RemoteService接口
 * 注意：这你采用@Component本地服务化，可替换为dubbo的@Service
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Slf4j
@Component
public class ${nameScheme.facadeImpl.className} implements ${nameScheme.facade.className} {
    <#assign domainVar='${nameScheme.domainClassName?uncap_first}'>
    @Autowired
    private ${nameScheme.domainClassName}Service ${domainVar}Service;

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    @Override
    @AppService
    public PageResult<${nameScheme.domainClassName}ListInfo> list(PageOrder pageOrder){
        PageInfo<${nameScheme.domainClassName}> pageInfo = ${domainVar}Service.query(pageOrder.getPageInfo(),
            pageOrder.getMap(), pageOrder.getSortMap());
        return PageResult.from(pageInfo, ${nameScheme.domainClassName}ListInfo.class);
    }

    /**
     * 新增
     *
     * @param order
     * @return
     */
    @Override
    @AppService
    public ResultBase create(SingleOrder<${nameScheme.domainClassName}CreateInfo> order){
        Validators.assertJSR303(order.getDto());
        ${nameScheme.domainClassName} entity = new ${nameScheme.domainClassName}();
        BeanCopier.copy(order.getDto(), entity);
        ${domainVar}Service.save(entity);
        return new ResultBase();
    }

    /**
     * 根据ID查询项目详情
     *
     * @param order
     * @return
     */
     @Override
     @AppService
     public SingleResult<${nameScheme.domainClassName}Info> read(SingleOrder<Long> order){
        Long id = order.getDto();
        ${nameScheme.domainClassName} entity = ${domainVar}Service.get(id);
        if (entity == null) {
            log.warn("${table.comment} ID对应的合作方不存在。 ID:{}", id);
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST, "ID对应的合作方不存在");
        }

        return SingleResult.from(entity, ${nameScheme.domainClassName}Info.class,
                (${nameScheme.domainClassName} pp, ${nameScheme.domainClassName}Info ${domainVar}Info) -> {
                    // 属性BeanCopies后的后置补充处理，比如替换或转换值。
                    return ${domainVar}Info;
                });
     }


    /**
     * 更新
     *
     * @param order
     * @return
     */
    @Override
    @AppService
    public ResultBase update(SingleOrder<${nameScheme.domainClassName}UpdateInfo> order){
       Validators.assertJSR303(order.getDto());
       Long id = order.getDto().getId();
       ${nameScheme.domainClassName} entity = ${domainVar}Service.get(id);
       BeanCopier.copy(order.getDto(), entity);
       ${domainVar}Service.update(entity);
       return new ResultBase();
    }

    /**
     * 根据ID删除
     *
     * @param order
     * @return
     */
    @Override
    @AppService
    public ResultBase delete(SingleOrder<Long> order){
        ${domainVar}Service.removeById(order.getDto());
        return new ResultBase();
    }
}