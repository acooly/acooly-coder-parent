/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-04-11 14:15
 */
package com.acooly.coder.enums;
/**
 * @author zhangpu
 * @date 2021-04-11 14:15
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ProjectModule implements Messageable {
    common("common", "common", "公共"),
    facade("facade", "facade", "远程接口"),
    openapi("openapi", "openapi", "OpenApi"),
    openapi_message("openapi-message", "openapi.message", "OpenApi消息"),
    openapi_service("openapi-service", "openapi.service", "OpenApi服务"),
    openapi_test("openapi-test", "openapi.test", "OpenApi测试"),
    ;


    private final String code;
    private final String packageName;
    private final String message;


    ProjectModule(String code, String packageName, String message) {
        this.code = code;
        this.packageName = packageName;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (ProjectModule type : values()) {
            map.put(type.getCode(), type.getMessage());
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
    public static ProjectModule find(String code) {
        for (ProjectModule status : values()) {
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
    public static List<ProjectModule> getAll() {
        List<ProjectModule> list = new ArrayList<ProjectModule>();
        for (ProjectModule status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllCode() {
        List<String> list = new ArrayList<String>();
        for (ProjectModule status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
