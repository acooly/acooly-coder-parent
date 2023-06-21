/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 */
 package com.acooly.coder.demo.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.coder.demo.entity.CoderCustomer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.acooly.coder.demo.common.enums.IdcardTypeEnum;

/**
 * 代码生成客户信息 Mybatis Dao
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
public interface CoderCustomerDao extends EntityMybatisDao<CoderCustomer> {

    /**
     * 唯一索引查询: UK_username
     *
     * @param username
     * @return
     */
    @Select("select * from acooly_coder_customer where username=#{username}")
    CoderCustomer uniqueByUsername(@Param("username") String username);

    /**
     * 唯一索引查询: UK_USER_ID
     *
     * @param idcardType
     * @param idcardNo
     * @return
     */
    @Select("select * from acooly_coder_customer where idcard_type=#{idcardType} AND idcard_no=#{idcardNo}")
    CoderCustomer uniqueByIdcardTypeAndIdcardNo(@Param("idcardType") IdcardTypeEnum idcardType, @Param("idcardNo") String idcardNo);

}
