package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.domain.Column;
import com.acooly.coder.domain.ColumnType;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class ManagerControllerModuleGenerator extends AbstractModuleGenerator {

	{
		templateName = "controller.ftl";
	}


	@Override
	protected String getOutputPath(GenerateContext generateContext, String temp) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getControllerPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String temp) {
		return generateContext.getNameScheme().getControllerClassName() + ".java";
	}

	@Override
	public String getGenerateKey() {
		return GenerateKeys.manageController.name();
	}

	@Override
	protected void onGenerate(GenerateContext generateContext) {
		boolean includeFile = false;
		for (Column column : generateContext.getTable().getColumns()) {
			if (column.getColumnType() == ColumnType.file) {
				includeFile = true;
				break;
			}
		}
		generateContext.getData().put("includeFile", includeFile);
	}

}
