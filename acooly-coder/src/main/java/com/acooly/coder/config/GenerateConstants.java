/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu
 * date:2016年7月1日
 *
 */
package com.acooly.coder.config;

import com.acooly.coder.module.GenerateModule;
import com.acooly.coder.support.ConfigurableConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangpu
 */
public class GenerateConstants extends ConfigurableConstants {

    static {
        initWithProfile("acoolycoder.properties");
    }

    // database configurations
    public static final String JDBC_DRIVER = getProperty("jdbc.driver", "com.mysql.jdbc.Driver");
    public static final String JDBC_URL = getProperty("jdbc.url", "");
    public static final String JDBC_USERNAME = getProperty("jdbc.username", "");
    public static final String JDBC_PASSWORD = getProperty("jdbc.password", "***");

    // maven project struction
    public static final String GENERATOR_CODE_PATH = getProperty("generator.codePath", "src/main/java");
    public static final String GENERATOR_TEST_PATH = getProperty("generator.testPath", "src/test/java");
    public static final String GENERATOR_RESOURCE_PATH = getProperty("generator.resourcePath", "src/main/resources");
    //视图渲染类型：jsp(默认)/freemarker
    public static final String GENERATOR_VIEW_TYPE = getProperty("generator.viewType", "freemaker");

    public static final String GENERATOR_WEBAPP_PATH = getProperty("generator.webappPath", "src/main/resources/META-INF/resources/WEB-INF/jsp");
    public static final String GENERATOR_VIEW_SUFFIX = getProperty("generator.viewSuffix", ".jsp");
    public static final String GENERATOR_TEMPLATE_PATH = getProperty("generator.templatePath",
            "classpath:/template/easyui");

    // code generator configurations
    public static final String GENERATOR_MODULES = getProperty("generator.modules", "");
    public static final String GENERATOR_WORKSPACE = getProperty("generator.workspace", "");
    public static final String GENERATOR_ROOT_PACKAGE = getProperty("generator.rootPackage", "");
    public static final String GENERATOR_MANAGE_PATH = getProperty("generator.manage.path", "/manage/");
    public static final String GENERATOR_PORTAL_PATH = getProperty("generator.portal.path", "/portal/");
    public static final String GENERATOR_IGNOR_PREFIX = getProperty("generator.tableToEntityIgnorPrefix", "");

    public static final String GENERATOR_CODE_COPYRIGHT = getProperty("generator.code.copyright", "acooly.cn");
    public static final String GENERATOR_CODE_AUTHOR = getProperty("generator.code.author", "acooly");
    public static final String GENERATOR_DATATYPE_DECLARE = getProperty("generator.dataType.declare", "");

    public static final String GENERATOR_PERSISTENT_SOLUTION = getProperty("generator.persistent.solution", "jpa");
    public static final boolean GENERATOR_ENUM_NAME_ASSEMBLE = Boolean.valueOf(getProperty("generator.enumName.assemble", "false"));
    public static final boolean GENERATOR_ENUM_ENABLE = Boolean.valueOf(getProperty("generator.enum.enable", "true"));




    public static Set<GenerateModule> getConfigGeneratorModules() {
        Set<GenerateModule> moduleList = new HashSet<>();
        GenerateModule gm = null;
        for (String m : StringUtils.split(GENERATOR_MODULES, ",")) {
            gm = GenerateModule.withOf(m);
            if (gm == null) {
                logger.info("模块配置了无效值,自动忽略:" + m);
            } else {
                moduleList.add(gm);
            }
        }
        return moduleList;
    }

}