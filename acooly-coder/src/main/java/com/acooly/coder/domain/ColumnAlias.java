/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-04-30 10:17
 */
package com.acooly.coder.domain;
/**
 * @author zhangpu
 * @date 2020-04-30 10:17
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 列类型别名
 *
 * @author zhangpu
 * @date 2020-04-30
 */
public enum ColumnAlias implements Messageable {

    gender("Gender", "性别", "com.acooly.core.common.enums.Gender"),
    whether("WhetherStatus", "是否", "com.acooly.core.utils.enums.WhetherStatus"),
    simple("SimpleStatus", "简单状态", "com.acooly.core.utils.enums.SimpleStatus"),
    able("AbleStatus", "开关状态", "com.acooly.core.utils.enums.AbleStatus"),
    animal("AnimalSign", "生肖", "com.acooly.core.common.enums.AnimalSign"),
    star("StarSign", "星座", "com.acooly.core.common.enums.StarSign"),
    channel("ChannelEnum", "渠道", "com.acooly.core.common.enums.ChannelEnum");

    private final String code;
    private final String message;
    private final String declare;

    ColumnAlias(String code, String message, String declare) {
        this.code = code;
        this.message = message;
        this.declare = declare;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public String getDeclare() {
        return declare;
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
        for (ColumnAlias type : values()) {
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
    public static ColumnAlias find(String code) {
        for (ColumnAlias status : values()) {
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
    public static List<ColumnAlias> getAll() {
        List<ColumnAlias> list = new ArrayList<ColumnAlias>();
        for (ColumnAlias status : values()) {
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
        for (ColumnAlias status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
