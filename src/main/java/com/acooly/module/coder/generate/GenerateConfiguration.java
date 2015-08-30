package com.acooly.module.coder.generate;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GenerateConfiguration {

	/** 工作目录 */
	private String workspace;
	/** 目标根package */
	private String rootPackage;

	private String codePath = "java";

	private String resourcePath = "resources";

	private String webappPath = "webapp";

	private String pagePath = "/admin/";

	private String testPath = "test";

	private String templatePath;

	/** 表名转实体名忽略前缀 */
	private String tableToEntityIgnorPrefix;

	/** 输出视图扩展名 */
	private String viewSuffix = ".jsp";

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getWebappPath() {
		return webappPath;
	}

	public void setWebappPath(String webappPath) {
		this.webappPath = webappPath;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getTestPath() {
		return testPath;
	}

	public void setTestPath(String testPath) {
		this.testPath = testPath;
	}

	public String getTableToEntityIgnorPrefix() {
		return tableToEntityIgnorPrefix;
	}

	public void setTableToEntityIgnorPrefix(String tableToEntityIgnorPrefix) {
		this.tableToEntityIgnorPrefix = tableToEntityIgnorPrefix;
	}

	public String getViewSuffix() {
		return viewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.viewSuffix = viewSuffix;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
