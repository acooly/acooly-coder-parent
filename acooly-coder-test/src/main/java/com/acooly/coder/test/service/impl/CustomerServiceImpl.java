/*
 * qiudot.com Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by qiudot
 * date:2019-04-02
 */
package com.acooly.coder.test.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.coder.test.service.CustomerService;
import com.acooly.coder.test.dao.CustomerDao;
import com.acooly.coder.test.entity.Customer;

/**
 * dm_customer Service实现
 *
 * Date: 2019-04-02 22:43:58
 *
 * @author qiudot
 *
 */
@Service("customerService")
public class CustomerServiceImpl extends EntityServiceImpl<Customer, CustomerDao> implements CustomerService {

}
