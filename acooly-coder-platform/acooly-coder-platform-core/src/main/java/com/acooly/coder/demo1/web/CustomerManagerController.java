/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo1.web;

import com.acooly.coder.demo1.common.enums.CustomerTypeEnum;
import com.acooly.coder.demo1.common.enums.IdcardTypeEnum;
import com.acooly.coder.demo1.entity.Customer;
import com.acooly.coder.demo1.service.CustomerService;
import com.acooly.core.common.web.AbstractJsonEntityController;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 代码生成客户信息 管理控制器
 *
 * @author acooly
 * @date 2023-06-16 15:53:17
 */
@Controller
@RequestMapping(value = "/manage/coder/demo1/customer")
public class CustomerManagerController extends AbstractJsonEntityController<Customer, CustomerService> {

	private static Map<Integer, String> allNumStatuss = Maps.newLinkedHashMap();
	static {
		allNumStatuss.put(1, "A");
		allNumStatuss.put(2, "B");
		allNumStatuss.put(3, "C类型");
	}

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerService customerService;


	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allIdcardTypes", IdcardTypeEnum.mapping());
		model.put("allCustomerTypes", CustomerTypeEnum.mapping());
		model.put("allNumStatuss", allNumStatuss);
	}

}
