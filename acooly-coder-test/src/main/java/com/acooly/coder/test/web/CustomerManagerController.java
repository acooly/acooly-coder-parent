/*
* acooly.cn Inc.
* Copyright (c) 2020 All Rights Reserved.
* create by acooly
* date:2020-05-02
*/
package com.acooly.coder.test.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.coder.test.entity.Customer;
import com.acooly.coder.test.service.CustomerService;
import com.acooly.core.common.enums.Gender;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.coder.test.enums.IdcardTypeEnum;
import com.acooly.coder.test.enums.CustomerTypeEnum;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.utils.enums.SimpleStatus;

import com.google.common.collect.Maps;

/**
 * acoolycoder测试 管理控制器
 * 
 * @author acooly
 * @date 2020-05-02 02:00:37
 */
@Controller
@RequestMapping(value = "/manage/coder/customer")
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
		model.put("allGenders", Gender.mapping());
		model.put("allAnimals", AnimalSign.mapping());
		model.put("allIdcardTypes", IdcardTypeEnum.mapping());
		model.put("allCustomerTypes", CustomerTypeEnum.mapping());
		model.put("allRegistryChannels", ChannelEnum.mapping());
		model.put("allPushAdvs", WhetherStatus.mapping());
		model.put("allNumStatuss", allNumStatuss);
		model.put("allStatuss", SimpleStatus.mapping());
	}

}
