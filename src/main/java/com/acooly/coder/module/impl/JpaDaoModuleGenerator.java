package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.module.GenerateKeys;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class JpaDaoModuleGenerator extends AbstractModuleGenerator {

	{
		templateName = "dao.ftl";
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


	@Override
	public String getGenerateKey() {
		return GenerateKeys.jpa.name();
	}
}
