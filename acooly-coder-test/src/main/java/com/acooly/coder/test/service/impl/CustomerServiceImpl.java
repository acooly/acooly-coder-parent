/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by acooly
 * date:2020-06-07
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
 * @author acooly
 * @date 2020-06-07 14:53:19
 */
@Service("customerService")
public class CustomerServiceImpl extends EntityServiceImpl<Customer, CustomerDao> implements CustomerService {

}
