/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-04-29 17:11
 */
package com.acooly.coder.domain;
/**
 * @author zhangpu
 * @date 2020-04-29 17:11
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 列业务类型
 */
public enum ColumnType implements Messageable {

    integer("integer", "整数"),
    money("money", "货币"),
    percent("percent", "百分数"),
    mobile("mobile", "手机号码"),
    email("email", "邮箱"),
    idcard("idcard", "身份证号码"),
    bankcard("bankcard", "银行卡"),
    url("url", "链接"),
    chinese("chinese","中文"),
    account("account","账号"),

    text("text", "文本"),
    option("option", "选项");


    private final String code;
    private final String message;

    ColumnType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
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
        for (ColumnType type : values()) {
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
    public static ColumnType find(String code) {
        for (ColumnType status : values()) {
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
    public static List<ColumnType> getAll() {
        List<ColumnType> list = new ArrayList<ColumnType>();
        for (ColumnType status : values()) {
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
        for (ColumnType status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
