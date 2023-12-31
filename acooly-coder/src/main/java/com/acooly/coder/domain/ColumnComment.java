/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-04-29 17:07
 */
package com.acooly.coder.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 备注对象
 *
 * @author zhangpu
 * @date 2020-04-29 17:07
 */
@Getter
@Setter
public class ColumnComment {
    private String title;
    private ColumnType type = ColumnType.text;
    private ColumnAlias alias;
    /**
     * 功能
     * 默认全功能
     *
     * @see com.acooly.coder.enums.Func
     */
    private int func = 31;
    private Map<String, String> options;
    private String tip;

    public ColumnComment() {
    }

    public ColumnComment(String title) {
        this.title = title;
        this.type = ColumnType.text;
    }
}
