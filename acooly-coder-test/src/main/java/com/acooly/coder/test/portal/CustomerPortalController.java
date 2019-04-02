/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-04-02
 */
package com.acooly.coder.test.portal;

import com.acooly.coder.test.entity.Customer;
import com.acooly.coder.test.enums.CustomerTypeEnum;
import com.acooly.coder.test.enums.IdcardTypeEnum;
import com.acooly.coder.test.service.CustomerService;
import com.acooly.core.common.web.AbstractPortalController;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * dm_customer 管理控制器
 *
 * @author acooly
 * Date: 2019-04-02 21:08:03
 */
@Controller
@RequestMapping(value = "/portal/coder/test/customer")
public class CustomerPortalController extends AbstractPortalController<Customer, CustomerService> {

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
        // 前端需考虑安全问题，请按需配置抽象封装的可访问方法
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private CustomerService customerService;


    /**
     * 首页
     */
    @Override
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 默认列表查询
        return super.list(request, response, model);
    }




    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allGenders", allGenders);
        model.put("allIdcardTypes", IdcardTypeEnum.mapping());
        model.put("allCustomerTypes", CustomerTypeEnum.mapping());
        model.put("allStatuss", allStatuss);
    }

}
