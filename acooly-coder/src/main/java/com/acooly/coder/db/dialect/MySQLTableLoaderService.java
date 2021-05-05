package com.acooly.coder.db.dialect;

import com.acooly.coder.config.Database;
import com.acooly.coder.db.AbstractTableLoaderService;
import com.acooly.coder.db.TableLoaderService;
import com.acooly.coder.domain.*;
import com.acooly.coder.support.LogManager;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Mysql 方言 实现
 *
 * @author zhangpu
 */
public class MySQLTableLoaderService extends AbstractTableLoaderService implements TableLoaderService {
    protected static Logger logger = LogManager.getLogger(MySQLTableLoaderService.class);
    /**
     * 列相关元数据SQL
     */
    protected final static String COLUMN_METADATA_SQL = "select COLUMN_NAME as name,DATA_TYPE as type,(case when data_type='varchar' then CHARACTER_MAXIMUM_LENGTH else NUMERIC_PRECISION end)as length, IS_NULLABLE AS nullable,COLUMN_COMMENT AS comments,COLUMN_DEFAULT AS defaultValue from information_schema.COLUMNS where table_schema=? and table_name=? order by ORDINAL_POSITION";
    /**
     * 表备注元数据SQL
     */
    protected final static String TABLE_METADATA_SQL = "select TABLE_COMMENT from information_schema.tables where table_schema=? and table_name = ?";
    /**
     * 获取所有的表名SQL
     */
    protected final static String SELECT_ALL_TABLES = "select TABLE_NAME from information_schema.tables where table_schema=? ORDER BY TABLE_NAME";

    private String schema;

    private DataSource dataSource;

    public MySQLTableLoaderService(DataSource dataSource, String schema) {
        this.dataSource = dataSource;
        this.schema = schema;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tables = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_TABLES);
            stmt.setString(1, schema);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException("获取表名失败：" + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e2) {
                    // ig
                }
            }
        }
        return tables;
    }

    @Override
    public Table loadTable(String tableName) {
        Table tableMetadata = new Table();
        tableMetadata.setName(tableName);

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(COLUMN_METADATA_SQL);
            stmt.setString(1, schema);
            stmt.setString(2, tableName);
            ResultSet rs = stmt.executeQuery();
            List<Column> columnMetadatas = new LinkedList<Column>();
            Column columnMetadata = null;
            String databaseType = null;
            while (rs.next()) {
                columnMetadata = new Column();
                String name = rs.getString("name");
                columnMetadata.setName(name);
                columnMetadata.setNullable(rs.getString("nullable").equalsIgnoreCase("YES"));
                String comment = rs.getString("comments");
                ColumnComment columnComment = parseComment(comment);
                columnMetadata.setColumnComment(columnComment);
                columnMetadata.setColumnType(columnComment.getType());
                columnMetadata.setColumnAlias(columnComment.getAlias());
                columnMetadata.setOptions(columnComment.getOptions());
                columnMetadata.setTip(columnComment.getTip());
                comment = columnComment.getTitle();
                columnMetadata.setCommon(StringUtils.isBlank(comment) ? name : comment);
                Object defaultValue = rs.getObject("defaultValue");
                columnMetadata.setDefaultValue(defaultValue);
                // 最后处理数据类型
                databaseType = rs.getString("type");
                ColumnDataType dataType = parseJavaType(databaseType, columnMetadata);
                columnMetadata.setDataType(dataType);
                columnMetadata.setLength(doLength(dataType, rs.getInt("length")));
                columnMetadatas.add(columnMetadata);
            }
            if (columnMetadatas == null || columnMetadatas.size() == 0) {
                throw new RuntimeException("表不存在或没有正确定义");
            }
            tableMetadata.setColumns(columnMetadatas);
            String tableComment = getTableComment(tableName);
            tableMetadata.setComment(StringUtils.isBlank(tableComment) ? tableName : tableComment);
            logger.info("Load metadata of table : " + tableName);
            rs.close();
            stmt.close();
            return tableMetadata;
        } catch (Exception e) {
            throw new RuntimeException("获取表名失败：" + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e2) {
                    // ig
                }
            }
        }
    }

    protected int doLength(ColumnDataType dataType, int length) {
        int size = length;
        // 如果是对象型或JSON型，则不设置长度
        if (StringUtils.containsIgnoreCase("json,text", dataType.getDatabaseType())) {
            //设置为足够大的值
            size = 999999999;
        }
        if (dataType.isNumber()) {
            if (StringUtils.equalsIgnoreCase(dataType.getDatabaseType(), "tinyint")) {
                size = 127;
            } else {
                size = Double.valueOf(Math.pow(10, length - 1)).intValue() - 1;
            }
        }
        return size;
    }

    protected String getTableComment(String tableName) {
        Connection conn = null;
        String comment = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(TABLE_METADATA_SQL);
            stmt.setString(1, schema);
            stmt.setString(2, tableName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comment = rs.getString(1);
            }
            rs.close();
            stmt.close();
            return comment;
        } catch (Exception e) {
            throw new RuntimeException("获取表名失败：" + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e2) {
                    // ig
                }
            }
        }
    }

    @Override
    protected ColumnDataType doConvertJavaType(String databaseType, Column column) {

        if (StringUtils.equalsIgnoreCase(databaseType, "int") || StringUtils.equalsIgnoreCase(databaseType, "tinyint")
                || StringUtils.equalsIgnoreCase(databaseType, "smallint")) {
            return new ColumnDataType(databaseType, JavaType.Integer);
        } else if (StringUtils.containsIgnoreCase(databaseType, "bigint")
                || StringUtils.containsIgnoreCase(databaseType, "numeric")
                || StringUtils.containsIgnoreCase(databaseType, "decimal")) {
            return new ColumnDataType(databaseType, JavaType.Long);
        } else if (databaseType.equalsIgnoreCase("DATE")) {
            return new ColumnDataType(databaseType, JavaType.Date, "java.util.Date");
        } else if (databaseType.equalsIgnoreCase("DATETIME")
                || databaseType.equalsIgnoreCase("timestamp")) {
            return new ColumnDataType(databaseType, JavaType.DateTime, "java.util.Date");
        } else {
            return new ColumnDataType(databaseType, JavaType.String);
        }

    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Database getDatabase() {
        return Database.MYSQL;
    }

}
