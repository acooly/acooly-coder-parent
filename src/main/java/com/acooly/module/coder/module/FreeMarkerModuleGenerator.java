package com.acooly.module.coder.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.acooly.module.coder.GenerateConfiguration;
import com.acooly.module.coder.GenerateContext;
import com.acooly.module.coder.freemarker.DatetimeMethod;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class FreeMarkerModuleGenerator implements ModuleGenerator {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerModuleGenerator.class);

	private GenerateConfiguration generateConfiguration;

	private String templateName;

	@Override
	public void generate(GenerateContext generateContext) {

		try {
			onGenerate(generateContext);

			String[] templates = StringUtils.split(templateName, ",");
			for (String temp : templates) {
				Template template = getTemplate(temp);
				doGenerate(template, generateContext, getOutputPath(generateContext, temp));
				logger.debug("Generate Module Success. -- > " + getClass().getName());
			}

		} catch (Exception e) {
			logger.warn("Generate Module fail.", e);
		}

	}

	protected void onGenerate(GenerateContext generateContext) {

	}

	protected abstract String getOutputPath(GenerateContext generateContext, String template);

	protected abstract String getOutputFile(GenerateContext generateContext, String template);

	protected void doGenerate(Template template, GenerateContext generateContext, String outputPath) {
		Writer out = null;
		try {
			File distPath = new File(outputPath);
			if (!distPath.exists()) {
				distPath.mkdirs();
			}
			File distFile = new File(distPath, getOutputFile(generateContext, template.getName()));
			out = new OutputStreamWriter(new FileOutputStream(distFile, false), "UTF-8");
			template.process(generateContext, out);
			out.flush();
			logger.debug("generate module with template(" + template.getName() + ") to " + distFile.getPath());
		} catch (Exception e) {
			logger.warn("generate module fail. template --> " + template.getName() + "; table --> "
					+ generateContext.getNames().getDomainClassName() + "; outputDir --> " + outputPath, e);
		} finally {
			IOUtils.closeQuietly(out);
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
			logger.error("template file (" + templateName + ") load fail.", e);
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
				Resource path = new DefaultResourceLoader().getResource(templatePath);
				cfg.setDirectoryForTemplateLoading(path.getFile());
			}
			return cfg;
		} catch (Exception e) {
			logger.error("create freemarker configuration fail. templateDirectory: " + templatePath, e);
			return null;
		}
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public void setGenerateConfiguration(GenerateConfiguration generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public GenerateConfiguration getGenerateConfiguration() {
		return generateConfiguration;
	}

}
