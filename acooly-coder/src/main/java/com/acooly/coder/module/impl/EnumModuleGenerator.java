package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.domain.Column;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;
import com.acooly.coder.resolver.NameBlock;
import com.acooly.coder.support.GenerateUtils;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
            return;
        }
        try {
            String[] templates = StringUtils.split(templateName, ",");
            List<String> outputFiles = new ArrayList<>();
            String outputFile = null;
            for (String temp : templates) {
                Template template = getTemplate(temp);
                List<Column> columns = generateContext.getTable().getColumns();
                for (Column column : columns) {
                    if (column.getDataType().isEnum() && column.getOptions() != null && column.getOptions().size() > 0) {
                        generateContext.appendData("enumColumn", column);
                        outputFile = doGenerate(template, generateContext, getOutputPath(generateContext, temp),
                                GenerateUtils.getCanonicalClassFileName(column.getDataType().getJavaName()));
                        if (outputFile != null) {
                            outputFiles.add(StringUtils.substringAfter(outputFile, GenerateConfig.INSTANCE().getProjectPath()));
                        }
                    }
                }
            }
            logger.info("success generated [" + this.getGenerateKey() + "] to " + outputFiles);
        } catch (Exception e) {
            logger.warning("Generate Module fail: " + e.getMessage());
        }
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        NameBlock block = generateContext.getNameScheme().getEnums();
        String outputPath = block.getModule() + "/" + cfg.getCodePath() + "/" + block.getPackagePath();
        return outputPath;
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
