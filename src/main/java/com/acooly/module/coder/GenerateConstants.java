/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月1日
 *
 */
package com.acooly.module.coder;

import com.acooly.module.coder.support.ConfigurableConstants;

/**
 * @author zhangpu
 */
public class GenerateConstants extends ConfigurableConstants {

	static {
		initWithProfile("application.properties");
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
	public static final String GENERATOR_WEBAPP_PATH = getProperty("generator.webappPath", "src/main/resources/META-INF/resources/WEB-INF/jsp");
	public static final String GENERATOR_VIEW_SUFFIX = getProperty("generator.viewSuffix", ".jsp");
	public static final String GENERATOR_TEMPLATE_PATH = getProperty("generator.templatePath",
			"classpath:/template/easyui");

	// code generator configurations
	public static final String GENERATOR_WORKSPACE = getProperty("generator.workspace", "");
	public static final String GENERATOR_ROOT_PACKAGE = getProperty("generator.rootPackage", "");
	public static final String GENERATOR_PAGE_PATH = getProperty("generator.pagePath", "");
	public static final String GENERATOR_IGNOR_PREFIX = getProperty("generator.tableToEntityIgnorPrefix", "");

	public static final String GENERATOR_CODE_COPYRIGHT = getProperty("generator.code.copyright", "acooly.cn");
	public static final String GENERATOR_CODE_AUTHOR = getProperty("generator.code.author", "acooly");
	public static final String GENERATOR_DATATYPE_DECLARE = getProperty("generator.dataType.declare", "");

	public static final String GENERATOR_PERSISTENT_SOLUTION = getProperty("generator.persistent.solution", "jpa");


}
