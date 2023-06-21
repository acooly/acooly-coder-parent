/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 *
 */
package com.acooly.coder.demo.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.coder.demo.entity.CoderCustomer;
    import com.acooly.coder.demo.common.enums.IdcardTypeEnum;
/**
 * 代码生成客户信息 Service接口
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
public interface CoderCustomerService extends EntityService<CoderCustomer> {

    /**
     * 唯一性查询
     * UK_username
     *
     * @param username
     * @return
     */
    CoderCustomer uniqueByUsername(String username);

    /**
     * 唯一性查询
     * UK_USER_ID
     *
     * @param idcardType
     * @param idcardNo
     * @return
     */
    CoderCustomer uniqueByIdcardTypeAndIdcardNo(IdcardTypeEnum idcardType, String idcardNo);

}
