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
public class DaoTestModuleGenerator extends FreeMarkerModuleGenerator {

	{
		templateName = "daoTest.ftl";
	}

	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getDaoTestPackage());
		return cfg.getWorkspace() + "/" + cfg.getTestPath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return generateContext.getNameScheme().getDaoTestClassName() + ".java";
	}

}
