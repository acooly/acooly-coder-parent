package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;
import com.acooly.coder.resolver.NameBlock;
import org.apache.commons.lang3.StringUtils;

/**
 * Domain generator
 *
 * @author zhangpu
 */
public class FacadeModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "facade.ftl,facadeImpl.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();

        if (StringUtils.equals("facade.ftl", template)) {
            NameBlock named = generateContext.getNameScheme().getFacade();
            return named.getModule() + "/" + cfg.getCodePath() + "/" + named.getPackagePath();
        } else {
            NameBlock named = generateContext.getNameScheme().getFacadeImpl();
            return named.getModule() + "/" + cfg.getCodePath() + "/" + named.getPackagePath();
        }


    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        NameBlock named = null;
        if (StringUtils.equals("facade.ftl", template)) {
            named = generateContext.getNameScheme().getFacade();
        } else {
            named = generateContext.getNameScheme().getFacadeImpl();
        }
        return named.getClassName() + ".java";
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.facade.name();
    }

}
