package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class ServiceInterfaceModuleGenerator extends AbstractModuleGenerator {

	{
		templateName = "service.ftl";
	}
	
	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getServicePackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return generateContext.getNameScheme().getServiceClassName() + ".java";
	}

	@Override
	public String getGenerateKey() {
		return GenerateKeys.service.name();
	}

}
