package com.acooly.module.coder.module;

import com.acooly.module.coder.GenerateConfiguration;
import com.acooly.module.coder.GenerateContext;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class JpaDaoModuleGenerator extends FreeMarkerModuleGenerator {

	@Override
	protected String getOutputPath(GenerateContext generateContext, String temp) {
		GenerateConfiguration cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNames().getDaoPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String temp) {
		return generateContext.getNames().getDaoClassName() + ".java";
	}

}
