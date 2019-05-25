/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-04-01 18:16
 */
package com.acooly.coder.enums;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-04-01 18:16
 */
public enum ViewType {

    freemarker("freemarker", ".ftl", "src/main/resources/templates", "easyui-freemarker"),

    jsp("jsp", ".jsp", "src/main/resources/META-INF/resources/WEB-INF/jsp", "easyui-jsp");


    private final String code;
    private final String suffix;
    private final String webappPath;
    private final String templatePath;

    private ViewType(String code, String suffix, String webappPath, String templatePath) {
        this.code = code;
        this.suffix = suffix;
        this.webappPath = webappPath;
        this.templatePath = templatePath;
    }

    public String getCode() {
        return code;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getWebappPath() {
        return webappPath;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (ViewType type : values()) {
            map.put(type.getCode(), type.getSuffix());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static ViewType find(String code) {
        for (ViewType status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<ViewType> getAll() {
        List<ViewType> list = new ArrayList<ViewType>();
        for (ViewType status : values()) {
            list.add(status);
        }
        return list;
    }


}
