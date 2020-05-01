/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-04-29 17:07
 */
package com.acooly.coder.domain;

import com.acooly.core.common.facade.InfoBase;
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
public class ColumnComment extends InfoBase {
    private String title;
    private ColumnType type = ColumnType.text;
    private ColumnAlias alias;
    private Map<String, String> options;
    
    public ColumnComment() {
    }

    public ColumnComment(String title) {
        this.title = title;
        this.type = ColumnType.text;
    }
}
