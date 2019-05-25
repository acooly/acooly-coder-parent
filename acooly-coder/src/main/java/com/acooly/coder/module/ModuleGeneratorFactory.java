/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu
 * date:2016年7月1日
 *
 */
package com.acooly.coder.module;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.module.impl.*;

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
        registerService(null);
        registerManage();
    }

    public static Map<String, ModuleGenerator> registies(GenerateConfig config) {
        modules.clear();
        registerService(config);
        if (config.getGeneratorModules().contains(GenerateModule.Manage)) {
            registerManage();
        }
        if (config.getGeneratorModules().contains(GenerateModule.Portal)) {
            registerPortal();
        }
        if (config.getGeneratorModules().contains(GenerateModule.Facade)) {
            registerFacade();
        }
        if (config.getGeneratorModules().contains(GenerateModule.OpenApi)) {
            registerOpenApi();
        }
        return modules;
    }


    private static void registerService(GenerateConfig config) {
        register(GenerateKeys.entity.name(), new EntityModuleGenerator());
        register(GenerateKeys.enumClass.name(), new EnumModuleGenerator());
        if (config != null && GenerateKeys.mybatis.name().equalsIgnoreCase(config.getPersistentSolution())) {
            register(GenerateKeys.mybatis.name(), new MyBatisDaoModuleGenerator());
        } else {
            register(GenerateKeys.jpa.name(), new JpaDaoModuleGenerator());
        }
        register(GenerateKeys.service.name(), new ServiceInterfaceModuleGenerator());
        register(GenerateKeys.serviceImpl.name(), new ServiceImplModuleGenerator());
    }


    private static void registerManage() {
        register(GenerateKeys.manageController.name(), new ManagerControllerModuleGenerator());
        register(GenerateKeys.manageView.name(), new ManagerViewsModuleGenerator());
    }

    private static void registerPortal() {
    }

    private static void registerFacade() {
    }

    private static void registerOpenApi() {
        register(GenerateKeys.openapi.name(), new OpenApiModuleGenerator());
    }


    public static void register(String name, ModuleGenerator moduleGenerator) {
        modules.put(name, moduleGenerator);
    }

    public static Map<String, ModuleGenerator> getModuleGenerators() {
        return modules;
    }
}
