package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.config.GenerateConstants;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;
import com.acooly.coder.resolver.NameBlock;

/**
 * Domain generator
 *
 * @author zhangpu
 */
public class DtoModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "dtoListInfo.ftl,dtoInfo.ftl,dtoCreateInfo.ftl,dtoUpdateInfo.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        NameBlock dto = generateContext.getNameScheme().getDto();
        return dto.getModule() + "/" + cfg.getCodePath() + "/" + dto.getPackagePath();
    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        String entityClassName = generateContext.getNameScheme().getDomainClassName();
        if (template.equalsIgnoreCase("dtoListInfo.ftl")) {
            return entityClassName + GenerateConstants.DTO_LIST_POSTFIX + ".java";
        } else if (template.equalsIgnoreCase("dtoInfo.ftl")) {
            return entityClassName + GenerateConstants.DTO_INFO_POSTFIX + ".java";
        } else if (template.equalsIgnoreCase("dtoCreateInfo.ftl")) {
            return entityClassName + GenerateConstants.DTO_CREATE_POSTFIX + ".java";
        } else if (template.equalsIgnoreCase("dtoUpdateInfo.ftl")) {
            return entityClassName + GenerateConstants.DTO_UPDATE_POSTFIX + ".java";
        }
        return null;
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.dto.name();
    }

}
