package com.acooly.module.coder.db.dialect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.config.Database;
import com.acooly.module.coder.db.AbstractTableLoaderService;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.domain.TableColumn;

/**
 * Mysql 方言 实现
 * 
 * @author zhangpu
 * 
 */
public class MySQLTableLoaderService extends AbstractTableLoaderService implements TableLoaderService {
	protected static Logger logger = Logger.getLogger(MySQLTableLoaderService.class.getSimpleName());
	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "select COLUMN_NAME as name,DATA_TYPE as type,(case when data_type='varchar' then CHARACTER_MAXIMUM_LENGTH else NUMERIC_PRECISION end)as length, IS_NULLABLE AS nullable,COLUMN_COMMENT AS comments,COLUMN_DEFAULT AS defaultValue from information_schema.COLUMNS where table_schema=? and table_name=? order by ORDINAL_POSITION";
	/** 表备注元数据SQL */
	protected final static String TABLE_METADATA_SQL = "select TABLE_COMMENT from information_schema.tables where table_schema=? and table_name = ?";

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
			List<TableColumn> columnMetadatas = new LinkedList<TableColumn>();
			TableColumn columnMetadata = null;
			while (rs.next()) {
				columnMetadata = new TableColumn();
				String name = rs.getString("name");
				columnMetadata.setName(name);
				columnMetadata.setDataType(transformDataType(rs.getString("type")));

				columnMetadata.setLength(rs.getInt("length"));
				columnMetadata.setNullable(rs.getString("nullable").equalsIgnoreCase("YES"));
				String comment = rs.getString("comments");
				Map<String, String> options = parseJsonComment(comment);
				if (options != null && options.size() > 0) {
					// 存在选项值
					if (!StringUtils.isNumeric(options.keySet().iterator().next())) {
						// 非数字，采用枚举
						columnMetadata.setDataType(TableColumn.DATATYPE_ENUM);
					}
				}
				columnMetadata.setOptions(options);
				comment = getCanonicalComment(comment);
				columnMetadata.setCommon(StringUtils.isBlank(comment) ? name : comment);
				Object defaultValue = rs.getObject("defaultValue");
				columnMetadata.setDefaultValue(defaultValue);
				columnMetadatas.add(columnMetadata);
			}
			if (columnMetadatas == null || columnMetadatas.size() == 0) {
				throw new RuntimeException("表不存在或没有正确定义");
			}
			tableMetadata.setColumnMetadatas(columnMetadatas);
			String tableComment = getTableComment(tableName);
			tableMetadata.setComment(StringUtils.isBlank(tableComment) ? tableName : tableComment);
			logger.info("Load table metadata: " + tableName);
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

	private int transformDataType(String xtype) {
		if (StringUtils.equalsIgnoreCase(xtype, "int") || StringUtils.equalsIgnoreCase(xtype, "tinyint")
				|| StringUtils.equalsIgnoreCase(xtype, "smallint")) {
			return TableColumn.DATATYPE_INT;
		} else if (StringUtils.containsIgnoreCase(xtype, "bigint") || StringUtils.containsIgnoreCase(xtype, "numeric")
				|| StringUtils.containsIgnoreCase(xtype, "decimal")) {
			return TableColumn.DATATYPE_LONG;
		} else if (xtype.equalsIgnoreCase("DATE") || xtype.equalsIgnoreCase("DATETIME")
				|| xtype.equalsIgnoreCase("timestamp")) {
			return TableColumn.DATATYPE_DATE;
		} else {
			return TableColumn.DATATYPE_STRING;
		}

	}

	private Map<String, String> parseJsonComment(String comment) {
		try {
			String json = null;
			Matcher m = Pattern.compile("\\{.+\\}").matcher(comment);
			if (m.find()) {
				json = m.group();
			}
			if (StringUtils.isBlank(json)) {
				return null;
			}
			Map<String, String> data = new LinkedHashMap<String, String>();
			json = StringUtils.substring(json, 1, json.length() - 1);
			for (String item : StringUtils.split(json, ",")) {
				String[] fields = StringUtils.split(item, ":");
				data.put(fields[0], fields[1]);
			}
			return data;
		} catch (Exception e) {
			logger.warning("parse property comment to options Map fail. " + comment + "e: " + e.getMessage());
			return null;
		}
	}

	private String getCanonicalComment(String comment) {
		if (StringUtils.contains(comment, "{")) {
			comment = StringUtils.trimToEmpty(StringUtils.substringBefore(comment, "{"));
		}
		if (StringUtils.contains(comment, "(")) {
			comment = StringUtils.trimToEmpty(StringUtils.substringBefore(comment, "("));
		}
		return comment;
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

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("adsfasdf");
		logger.info("asdfasdf");

	}

}
