/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by acooly
 * date:2021-04-15
 */
package com.acooly.coder.test.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.coder.test.service.CodeCustomerService;
import com.acooly.coder.test.dao.CodeCustomerDao;
import com.acooly.coder.test.entity.CodeCustomer;

/**
 * acoolycoder测试 Service实现
 *
 * @author acooly
 * @date 2021-04-15 17:29:17
 */
@Service("codeCustomerService")
public class CodeCustomerServiceImpl extends EntityServiceImpl<CodeCustomer, CodeCustomerDao> implements CodeCustomerService {

}
