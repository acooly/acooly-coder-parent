/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-05-25
 */
package com.acooly.coder.test.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.coder.test.service.CustomerService;
import com.acooly.coder.test.dao.CustomerDao;
import com.acooly.coder.test.entity.Customer;

/**
 * acoolycoder测试 Service实现
 *
 * Date: 2019-05-25 22:03:58
 *
 * @author acooly
 *
 */
@Service("customerService")
public class CustomerServiceImpl extends EntityServiceImpl<Customer, CustomerDao> implements CustomerService {

}
