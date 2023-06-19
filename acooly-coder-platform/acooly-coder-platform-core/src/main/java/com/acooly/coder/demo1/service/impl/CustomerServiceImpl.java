/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 */
package com.acooly.coder.demo1.service.impl;

import com.acooly.coder.demo1.dao.CustomerDao;
import com.acooly.coder.demo1.entity.Customer;
import com.acooly.coder.demo1.service.CustomerService;
import com.acooly.core.common.service.EntityServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 代码生成客户信息 Service实现
 *
 * @author acooly
 * @date 2023-06-16 15:53:17
 */
@Service("customerService")
public class CustomerServiceImpl extends EntityServiceImpl<Customer, CustomerDao> implements CustomerService {

}
