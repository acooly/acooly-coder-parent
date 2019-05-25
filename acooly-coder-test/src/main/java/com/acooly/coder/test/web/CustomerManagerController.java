/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by acooly
* date:2019-05-25
*/
package com.acooly.coder.test.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.coder.test.entity.Customer;
import com.acooly.coder.test.service.CustomerService;
import com.acooly.coder.test.enums.IdcardTypeEnum;
import com.acooly.coder.test.enums.CustomerTypeEnum;

import com.google.common.collect.Maps;

/**
 * acoolycoder测试 管理控制器
 * 
 * @author acooly
 * Date: 2019-05-25 22:03:58
 */
@Controller
@RequestMapping(value = "/manage/demo/customer")
public class CustomerManagerController extends AbstractJQueryEntityController<Customer, CustomerService> {
	
	private static Map<Integer, String> allGenders = Maps.newLinkedHashMap();
	static {
		allGenders.put(1, "男");
		allGenders.put(2, "女");
		allGenders.put(3, "人妖");
	}
	private static Map<Integer, String> allStatuss = Maps.newLinkedHashMap();
	static {
		allStatuss.put(0, "无效");
		allStatuss.put(1, "有效");
	}

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerService customerService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allGenders", allGenders);
		model.put("allIdcardTypes", IdcardTypeEnum.mapping());
		model.put("allCustomerTypes", CustomerTypeEnum.mapping());
		model.put("allStatuss", allStatuss);
	}

}
