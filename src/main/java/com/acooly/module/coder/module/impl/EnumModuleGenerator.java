package com.acooly.module.coder.module.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.domain.Column;
import com.acooly.module.coder.domain.JavaType;
import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.module.FreeMarkerModuleGenerator;
import com.acooly.module.coder.support.GenerateUtils;

import freemarker.template.Template;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class EnumModuleGenerator extends FreeMarkerModuleGenerator {

	{
		templateName = "enum.ftl";
	}

	@Override
	public void generate(GenerateContext generateContext) {
		try {
			String[] templates = StringUtils.split(templateName, ",");
			for (String temp : templates) {
				Template template = getTemplate(temp);
				
				List<Column> columns = generateContext.getTable().getColumns();
				for (Column column : columns) {
					if (column.getDataType().isEnum()) {
						generateContext.appendData("enumColumn", column);
						doGenerate(template, generateContext, getOutputPath(generateContext, temp),
								GenerateUtils.getCanonicalClassFileName(column.getPropertyName()));
					}
				}
			}

			
		} catch (Exception e) {
			logger.warning("Generate Module fail: " + e.getMessage());
		}
	}

	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfig cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNameScheme().getEnumPackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return null;
	}

}
