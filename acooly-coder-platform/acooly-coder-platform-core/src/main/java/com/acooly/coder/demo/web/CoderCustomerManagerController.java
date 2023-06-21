/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acooly.module.ofile.OFileProperties;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.coder.demo.entity.CoderCustomer;
import com.acooly.coder.demo.service.CoderCustomerService;
import com.acooly.core.common.enums.Gender;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.coder.demo.common.enums.IdcardTypeEnum;
import com.acooly.coder.demo.common.enums.CustomerTypeEnum;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.utils.enums.SimpleStatus;

import com.google.common.collect.Maps;

/**
 * 代码生成客户信息 管理控制器
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
@Controller
@RequestMapping(value = "/manage/coder/demo/coderCustomer")
public class CoderCustomerManagerController extends AbstractJsonEntityController<CoderCustomer, CoderCustomerService> {

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
	private CoderCustomerService coderCustomerService;

    @Autowired
    private OFileProperties oFileProperties;


    @Override
    protected CoderCustomer onSave(HttpServletRequest request, HttpServletResponse response, Model model, CoderCustomer entity, boolean isCreate) throws Exception {
		// 上传文件：设置上传文件的根存储路径，相对路径绑定到对应的属性；
		UploadConfig uploadConfig = getUploadConfig();
        uploadConfig.setStorageRoot(oFileProperties.getStorageRoot());
		uploadConfig.setUseMemery(false);
		doUpload(request, entity);
        return super.onSave(request, response, model, entity, isCreate);
    }



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
        model.put("serverRoot", oFileProperties.getServerRoot());
	}

}
