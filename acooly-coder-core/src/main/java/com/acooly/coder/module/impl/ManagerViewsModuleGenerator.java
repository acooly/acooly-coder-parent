package com.acooly.coder.module.impl;

import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;

public class ManagerViewsModuleGenerator extends AbstractModuleGenerator {

	{
		templateName = "listPage.ftl,editPage.ftl,showPage.ftl,importPage.ftl";
	}
	
	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		return generateContext.getNameScheme().getPagePath();
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		if (template.equalsIgnoreCase("listPage.ftl")) {
			return generateContext.getNameScheme().getListPageName();
		} else if (template.equalsIgnoreCase("editPage.ftl")) {
			return generateContext.getNameScheme().getEditPageName();
		} else if (template.equalsIgnoreCase("showPage.ftl")) {
			return generateContext.getNameScheme().getShowPageName();
		} else {
			return generateContext.getNameScheme().getImportPageName();
		}
	}

	@Override
	public String getGenerateKey() {
		return GenerateKeys.manageView.name();
	}
}
