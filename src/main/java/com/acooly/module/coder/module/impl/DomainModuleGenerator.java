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
public class DomainModuleGenerator extends FreeMarkerModuleGenerator {

	{
		templateName = "domain.ftl";
	}
	
	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getDomainPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return generateContext.getNameScheme().getDomainClassName() + ".java";
	}

}
