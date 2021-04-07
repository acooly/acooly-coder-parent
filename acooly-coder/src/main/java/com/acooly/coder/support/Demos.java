/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2021-04-07 14:02
 */
package com.acooly.coder.support;

import com.acooly.coder.domain.Column;
import com.acooly.coder.domain.ColumnAlias;
import com.acooly.coder.domain.ColumnDataType;
import com.acooly.coder.domain.ColumnType;
import com.acooly.core.utils.enums.Messageable;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 类型字段demo生成器
 *
 * @author zhangpu
 * @date 2021-04-07 14:02
 */
public class Demos {


    public static String demo(Column column) {
        // 如果是有别名，则根据别名的枚举声明获取枚举的第一个值
        ColumnAlias columnAlias = column.getColumnAlias();
        if (columnAlias != null) {
            return loadFirstMessageable(columnAlias.getDeclare()).code();
        }

        // 根据列自定义类型生成
        ColumnType columnType = column.getColumnType();
        if (columnType == ColumnType.option) {
            Map<String, String> options = column.getOptions();
            if (options != null && options.size() > 0) {
                return options.keySet().iterator().next();
            }
        }

        if (columnType != ColumnType.text) {
            return columnType.getDemo();
        }

        ColumnDataType dataType = column.getDataType();
        if (dataType.isDate()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            return sdf.format(new Date());
        }

        if (dataType.isDateTime()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            return sdf.format(new Date());
        }

        if (dataType.isBigObject()) {
            return "该值为大对象字段，可以输入较多的文本数据";
        }

        if (dataType.isNumber()) {
            return RandomStringUtils.randomNumeric(1);
        }
        int max = column.getLength() == 0 || column.getLength() > 10 ? 10 : column.getMax().intValue();
        return RandomStringUtils.random(max, true, true);
    }

    private static Messageable loadFirstMessageable(String declare) {
        try {
            Class clazz = Class.forName(declare);
            Object[] objects = clazz.getEnumConstants();
            Messageable messageable = (Messageable) objects[0];
            return messageable;
        } catch (Exception e) {
            //ig
            return new Messageable() {
                @Override
                public String code() {
                    return "";
                }

                @Override
                public String message() {
                    return "";
                }
            };
        }
    }

    public static String firstEnumValue(Column column) {
        ColumnAlias columnAlias = column.getColumnAlias();
        if (columnAlias != null) {
            return loadFirstMessageable(columnAlias.getDeclare()).message();
        }
        ColumnType columnType = column.getColumnType();
        if (columnType == ColumnType.option) {
            Map<String, String> options = column.getOptions();
            if (options != null && options.size() > 0) {
                return options.values().iterator().next();
            }
        }
        return null;
    }

}
