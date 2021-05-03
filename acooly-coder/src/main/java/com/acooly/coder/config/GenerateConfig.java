package com.acooly.coder.config;

import com.acooly.coder.enums.ProjectModule;
import com.acooly.coder.enums.ViewType;
import com.acooly.coder.module.GenerateModule;
import com.acooly.coder.support.Projects;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.beans.Transient;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

/**
 * 代码生成器配置
 *
 * @author zhangpu
 */
@Getter
@Setter
public class GenerateConfig {

    /**
     * 是否多模块工程
     */
    private boolean multiModule = GenerateConstants.GENERATOR_MULTI_MODULE_ENABLE;

    /**
     * dto
     */
    private String dtoModulePostfix = GenerateConstants.DTO_MODULE_POSTFIX_DEF;

    /**
     * 单点登录支持
     */
    private boolean sso = GenerateConstants.GENERATOR_SSO_ENABLE;

    /**
     * 工作目录
     */
    private String workspace = GenerateConstants.GENERATOR_WORKSPACE;

    private String viewType = GenerateConstants.GENERATOR_VIEW_TYPE;

    /**
     * 目标根package
     */
    private String rootPackage = GenerateConstants.GENERATOR_ROOT_PACKAGE;

    private String codePath = GenerateConstants.GENERATOR_CODE_PATH;

    private String resourcePath = GenerateConstants.GENERATOR_RESOURCE_PATH;

    private String webappPath = GenerateConstants.GENERATOR_WEBAPP_PATH;

    private String managePath = GenerateConstants.GENERATOR_MANAGE_PATH;

    private String portalPath = GenerateConstants.GENERATOR_PORTAL_PATH;

    private String testPath = GenerateConstants.GENERATOR_TEST_PATH;

    private String templatePath = GenerateConstants.GENERATOR_TEMPLATE_PATH;

    /**
     * 表名转实体名忽略前缀
     */
    private String tableToEntityIgnorPrefix = GenerateConstants.GENERATOR_IGNOR_PREFIX;
    private String entityPrefix = GenerateConstants.GENERATOR_ENTITY_PREFIX;

    /**
     * 输出视图扩展名
     */
    private String viewSuffix = GenerateConstants.GENERATOR_VIEW_SUFFIX;

    private DatabaseConfig databaseConfig = new DatabaseConfig();

    /**
     * 代码版权
     */
    private String codeCopyright = GenerateConstants.GENERATOR_CODE_COPYRIGHT;
    /**
     * 代码作者
     */
    private String codeAuthor = GenerateConstants.GENERATOR_CODE_AUTHOR;
    /**
     * 自定义数据类型转换
     */
    private String dateTypeDeclare = GenerateConstants.GENERATOR_DATATYPE_DECLARE;

    private String persistentSolution = GenerateConstants.GENERATOR_PERSISTENT_SOLUTION;

    /**
     * 枚举名称生成是否采用组合名称
     */
    private boolean enumNameAssemble = GenerateConstants.GENERATOR_ENUM_NAME_ASSEMBLE;

    private boolean enumEnable = GenerateConstants.GENERATOR_ENUM_ENABLE;

    private Set<GenerateModule> generatorModules = GenerateConstants.getConfigGeneratorModules();

    private static GenerateConfig generateConfiguration = new GenerateConfig();

    public static GenerateConfig INSTANCE() {
        return generateConfiguration;
    }

    public static GenerateConfig getGenerateConfiguration() {
        return generateConfiguration;
    }

    private GenerateConfig() {
        ViewType viewTypeEnum = ViewType.find(viewType);
        if (viewTypeEnum == null) {
            viewTypeEnum = ViewType.freemarker;
        }
        this.viewSuffix = viewTypeEnum.getSuffix();
        this.templatePath = "classpath:/template/" + viewTypeEnum.getTemplatePath();
        this.webappPath = viewTypeEnum.getWebappPath();
    }


    @Transient
    public String getManagePath() {
        if (StringUtils.endsWith(managePath, "/")) {
            managePath = StringUtils.removeEnd(managePath, "/");
        }
        return managePath;
    }

    /**
     * 工程根路径
     *
     * @return
     */
    public String getProjectPath() {
        return Projects.getProjectPath(this.workspace);
    }

    public String getProjectName() {
        return Projects.getProjectName(this.workspace);
    }


    public File getModulePath(ProjectModule projectModule) {
        String dtoModulePath = getGenerateConfiguration().getProjectPath() + "/" +
                getGenerateConfiguration().getProjectName() + "-" + projectModule.code();
        return new File(dtoModulePath);
    }


    @Override
    public String toString() {
        return String.format(
                "{\n  workspace: %s \n  rootPackage: %s \n  webappPath %s \n  manageViewPath %s \n  persistent: %s \n  modules: %s  \n  tableToEntityIgnorPrefix: %s \n  databaseConfig: %s\n  templatePath: %s\n}",
                workspace, rootPackage, this.webappPath, this.managePath, persistentSolution, generatorModules, tableToEntityIgnorPrefix, databaseConfig, templatePath);
    }

}
