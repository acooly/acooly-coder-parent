/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 */
package com.acooly.coder.demo.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.coder.demo.service.CoderCustomerService;
import com.acooly.coder.demo.dao.CoderCustomerDao;
import com.acooly.coder.demo.entity.CoderCustomer;
    import com.acooly.coder.demo.common.enums.IdcardTypeEnum;

/**
 * 代码生成客户信息 Service实现
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
@Service("coderCustomerService")
public class CoderCustomerServiceImpl extends EntityServiceImpl<CoderCustomer, CoderCustomerDao> implements CoderCustomerService {

    /**
     * 唯一性查询
     * UK_username
     *
     * @param username
     * @return
     */
    @Override
    public CoderCustomer uniqueByUsername(String username) {
        return getEntityDao().uniqueByUsername(username);
    }

    /**
     * 唯一性查询
     * UK_USER_ID
     *
     * @param idcardType
     * @param idcardNo
     * @return
     */
    @Override
    public CoderCustomer uniqueByIdcardTypeAndIdcardNo(IdcardTypeEnum idcardType, String idcardNo) {
        return getEntityDao().uniqueByIdcardTypeAndIdcardNo(idcardType, idcardNo);
    }

}
