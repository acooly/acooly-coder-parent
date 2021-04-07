/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-01-28 15:14 创建
 */
package com.acooly.coder.module;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author acooly
 */
public enum GenerateModule {

    Service("Service", "包括entity,dao,service的基础模块。"),

    Manage("Manage", "包括BOSS管理的controller和pages。"),

    Portal("Portal", "基于freemarker的controller和pages。"),

    Facade("Facade", "内部系统通讯的接口服务,包括:DTO,Order,Result和Facade"),

    OpenApi("OpenApi", "包括对外服务的OpenAPI的service,request,response");


    private final String code;
    private final String message;

    private GenerateModule(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (GenerateModule type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    public static GenerateModule withOf(String code) {
        for (GenerateModule status : values()) {
            if (status.getCode().equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static GenerateModule find(String code) {
        for (GenerateModule status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Modules not legal:" + code);
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<GenerateModule> getAll() {
        List<GenerateModule> list = new ArrayList<GenerateModule>();
        for (GenerateModule status : values()) {
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
        for (GenerateModule status : values()) {
            list.add(status.code());
        }
        return list;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.code, this.message);
    }
}
