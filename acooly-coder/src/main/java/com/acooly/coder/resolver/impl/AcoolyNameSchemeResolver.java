package com.acooly.coder.resolver.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.config.GenerateConstants;
import com.acooly.coder.enums.ProjectModule;
import com.acooly.coder.resolver.NameBlock;
import com.acooly.coder.resolver.NameScheme;
import com.acooly.coder.resolver.NameSchemeResolver;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * acooly 命名解析器
 *
 * @author zhangpu
 * @date 2015年8月30日
 */
public class AcoolyNameSchemeResolver implements NameSchemeResolver {

    /**
     * 表或列名单词分割符，每个分割符后的单词首字母大写（驼峰规则）
     */
    private static final String WORD_SEPARATOR = "_";
    private GenerateConfig generateConfiguration = GenerateConfig.INSTANCE();

    private String daoPostfit = "Dao";
    private String daoImplPostfit = "DaoImpl";
    private String daoTestPostfit = "DaoTest";
    private String servicePostfit = "Service";
    private String serviceImplPostfit = "ServiceImpl";
    private String serviceTestPostfit = "ServiceTest";
    private String controllerPostfit = "ManagerController";
    private String listPagePostfit = "";
    private String editPagePostfit = "Edit";
    private String showPagePostfit = "Show";
    private String importPagePostfit = "Import";


    @Override
    public NameScheme resolve(String tableName) {
        NameScheme namesHold = new NameScheme();
        String baseName = convertBaseName(tableName);

        String rootPackage = getGenerateConfiguration().getRootPackage();
        namesHold.setDomainClassName(StringUtils.capitalize(baseName));
        namesHold.setDomainPackage(rootPackage + ".entity");

        // enum
        doEnum(namesHold);
        // dto
        doDto(namesHold);
        // facade
        doFacade(namesHold);

        doOpenApi(namesHold);

        namesHold.setDaoPackage(rootPackage + ".dao");
        namesHold.setDaoClassName(namesHold.getDomainClassName() + daoPostfit);

        namesHold.setDaoImplPackage(rootPackage + ".dao.impl");
        namesHold.setDaoImplClassName(namesHold.getDomainClassName() + daoImplPostfit);

        namesHold.setDaoTestPackage(namesHold.getDaoPackage());
        namesHold.setDaoTestClassName(namesHold.getDomainClassName() + daoTestPostfit);

        namesHold.setServicePackage(rootPackage + ".service");
        namesHold.setServiceClassName(namesHold.getDomainClassName() + servicePostfit);
        namesHold.setServiceImplPackage(rootPackage + ".service.impl");
        namesHold.setServiceImplClassName(namesHold.getDomainClassName() + serviceImplPostfit);

        namesHold.setServiceTestPackage(namesHold.getServicePackage());
        namesHold.setServiceTestClassName(namesHold.getDomainClassName() + serviceTestPostfit);

        namesHold.setControllerPackage(rootPackage + ".web");
        namesHold.setControllerClassName(namesHold.getDomainClassName() + controllerPostfit);

        namesHold.setPagePath(getGenerateConfiguration().getWorkspace() + "/"
                + getGenerateConfiguration().getWebappPath() + "/" + getGenerateConfiguration().getManagePath());
        namesHold.setListPageName(baseName + listPagePostfit + getGenerateConfiguration().getViewSuffix());
        namesHold.setEditPageName(baseName + editPagePostfit + getGenerateConfiguration().getViewSuffix());
        namesHold.setShowPageName(baseName + showPagePostfit + getGenerateConfiguration().getViewSuffix());
        namesHold.setImportPageName(baseName + importPagePostfit + getGenerateConfiguration().getViewSuffix());
        return namesHold;
    }


    /**
     * Dto
     *
     * @param namesHold
     */
    protected void doDto(NameScheme namesHold) {
        String rootPackage = getConfig().getRootPackage();
        namesHold.getDto().setModule(getConfig().getWorkspace());
        namesHold.getDto().setPackageName(rootPackage + ".dto");
        File file = getConfig().getModulePath(ProjectModule.common);
        if (getConfig().isMultiModule() && file.exists()) {
            namesHold.getDto().setModule(file.getPath());
            namesHold.getDto().setPackageName(rootPackage + "." + ProjectModule.common + ".dto");
        }
    }

    /**
     * enum
     *
     * @param namesHold
     */
    protected void doEnum(NameScheme namesHold) {
        NameBlock block = namesHold.getEnums();
        block.setModule(getConfig().getWorkspace());
        block.setPackageName(getConfig().getRootPackage() + ".enums");
        File file = getConfig().getModulePath(ProjectModule.common);
        if (getConfig().isMultiModule() && file.exists()) {
            block.setModule(file.getPath());
            block.setPackageName(getConfig().getRootPackage() + "." + ProjectModule.common + ".enums");
        }
    }

    protected void doFacade(NameScheme namesHold) {
        // interface
        NameBlock block = namesHold.getFacade();
        block.setModule(getConfig().getWorkspace());
        block.setClassName(namesHold.getDomainClassName() + "RemoteService");
        block.setPackageName(getConfig().getRootPackage() + "." + ProjectModule.facade.getPackageName() + ".api");
        File file = getConfig().getModulePath(ProjectModule.facade);
        if (getConfig().isMultiModule() && file.exists()) {
            block.setModule(file.getPath());
        }
        // implements
        NameBlock facadeImpl = namesHold.getFacadeImpl();
        facadeImpl.setModule(getConfig().getWorkspace());
        facadeImpl.setClassName(namesHold.getDomainClassName() + "RemoteServiceImpl");
        facadeImpl.setPackageName(getConfig().getRootPackage() + "." + ProjectModule.facade.getPackageName());
    }


