package com.acooly.module.coder.module.impl;

import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.module.FreeMarkerModuleGenerator;

public class PagesModuleGenerator extends FreeMarkerModuleGenerator {


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
}
