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
        templateName = "dtoListInfo.ftl,dtoInfo.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        NameBlock dto = generateContext.getNameScheme().getDto();
        return dto.getModuleName() + "/" + cfg.getCodePath() + "/" + dto.getPackagePath();
    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        if (template.equalsIgnoreCase("dtoListInfo.ftl")) {
            return generateContext.getNameScheme().getDto().get(GenerateConstants.DTO_LIST_INFO_POSTFIX) + ".java";
        } else if (template.equalsIgnoreCase("dtoInfo.ftl")) {
            return generateContext.getNameScheme().getDto().get(GenerateConstants.DTO_INFO_POSTFIX) + ".java";
        }
        return null;
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.dto.name();
    }

}
