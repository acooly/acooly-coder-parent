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
public class ServiceImplModuleGenerator extends AbstractModuleGenerator {

	{
		templateName = "serviceImpl.ftl";
	}
	
	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getServiceImplPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return generateContext.getNameScheme().getServiceImplClassName() + ".java";
	}

	@Override
	public String getGenerateKey() {
		return GenerateKeys.serviceImpl.name();
	}

}
