/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月1日
 *
 */
package com.acooly.module.coder.module;

import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.module.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 */
public class ModuleGeneratorFactory {

    static Map<String, ModuleGenerator> modules = new HashMap<String, ModuleGenerator>();

    static {
        registryDefault();
    }

    public static void registryDefault() {
        register("domain", new DomainModuleGenerator());
        register("enum", new EnumModuleGenerator());
        register("dao", new JpaDaoModuleGenerator());
        //register("daoTest", new DaoTestModuleGenerator());
        register("service", new ServiceInterfaceModuleGenerator());
        register("serviceImpl", new ServiceImplModuleGenerator());
        register("managerController", new ManagerControllerModuleGenerator());
        register("managerViews", new ManagerViewsModuleGenerator());
    }


    public static Map<String, ModuleGenerator> registies(GenerateConfig config) {
        modules.clear();
        register("domain", new DomainModuleGenerator());
        register("enum", new EnumModuleGenerator());
        if ("jpa".equalsIgnoreCase(config.getPersistentSolution())) {
            register("dao", new JpaDaoModuleGenerator());
        } else {
            register("mybatisDao", new MyBatisDaoModuleGenerator());
        }
        //register("daoTest", new DaoTestModuleGenerator());
        register("service", new ServiceInterfaceModuleGenerator());
        register("serviceImpl", new ServiceImplModuleGenerator());
        register("managerController", new ManagerControllerModuleGenerator());
        register("managerViews", new ManagerViewsModuleGenerator());
        return modules;
    }


    public static void register(String name, ModuleGenerator moduleGenerator) {
        modules.put(name, moduleGenerator);
    }

    public static Map<String, ModuleGenerator> getModuleGenerators() {
        return modules;
    }
}
