package com.acooly.module.coder.module.impl;

import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.module.FreeMarkerModuleGenerator;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class MyBatisDaoModuleGenerator extends FreeMarkerModuleGenerator {

	{
		templateName = "mybatisDao.ftl";
	}

	@Override
	protected String getOutputPath(GenerateContext generateContext, String temp) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getDaoPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String temp) {
		return generateContext.getNameScheme().getDaoClassName() + ".java";
	}

}
