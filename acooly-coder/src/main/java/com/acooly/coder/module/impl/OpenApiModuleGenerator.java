package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;

/**
 * OpenApi generator
 *
 * @author zhangpu
 */
public class OpenApiModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "openapiMessage.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        String packagePath = getPackagePath(generateContext.getNameScheme().getOpenApiMessagePackage());
        return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        return generateContext.getNameScheme().getOpenApiMessageClassName() + ".java";
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.openapi.name();
    }

}
