/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月1日
 *
 */
package com.acooly.module.coder.module;

import java.util.HashMap;
import java.util.Map;

import com.acooly.module.coder.module.impl.DaoTestModuleGenerator;
import com.acooly.module.coder.module.impl.DomainModuleGenerator;
import com.acooly.module.coder.module.impl.EnumModuleGenerator;
import com.acooly.module.coder.module.impl.JpaDaoModuleGenerator;
import com.acooly.module.coder.module.impl.ManagerControllerModuleGenerator;
import com.acooly.module.coder.module.impl.ManagerViewsModuleGenerator;
import com.acooly.module.coder.module.impl.ServiceImplModuleGenerator;
import com.acooly.module.coder.module.impl.ServiceInterfaceModuleGenerator;

/**
 * @author zhangpu
 */
public class ModuleGeneratorFactory {

	static Map<String, ModuleGenerator> modules = new HashMap<String, ModuleGenerator>();

	static {
		register("domain", new DomainModuleGenerator());
		register("enum", new EnumModuleGenerator());
		register("dao", new JpaDaoModuleGenerator());
		register("daoTest", new DaoTestModuleGenerator());
		register("service", new ServiceInterfaceModuleGenerator());
		register("serviceImpl", new ServiceImplModuleGenerator());
		register("managerController", new ManagerControllerModuleGenerator());
		register("managerViews", new ManagerViewsModuleGenerator());
	}

	public static void register(String name, ModuleGenerator moduleGenerator) {
		modules.put(name, moduleGenerator);
	}

	public static Map<String, ModuleGenerator> getModuleGenerators() {
		return modules;
	}
}
