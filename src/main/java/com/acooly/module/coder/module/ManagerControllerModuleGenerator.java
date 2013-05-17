package com.acooly.module.coder.module;

import com.acooly.module.coder.GenerateConfiguration;
import com.acooly.module.coder.GenerateContext;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class ManagerControllerModuleGenerator extends FreeMarkerModuleGenerator {

	@Override
	protected String getOutputPath(GenerateContext generateContext, String temp) {
		GenerateConfiguration cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNames().getControllerPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String temp) {
		return generateContext.getNames().getControllerClassName() + ".java";
	}

}
