package com.acooly.coder.domain;

import com.acooly.coder.db.TableProperties;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 表元数据
 *
 * @author zhangpu
 * @date 2015年8月30日
 */
public class Table {

    /**
     * 表名
     */
    private String name;
    /**
     * 表备注
     */
    private String comment;
    /**
     * 扩展信息
     */
    private Map<String, Object> properties = new HashMap<>();

    /**
     * 列信息
     */
    private List<Column> columns = new LinkedList<>();

    /**
     * 唯一索引
     */
    private List<Index> indexes = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void addProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    public <T> T getProperty(String key) {
        return (T) this.properties.get(key);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Set<String> getImportDeclares() {
        Set<String> declares = new HashSet<String>();
        for (Column col : columns) {
            if (StringUtils.isNotBlank(col.getDataType().getJavaDeclare())) {
                declares.add(col.getDataType().getJavaDeclare());
            }
        }
        return declares;
    }

    public Boolean getMoveFunc() {
        return getProperty(TableProperties.MOVE_FUNC_REQUIRED.code());
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> indexes) {
        this.indexes = indexes;
    }
}
