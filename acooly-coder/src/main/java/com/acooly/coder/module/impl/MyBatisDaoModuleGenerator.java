package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;

/**
 * Domain generator
 *
 * @author zhangpu
 */
public class MyBatisDaoModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "mybatisDao.ftl,mybatisMapper.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String temp) {
        if ("mybatisDao.ftl".equals(temp)) {
            GenerateConfig cfg = getGenerateConfiguration();
            String packagePath = getPackagePath(generateContext.getNameScheme().getDaoPackage());
            return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
        }

        if ("mybatisMapper.ftl".equals(temp)) {
            GenerateConfig cfg = getGenerateConfiguration();
            return cfg.getWorkspace() + "/" +"src/main/resources/mybatis";
        }
        throw new UnsupportedOperationException("x");
    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String temp) {
        if ("mybatisDao.ftl".equals(temp)) {
            return generateContext.getNameScheme().getDaoClassName() + ".java";
        }
        if ("mybatisMapper.ftl".equals(temp)) {
            return generateContext.getNameScheme().getDomainClassName() + "Mapper.xml";
        }
        throw new UnsupportedOperationException("x");
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.mybatis.name();
    }
}
