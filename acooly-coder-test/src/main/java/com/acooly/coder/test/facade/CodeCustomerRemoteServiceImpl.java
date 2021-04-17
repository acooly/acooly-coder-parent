/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by acooly
 * date:2021-04-18
 *
 */
package com.acooly.coder.test.facade;

import com.acooly.coder.test.facade.api.*;
import com.acooly.coder.test.common.dto.*;
import com.acooly.core.common.facade.*;

import com.acooly.coder.test.entity.CodeCustomer;
import com.acooly.coder.test.service.CodeCustomerService;

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
 * acoolycoder测试 RemoteService接口
 * 注意：这你采用@Component本地服务化，可替换为dubbo的@Service
 *
 * @author acooly
 * @date 2021-04-18 00:31:50
 */
@Slf4j
@Component
public class CodeCustomerRemoteServiceImpl implements CodeCustomerRemoteService {
    @Autowired
    private CodeCustomerService codeCustomerService;

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    @Override
    @AppService
    public PageResult<CodeCustomerListInfo> list(PageOrder pageOrder){
        PageInfo<CodeCustomer> pageInfo = codeCustomerService.query(pageOrder.getPageInfo(),
            pageOrder.getMap(), pageOrder.getSortMap());
        return PageResult.from(pageInfo, CodeCustomerListInfo.class);
    }

    /**
     * 新增
     *
     * @param order
     * @return
     */
    @Override
    @AppService
    public ResultBase create(SingleOrder<CodeCustomerCreateInfo> order){
        Validators.assertJSR303(order.getDto());
        CodeCustomer entity = new CodeCustomer();
        BeanCopier.copy(order.getDto(), entity);
        codeCustomerService.save(entity);
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
     public SingleResult<CodeCustomerInfo> read(SingleOrder<Long> order){
        Long id = order.getDto();
        CodeCustomer entity = codeCustomerService.get(id);
        if (entity == null) {
            log.warn("acoolycoder测试 ID对应的合作方不存在。 ID:{}", id);
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST, "ID对应的合作方不存在");
        }

        return SingleResult.from(entity, CodeCustomerInfo.class,
                (CodeCustomer pp, CodeCustomerInfo codeCustomerInfo) -> {
                    // 属性BeanCopies后的后置补充处理，比如替换或转换值。
                    return codeCustomerInfo;
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
    public ResultBase update(SingleOrder<CodeCustomerUpdateInfo> order){
       Validators.assertJSR303(order.getDto());
       Long id = order.getDto().getId();
       CodeCustomer entity = codeCustomerService.get(id);
       BeanCopier.copy(order.getDto(), entity);
       codeCustomerService.update(entity);
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
        codeCustomerService.removeById(order.getDto());
        return new ResultBase();
    }
}