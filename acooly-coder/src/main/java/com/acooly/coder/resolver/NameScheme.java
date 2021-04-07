package com.acooly.coder.resolver;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 命名规则和约定
 *
 * @author zhangpu
 */
@Getter
@Setter
public class NameScheme {


    private NameBlock dto = new NameBlock();

    private String domainClassName;

    private String domainPackage;

    private String enumPackage;

    private String daoClassName;

    private String daoPackage;

    private String daoImplClassName;

    private String daoImplPackage;

    private String daoTestPackage;

    private String daoTestClassName;

    private String servicePackage;

    private String serviceClassName;

    private String serviceImplPackage;
    private String serviceImplClassName;

    private String serviceTestPackage;
    private String serviceTestClassName;

    private String controllerPackage;
    private String controllerClassName;

    private String pagePath;
    private String listPageName;

    private String editPageName;
    private String ImportPageName;
    private String showPageName;

    private String openApiMessageClassName;
    private String openApiMessagePackage;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
