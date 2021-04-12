/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-04-07 11:59
 */
package com.acooly.coder.resolver;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2021-04-07 11:59
 */
@Getter
@Setter
public class NameBlock {

    /**
     * 模块名称
     */
    private String module;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类名称
     */
    private String className;

    /**
     * 扩展数据
     */
    private Map<String, String> data = new HashMap<>();

    public String getPackagePath() {
        return packageName.replaceAll("\\.", "\\/");
    }

    public String getPath() {
        return this.module + "/" + getPackagePath();
    }

    public void add(String key, String val) {
        this.data.put(key, val);
    }

    public String get(String key) {
        return this.data.get(key);
    }

}
