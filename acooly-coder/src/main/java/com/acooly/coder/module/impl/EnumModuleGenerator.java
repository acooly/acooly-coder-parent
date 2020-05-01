package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.support.GenerateUtils;
import com.acooly.coder.domain.Column;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * enum property generator
 *
 * @author zhangpu
 */
public class EnumModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "enum.ftl";
    }

    @Override
    public void generate(GenerateContext generateContext) {
        if (!generateContext.getConfiguration().isEnumEnable()) {
            //logger.info("ignore generate [" + this.getGenerateKey() + "]，配置参数generator.enum.enable=false，不生成枚举方案");
            return;
        }
        try {
            String[] templates = StringUtils.split(templateName, ",");
            for (String temp : templates) {
                Template template = getTemplate(temp);

                List<Column> columns = generateContext.getTable().getColumns();
                for (Column column : columns) {
                    if (column.getDataType().isEnum() && column.getOptions() != null && column.getOptions().size() > 0) {
                        generateContext.appendData("enumColumn", column);
                        doGenerate(template, generateContext, getOutputPath(generateContext, temp),
                                GenerateUtils.getCanonicalClassFileName(column.getDataType().getJavaName()));
                    }
                }
            }
            logger.info("success generate [" + this.getGenerateKey() + "]");
        } catch (Exception e) {
            logger.warning("Generate Module fail: " + e.getMessage());
        }
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        String packagePath = getPackagePath(generateContext.getNameScheme().getEnumPackage());
        return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
    }

    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        return null;
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.enumClass.name();
    }

}
