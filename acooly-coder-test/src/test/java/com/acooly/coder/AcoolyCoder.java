
package com.acooly.coder;

import com.acooly.coder.generate.impl.DefaultCodeGenerateService;
import org.apache.commons.lang3.StringUtils;

/**
 * 代码生成工具
 */
public class AcoolyCoder {

    static String MODULE_NAME = "acooly-coder-test";
    static String ROOT_PACKAGE = "com.acooly.coder.test";
    static String[] TABLES = {"dm_customer"};

    public static void main(String[] args) {
        DefaultCodeGenerateService service = (DefaultCodeGenerateService) Generator.getGenerator();
        //set workspace if possible
        if (StringUtils.isBlank(service.getGenerateConfiguration().getWorkspace())) {
            String workspace = getProjectPath() + MODULE_NAME;
            service.getGenerateConfiguration().setWorkspace(workspace);
        }
        //set root pacakge if possible
        if (StringUtils.isBlank(service.getGenerateConfiguration().getRootPackage())) {
            service.getGenerateConfiguration().setRootPackage(ROOT_PACKAGE);
        }
        service.generateTable(TABLES);
    }

    public static String getProjectPath() {
        String file = AcoolyCoder.class.getClassLoader().getResource(".").getFile();
        String testModulePath = file.substring(0, file.indexOf("/target/"));
        String projectPath = testModulePath.substring(0, testModulePath.lastIndexOf("/"));
        return projectPath + "/";
    }
}
