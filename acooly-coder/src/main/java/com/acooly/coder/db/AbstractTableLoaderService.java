/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu
 * date:2016年7月2日
 *
 */
package com.acooly.coder.db;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.domain.*;
import com.acooly.coder.support.GenerateUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @author zhangpu
 */
public abstract class AbstractTableLoaderService implements TableLoaderService {

    protected static Logger logger = Logger.getLogger(AbstractTableLoaderService.class.getSimpleName());

    protected ColumnDataType parseJavaType(String databaseType, Column column) {
        // 自定义选项
        if (column.getOptions() != null && column.getOptions().size() > 0) {
            // 存在选项值
            if (!StringUtils.isNumeric(column.getOptions().keySet().iterator().next())) {
                // 非数字，采用枚举
                return new ColumnDataType(databaseType, JavaType.Enum, column.getName());
            }
        }

        // 别名选项
        if (column.getColumnAlias() != null) {
            return new ColumnDataType(databaseType, JavaType.Enum, column.getColumnAlias().getDeclare(),
                    column.getColumnAlias().code());
        }

        // 特别处理Money
        if (column.getColumnType() == ColumnType.money) {
            return new ColumnDataType(databaseType, JavaType.Money, "com.acooly.core.utils.Money",
                    "Money");
        }

        // 根据数据库的实现
        ColumnDataType dataType = doConvertJavaType(databaseType, column);

        // 用户自定义配置实现
        ColumnDataType customeType = convertCustomeType(databaseType, column);
        return customeType != null ? customeType : dataType;
    }

    protected ColumnDataType convertCustomeType(String databaseType, Column column) {

        Map<String, String> dataTypeMapping = GenerateUtils
                .parseSimilarJson(GenerateConfig.INSTANCE().getDateTypeDeclare());
        if (dataTypeMapping == null || dataTypeMapping.size() == 0) {
            return null;
        }
        String mapping = dataTypeMapping.get(databaseType);
        if (StringUtils.isBlank(mapping)) {
            return null;
        }

        String javaTypeValue = mapping;
        if (StringUtils.contains(mapping, ".")) {
            javaTypeValue = StringUtils.substringAfterLast(mapping, ".");
        }
        JavaType javaType = JavaType.of(javaTypeValue);
        if (javaType == null) {
            javaType = JavaType.Object;
            return new ColumnDataType(databaseType, javaType, mapping);
        } else {
            return new ColumnDataType(databaseType, javaType);
        }

    }

    protected abstract ColumnDataType doConvertJavaType(String databaseType, Column column);

    /**
     * 解析规范的备注
     *
     * @param comment
     * @return
     */
    protected String parseCanonicalComment(String comment) {
        if (StringUtils.contains(comment, "{")) {
            comment = StringUtils.trimToEmpty(StringUtils.substringBefore(comment, "{"));
        }
        if (StringUtils.contains(comment, "(")) {
            comment = StringUtils.trimToEmpty(StringUtils.substringBefore(comment, "("));
        }
        return comment;
    }

    protected ColumnComment parseComment(String comment) {
        String similarJson = StringUtils.trimToEmpty(comment);
        // JSON格式
        if (StringUtils.startsWith(comment, "{")) {
            similarJson = StringUtils.replace(similarJson, "’", "'");
            similarJson = StringUtils.replace(similarJson, "‘", "'");
            try {
                ColumnComment columnComment = JSON.parseObject(similarJson, ColumnComment.class);
                if (columnComment.getAlias() != null) {
                    columnComment.setType(ColumnType.option);
                }
                if (columnComment.getType() == null) {
                    columnComment.setType(ColumnType.text);
                }
                return columnComment;
            } catch (Exception e) {
                logger.warning("JSON Comment parse failure: " + e.getMessage());
                return new ColumnComment(comment);
            }
        } else {
            ColumnComment columnComment = new ColumnComment(parseCanonicalComment(similarJson));
            Map<String, String> options = GenerateUtils.parseSimilarJson(similarJson);
            columnComment.setOptions(options);
            if (options == null || options.size() == 0) {
                columnComment.setType(ColumnType.text);
            } else {
                columnComment.setType(ColumnType.option);
            }
            return columnComment;
        }
    }

    /**
     * 解析列的可选值
     *
     * @param comment
     * @return
     */
    protected Map<String, String> parseOptions(String comment) {
        return GenerateUtils.parseSimilarJson(comment);
    }

}
