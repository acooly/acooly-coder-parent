/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-15
*/
package com.acooly.coder.test.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.acooly.module.ofile.OFileProperties;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.coder.test.entity.CodeCustomer;
import com.acooly.coder.test.service.CodeCustomerService;
import com.acooly.core.common.enums.Gender;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.coder.test.common.enums.IdcardTypeEnum;
import com.acooly.coder.test.common.enums.CustomerTypeEnum;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.utils.enums.SimpleStatus;

import com.google.common.collect.Maps;

/**
 * acoolycoder测试 管理控制器
 *
 * @author acooly
 * @date 2021-04-15 17:29:16
 */
@Controller
@RequestMapping(value = "/manage/coder/codeCustomer")
public class CodeCustomerManagerController extends AbstractJsonEntityController<CodeCustomer, CodeCustomerService> {

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
	private CodeCustomerService codeCustomerService;

    @Autowired
    private OFileProperties oFileProperties;

    @Override
    protected CodeCustomer onSave(HttpServletRequest request, HttpServletResponse response, Model model, CodeCustomer entity, boolean isCreate) throws Exception {
        // 设置上传文件的根存储路径
		UploadConfig uploadConfig = getUploadConfig();
		uploadConfig.setUseMemery(false);
        uploadConfig.setStorageRoot(oFileProperties.getStorageRoot());
		// 上传文件，相对路径绑定到对应的属性
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
