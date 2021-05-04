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
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 列业务类型
 * @author zhangpu
 */
public enum ColumnType implements Messageable {

    integer("integer", "整数", RandomStringUtils.randomNumeric(1)),
    money("money", "货币", "100.00"),
    percent("percent", "百分整数", "10"),
    centPercent("centPercent", "百分小数", "90.50"),
    mobile("mobile", "手机号码", "13787655390"),
    email("email", "邮箱", "demo@acooly.cn"),
    idcard("idcard", "身份证号码", "130928198905281793"),
    bankcard("bankcard", "银行卡", "667688929983998701"),
    url("url", "链接", "https://acooly.cn"),
    chinese("chinese", "中文", "只允许输入纯中文"),
    account("account", "账号", "Acooly@123!"),
    file("file", "文件", "/aaa/bbb/ccc.png"),

    text("text", "文本", ""),
    option("option", "选项", "");


    private final String code;
    private final String message;
    private final String demo;

    ColumnType(String code, String message, String demo) {
        this.code = code;
        this.message = message;
        this.demo = demo;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDemo() {
        return demo;
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
