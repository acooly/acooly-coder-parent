package com.acooly.coder.module.impl;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.module.AbstractModuleGenerator;
import com.acooly.coder.module.GenerateKeys;
import com.acooly.coder.resolver.NameBlock;
import org.apache.commons.lang3.StringUtils;

/**
 * OpenApi generator
 *
 * @author zhangpu
 */
public class OpenApiModuleGenerator extends AbstractModuleGenerator {

    {
        templateName = "openapi_infoApiRequest.ftl,openapi_infoApiResponse.ftl," +
                "openapi_listApiRequest.ftl,openapi_listApiResponse.ftl," +
                "openapi_infoApiService.ftl,openapi_listApiService.ftl," +
                "openapi_ApiServiceTest.ftl";
    }

    @Override
    protected String getOutputPath(GenerateContext generateContext, String template) {
        GenerateConfig cfg = getGenerateConfiguration();
        TemplateType type = getType(template);
        if (type == TemplateType.message) {
            NameBlock named = generateContext.getNameScheme().getOpenapiMessage();
            String messagePath = named.getModule() + "/" + cfg.getCodePath() + "/" + named.getPackagePath();
            messagePath = messagePath + "/" + (StringUtils.endsWith(template, "Request.ftl") ? "request" : "response");
            return messagePath;
        } else if (type == TemplateType.service) {
            NameBlock named = generateContext.getNameScheme().getOpenapiService();
            return named.getModule() + "/" + cfg.getCodePath() + "/" + named.getPackagePath();
        } else {
            NameBlock named = generateContext.getNameScheme().getOpenapiTest();
            return named.getModule() + "/" + cfg.getTestPath() + "/" + named.getPackagePath();
        }
    }


    @Override
    protected String getOutputFile(GenerateContext generateContext, String template) {
        String namePart = StringUtils.capitalize(StringUtils.substringBetween(template, "openapi_", ".ftl"));
        return generateContext.getNameScheme().getDomainClassName() + namePart + ".java";
    }

    @Override
    public String getGenerateKey() {
        return GenerateKeys.openapi.name();
    }

    public TemplateType getType(String template) {
        if (StringUtils.endsWith(template, "ApiService.ftl")) {
            return TemplateType.service;
        } else if (StringUtils.endsWith(template, "ApiServiceTest.ftl")) {
            return TemplateType.test;
        } else {
            return TemplateType.message;
        }
    }

    static enum TemplateType {
        message, service, test
    }


}
