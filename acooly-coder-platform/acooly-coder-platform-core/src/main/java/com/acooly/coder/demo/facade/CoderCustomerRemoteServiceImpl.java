/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 *
 */
package com.acooly.coder.demo.facade;

import com.acooly.coder.demo.facade.api.*;
import com.acooly.coder.demo.common.dto.*;
import com.acooly.core.common.facade.*;

import com.acooly.coder.demo.entity.CoderCustomer;
import com.acooly.coder.demo.service.CoderCustomerService;

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
 * 代码生成客户信息 RemoteService接口
 * 注意：这你采用@Component本地服务化，可替换为dubbo的@Service
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
@Slf4j
@Component
public class CoderCustomerRemoteServiceImpl implements CoderCustomerRemoteService {
    @Autowired
    private CoderCustomerService coderCustomerService;

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    @Override
    @AppService
    public PageResult<CoderCustomerListInfo> list(PageOrder pageOrder){
        PageInfo<CoderCustomer> pageInfo = coderCustomerService.query(pageOrder.getPageInfo(),
            pageOrder.getMap(), pageOrder.getSortMap());
        return PageResult.from(pageInfo, CoderCustomerListInfo.class);
    }

    /**
     * 新增
     *
     * @param order
     * @return
     */
    @Override
    @AppService
    public ResultBase create(SingleOrder<CoderCustomerCreateInfo> order){
        Validators.assertJSR303(order.getDto());
        CoderCustomer entity = new CoderCustomer();
        BeanCopier.copy(order.getDto(), entity);
        coderCustomerService.save(entity);
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
     public SingleResult<CoderCustomerInfo> read(SingleOrder<Long> order){
        Long id = order.getDto();
        CoderCustomer entity = coderCustomerService.get(id);
        if (entity == null) {
            log.warn("代码生成客户信息 ID对应的合作方不存在。 ID:{}", id);
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST, "ID对应的合作方不存在");
        }

        return SingleResult.from(entity, CoderCustomerInfo.class,
                (CoderCustomer pp, CoderCustomerInfo coderCustomerInfo) -> {
                    // 属性BeanCopies后的后置补充处理，比如替换或转换值。
                    return coderCustomerInfo;
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
    public ResultBase update(SingleOrder<CoderCustomerUpdateInfo> order){
       Validators.assertJSR303(order.getDto());
       Long id = order.getDto().getId();
       CoderCustomer entity = coderCustomerService.get(id);
       BeanCopier.copy(order.getDto(), entity);
       coderCustomerService.update(entity);
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
        coderCustomerService.removeById(order.getDto());
        return new ResultBase();
    }
}