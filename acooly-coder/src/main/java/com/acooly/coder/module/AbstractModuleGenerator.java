package com.acooly.coder.module;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.support.DatetimeMethod;
import com.acooly.coder.support.LogManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractModuleGenerator implements ModuleGenerator {

    protected static Logger logger = LogManager.getLogger(AbstractModuleGenerator.class);

    protected GenerateConfig generateConfiguration = GenerateConfig.INSTANCE();

    protected String templateName;

    @Override
    public void generate(GenerateContext generateContext) {

        try {
            onGenerate(generateContext);
            String[] templates = StringUtils.split(templateName, ",");
            List<String> outputFiles = new ArrayList<>();
            String outputFile = null;
            for (String temp : templates) {
                Template template = getTemplate(temp);
                outputFile = doGenerate(template, generateContext, getOutputPath(generateContext, temp));
                if (outputFile != null) {
                    outputFiles.add(StringUtils.substringAfter(outputFile, GenerateConfig.INSTANCE().getWorkspace()));
                }

            }
            logger.info("success generated [" + this.getGenerateKey() + "] to " + outputFiles);
        } catch (Exception e) {
            logger.warning("Generate Module fail: " + e.getMessage());
        }
    }


    protected void onGenerate(GenerateContext generateContext) {

    }

    protected abstract String getOutputPath(GenerateContext generateContext, String template);

    protected abstract String getOutputFile(GenerateContext generateContext, String template);

    protected String doGenerate(Template template, GenerateContext generateContext, String outputPath) {
        return doGenerate(template, generateContext, outputPath, getOutputFile(generateContext, template.getName()));
    }

    protected String doGenerate(Template template, GenerateContext generateContext, String outputPath,
                                String outputFile) {
        Writer out = null;
        try {
            File distPath = new File(outputPath);
            if (!distPath.exists()) {
                distPath.mkdirs();
            }
            File distFile = new File(distPath, outputFile);
            out = new OutputStreamWriter(new FileOutputStream(distFile, false), "UTF-8");
            template.process(generateContext, out);
            out.flush();
            return distFile.getPath();
        } catch (Exception e) {
            logger.warning("generate module fail. template " + template.getName() + " table "
                    + generateContext.getNameScheme().getDomainClassName() + " ; outputDir: " + outputPath + ", error:"
                    + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    // ig
                }
            }
        }
        return null;
    }


    protected String getPackage(String name) {
        return generateConfiguration.getRootPackage() + "." + name;
    }

    protected String getPackagePath(String packageName) {
        return packageName.replaceAll("\\.", "\\/");
    }

    protected Template getTemplate(String templateName) {
        try {
            return getConfiguration().getTemplate(templateName);
        } catch (Exception e) {
            logger.warning("template file (" + templateName + ") load fail: " + e.getMessage());
            throw new RuntimeException("template file (" + templateName + ") load fail.");
        }

    }

    protected Configuration getConfiguration() {

        String templatePath = generateConfiguration.getTemplatePath();
        try {
            Configuration cfg = new Configuration();
            cfg.setSharedVariable("datetime", new DatetimeMethod());
            cfg.setDefaultEncoding("UTF-8");
            if (StringUtils.containsIgnoreCase(templatePath, "classpath:")) {
                templatePath = StringUtils.substringAfter(templatePath, "classpath:");
                if (!StringUtils.startsWith(templatePath, "/")) {
                    templatePath = "/" + templatePath;
                }
                cfg.setClassForTemplateLoading(getClass(), templatePath);
            } else {
                cfg.setDirectoryForTemplateLoading(new File(templatePath));
            }
            return cfg;
        } catch (Exception e) {
            logger.warning("create freemarker configuration fail. templateDirectory: " + templatePath + ", message: "
                    + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    public void setGenerateConfiguration(GenerateConfig generateConfiguration) {
        this.generateConfiguration = generateConfiguration;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public GenerateConfig getGenerateConfiguration() {
        return generateConfiguration;
    }

}
