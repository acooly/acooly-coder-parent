package com.acooly.module.coder.module;

import com.acooly.module.coder.GenerateContext;

public class PagesModuleGenerator extends FreeMarkerModuleGenerator {


	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		return generateContext.getNames().getPagePath();
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		if (template.equalsIgnoreCase("listPage.ftl")) {
			return generateContext.getNames().getListPageName();
		} else if (template.equalsIgnoreCase("editPage.ftl")) {
			return generateContext.getNames().getEditPageName();
		} else if (template.equalsIgnoreCase("showPage.ftl")) {
			return generateContext.getNames().getShowPageName();
		} else {
			return generateContext.getNames().getImportPageName();
		}
	}
}