    protected void doOpenApi(NameScheme namesHold) {
        GenerateConfig config = getConfig();
        File file = getConfig().getModulePath(ProjectModule.openapi);
        // openapi-message
        NameBlock openapiMessage = namesHold.getOpenapiMessage();
        openapiMessage.setModule(config.getWorkspace());
        openapiMessage.setPackageName(config.getRootPackage() + "." + ProjectModule.openapi_message.getPackageName());
        File fileMessage = new File(file, config.getProjectName() + "-" + ProjectModule.openapi_message.code());
        if (getConfig().isMultiModule() && fileMessage.exists()) {
            openapiMessage.setModule(fileMessage.getPath());
        }

        // openapi-service
        NameBlock openapiService = namesHold.getOpenapiService();
        openapiService.setModule(config.getWorkspace());
        openapiService.setPackageName(config.getRootPackage() + "." + ProjectModule.openapi_service.getPackageName());
        File fileService = new File(file, config.getProjectName() + "-" + ProjectModule.openapi_service.code());
        if (getConfig().isMultiModule() && fileService.exists()) {
            openapiService.setModule(fileService.getPath());
        }

        // openapi-service
        NameBlock openapiTest = namesHold.getOpenapiTest();
        openapiTest.setModule(config.getWorkspace());
        openapiTest.setPackageName(config.getRootPackage() + "." + ProjectModule.openapi_test.getPackageName());
        File fileTest = new File(file, config.getProjectName() + "-" + ProjectModule.openapi_test.code());
        if (getConfig().isMultiModule() && fileTest.exists()) {
            openapiTest.setModule(fileTest.getPath());
        }
    }

    /**
     * 表名转换为基础变量名称
     *
     * @param tableName
     * @return
     */
    private String convertBaseName(String tableName) {
        String baseName = tableName.toLowerCase();

        if (StringUtils.isNotBlank(generateConfiguration.getTableToEntityIgnorPrefix())) {
            String ignorPrefix = generateConfiguration.getTableToEntityIgnorPrefix().toLowerCase();
            if (baseName.startsWith(ignorPrefix)) {
                baseName = StringUtils.substringAfter(baseName, ignorPrefix);
            }
        }

        if (baseName.startsWith(WORD_SEPARATOR)) {
            baseName = StringUtils.substringAfter(baseName, WORD_SEPARATOR);
        }
        while (baseName.contains(WORD_SEPARATOR)) {
            baseName = StringUtils.substringBefore(baseName, WORD_SEPARATOR)
                    + StringUtils.capitalize(StringUtils.substringAfter(baseName, WORD_SEPARATOR));
        }

        if (StringUtils.isNotBlank(generateConfiguration.getEntityPrefix())) {
            baseName = StringUtils.capitalize(generateConfiguration.getEntityPrefix())
                    + StringUtils.capitalize(baseName);
        }
        return baseName;
    }

    public String getDaoPostfit() {
        return daoPostfit;
    }

    public void setDaoPostfit(String daoPostfit) {
        this.daoPostfit = daoPostfit;
    }

    public String getDaoImplPostfit() {
        return daoImplPostfit;
    }

    public void setDaoImplPostfit(String daoImplPostfit) {
        this.daoImplPostfit = daoImplPostfit;
    }

    public String getDaoTestPostfit() {
        return daoTestPostfit;
    }

    public void setDaoTestPostfit(String daoTestPostfit) {
        this.daoTestPostfit = daoTestPostfit;
    }

    public String getServicePostfit() {
        return servicePostfit;
    }

    public void setServicePostfit(String servicePostfit) {
        this.servicePostfit = servicePostfit;
    }

    public String getServiceImplPostfit() {
        return serviceImplPostfit;
    }

    public void setServiceImplPostfit(String serviceImplPostfit) {
        this.serviceImplPostfit = serviceImplPostfit;
    }

    public String getControllerPostfit() {
        return controllerPostfit;
    }

    public void setControllerPostfit(String controllerPostfit) {
        this.controllerPostfit = controllerPostfit;
    }

    public String getListPagePostfit() {
        return listPagePostfit;
    }

    public void setListPagePostfit(String listPagePostfit) {
        this.listPagePostfit = listPagePostfit;
    }

    public String getEditPagePostfit() {
        return editPagePostfit;
    }

    public void setEditPagePostfit(String editPagePostfit) {
        this.editPagePostfit = editPagePostfit;
    }

    public GenerateConfig getGenerateConfiguration() {
        return generateConfiguration;
    }

    public GenerateConfig getConfig() {
        return this.generateConfiguration;
    }

    public void setGenerateConfiguration(GenerateConfig generateConfiguration) {
        this.generateConfiguration = generateConfiguration;
    }

    public String getServiceTestPostfit() {
        return serviceTestPostfit;
    }

    public void setServiceTestPostfit(String serviceTestPostfit) {
        this.serviceTestPostfit = serviceTestPostfit;
    }

    public String getImportPagePostfit() {
        return importPagePostfit;
    }

    public void setImportPagePostfit(String importPagePostfit) {
        this.importPagePostfit = importPagePostfit;
    }

    public String getShowPagePostfit() {
        return showPagePostfit;
    }

    public void setShowPagePostfit(String showPagePostfit) {
        this.showPagePostfit = showPagePostfit;
    }

}
