/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2022-11-13 11:38
 */
package com.acooly.coder.enums;
/**
 * @author zhangpu
 * @date 2022-11-13 11:38
 */

import java.util.ArrayList;
import java.util.List;

public enum Func {
    Search(1, "查询条件"),
    List(2, "列表"),
    Save(4, "新增修改"),
    Show(8, "查看"),
    Export(16, "导出");

    private final int bit;
    private final String title;

    Func(int bit, String message) {
        this.bit = bit;
        this.title = message;
    }


    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<Func> getAll() {
        List<Func> list = new ArrayList<Func>();
        for (Func status : values()) {
            list.add(status);
        }
        return list;
    }

    public int getBit() {
        return bit;
    }

    public String getTitle() {
        return title;
    }

}
