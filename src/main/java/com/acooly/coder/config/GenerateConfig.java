package com.acooly.coder.config;

import java.beans.Transient;
import java.util.Set;

import com.acooly.coder.module.GenerateModule;

public class GenerateConfig {

	/** 工作目录 */
	private String workspace = GenerateConstants.GENERATOR_WORKSPACE;
	/** 目标根package */
	private String rootPackage = GenerateConstants.GENERATOR_ROOT_PACKAGE;

	private String codePath = GenerateConstants.GENERATOR_CODE_PATH;

	private String resourcePath = GenerateConstants.GENERATOR_RESOURCE_PATH;

	private String webappPath = GenerateConstants.GENERATOR_WEBAPP_PATH;

	private String managePath = GenerateConstants.GENERATOR_MANAGE_PATH;

    private String portalPath = GenerateConstants.GENERATOR_PORTAL_PATH;

	private String testPath = GenerateConstants.GENERATOR_TEST_PATH;

	private String templatePath = GenerateConstants.GENERATOR_TEMPLATE_PATH;

	/** 表名转实体名忽略前缀 */
	private String tableToEntityIgnorPrefix = GenerateConstants.GENERATOR_IGNOR_PREFIX;

	/** 输出视图扩展名 */
	private String viewSuffix = GenerateConstants.GENERATOR_VIEW_SUFFIX;

	private DatabaseConfig databaseConfig = new DatabaseConfig();

	/** 代码版权 */
	private String codeCopyright = GenerateConstants.GENERATOR_CODE_COPYRIGHT;
	/** 代码作者 */
	private String codeAuthor = GenerateConstants.GENERATOR_CODE_AUTHOR;
	/** 自定义数据类型转换 */
	private String dateTypeDeclare = GenerateConstants.GENERATOR_DATATYPE_DECLARE;

	private String persistentSolution = GenerateConstants.GENERATOR_PERSISTENT_SOLUTION;

	private Set<GenerateModule> generatorModules = GenerateConstants.getConfigGeneratorModules();

	private static GenerateConfig generateConfiguration = new GenerateConfig();

	public static GenerateConfig INSTANCE() {
		return generateConfiguration;
	}

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

	@Transient
	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	@Transient
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@Transient
	public String getWebappPath() {
		return webappPath;
	}

	public void setWebappPath(String webappPath) {
		this.webappPath = webappPath;
	}

	@Transient
	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

    @Transient
    public String getManagePath() {
        return managePath;
    }

    public void setManagePath(String managePath) {
        this.managePath = managePath;
    }
    @Transient
    public String getPortalPath() {
        return portalPath;
    }

    public void setPortalPath(String portalPath) {
        this.portalPath = portalPath;
    }

    @Transient
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

	@Transient
	public String getViewSuffix() {
		return viewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.viewSuffix = viewSuffix;
	}

	public DatabaseConfig getDatabaseConfig() {
		return databaseConfig;
	}

	public void setDatabaseConfig(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	public String getCodeCopyright() {
		return codeCopyright;
	}

	public void setCodeCopyright(String codeCopyright) {
		this.codeCopyright = codeCopyright;
	}

	public String getCodeAuthor() {
		return codeAuthor;
	}

	public void setCodeAuthor(String codeAuthor) {
		this.codeAuthor = codeAuthor;
	}

	public String getDateTypeDeclare() {
		return dateTypeDeclare;
	}

	public void setDateTypeDeclare(String dateTypeDeclare) {
		this.dateTypeDeclare = dateTypeDeclare;
	}

	public String getPersistentSolution() {
		return persistentSolution;
	}

	public void setPersistentSolution(String persistentSolution) {
		this.persistentSolution = persistentSolution;
	}

    public Set<GenerateModule> getGeneratorModules() {
        return generatorModules;
    }

    public static GenerateConfig getGenerateConfiguration() {
        return generateConfiguration;
    }



    @Override
	public String toString() {
		return String.format(
				"{\n  workspace: %s \n  rootPackage: %s \n  persistent: %s \n  modules: %s  \n  tableToEntityIgnorPrefix: %s \n  databaseConfig: %s\n}",
				workspace, rootPackage, persistentSolution, generatorModules, tableToEntityIgnorPrefix, databaseConfig);
	}

}