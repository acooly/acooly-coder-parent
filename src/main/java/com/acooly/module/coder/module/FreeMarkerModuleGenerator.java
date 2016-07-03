package com.acooly.module.coder.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.support.DatetimeMethod;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class FreeMarkerModuleGenerator implements ModuleGenerator {

	protected static Logger logger = Logger.getLogger(FreeMarkerModuleGenerator.class.getSimpleName());

	protected GenerateConfig generateConfiguration = GenerateConfig.INSTANCE();

	protected String templateName;

	@Override
	public void generate(GenerateContext generateContext) {

		try {
			onGenerate(generateContext);
			String[] templates = StringUtils.split(templateName, ",");
			for (String temp : templates) {
				Template template = getTemplate(temp);
				doGenerate(template, generateContext, getOutputPath(generateContext, temp));
			}

		} catch (Exception e) {
			logger.warning("Generate Module fail: " + e.getMessage());
		}
	}

	protected void onGenerate(GenerateContext generateContext) {

	}

	protected abstract String getOutputPath(GenerateContext generateContext, String template);

	protected abstract String getOutputFile(GenerateContext generateContext, String template);

	protected void doGenerate(Template template, GenerateContext generateContext, String outputPath) {
		doGenerate(template, generateContext, outputPath, getOutputFile(generateContext, template.getName()));
	}

	protected void doGenerate(Template template, GenerateContext generateContext, String outputPath,
			String outputFile) {
		Writer out = null;
		try {
			File distPath = new File(outputPath);
			if (!distPath.exists()) {
				distPath.mkdirs();
			}
			File distFile = new File(distPath, outputFile);
			out = new OutputStreamWriter(new FileOutputStream(distFile, false), "UTF-8");
			template.process(generateContext, out);
			out.flush();
			// logger.info("generate :" + this.getClass().getSimpleName() + "["
			// + template.getName() + "], file: "
			// + distFile.getPath());
		} catch (Exception e) {
			logger.warning("generate module fail. template " + template.getName() + " table "
					+ generateContext.getNameScheme().getDomainClassName() + " ; outputDir: " + outputPath + ", error:"
					+ e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e2) {
					// ig
				}
			}
		}

	}

	protected String getPackage(String name) {
		return generateConfiguration.getRootPackage() + "." + name;
	}

	protected String getPackagePath(String packageName) {
		return packageName.replaceAll("\\.", "\\/");
	}

	protected Template getTemplate(String templateName) {
		try {
			return getConfiguration().getTemplate(templateName);
		} catch (Exception e) {
			logger.warning("template file (" + templateName + ") load fail: " + e.getMessage());
			throw new RuntimeException("template file (" + templateName + ") load fail.");
		}

	}

	protected Configuration getConfiguration() {

		String templatePath = generateConfiguration.getTemplatePath();
		try {
			Configuration cfg = new Configuration();
			cfg.setSharedVariable("datetime", new DatetimeMethod());
			cfg.setDefaultEncoding("UTF-8");
			if (StringUtils.containsIgnoreCase(templatePath, "classpath:")) {
				templatePath = StringUtils.substringAfter(templatePath, "classpath:");
				if (!StringUtils.startsWith(templatePath, "/")) {
					templatePath = "/" + templatePath;
				}
				cfg.setClassForTemplateLoading(getClass(), templatePath);
			} else {
				cfg.setDirectoryForTemplateLoading(new File(templatePath));
			}
			return cfg;
		} catch (Exception e) {
			logger.warning("create freemarker configuration fail. templateDirectory: " + templatePath + ", message: "
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public void setGenerateConfiguration(GenerateConfig generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public GenerateConfig getGenerateConfiguration() {
		return generateConfiguration;
	}

}
