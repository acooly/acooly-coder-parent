package com.acooly.module.coder.db.dialect;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.domain.TableColumn;

/**
 * Mysql 方言 实现
 * 
 * @author zhangpu
 * 
 */
public class MySQLTableLoaderDialect implements TableLoaderService {
	private static final Logger logger = LoggerFactory.getLogger(OracleTableLoaderDialect.class);

	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "select COLUMN_NAME as name,DATA_TYPE as type,(case when data_type='varchar' then CHARACTER_MAXIMUM_LENGTH else NUMERIC_PRECISION end)as length, IS_NULLABLE AS nullable,COLUMN_COMMENT AS comments,COLUMN_DEFAULT AS defaultValue from information_schema.COLUMNS where table_name=?";
	/** 表备注元数据SQL */
	protected final static String TABLE_METADATA_SQL = "select TABLE_COMMENT from information_schema.tables where table_name = ?";

	protected final static String SELECT_ALL_TABLES = "select TABLE_NAME from information_schema.tables where table_schema=? ORDER BY TABLE_NAME";

	private JdbcTemplate jdbcTemplate;

	public MySQLTableLoaderDialect(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<String> getTableNames() {
		List<String> tables = jdbcTemplate.queryForList(SELECT_ALL_TABLES, String.class);
		return tables;
	}

	@Override
	public Table loadTable(String tableName) {
		Table tableMetadata = new Table();
		tableMetadata.setName(tableName);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(COLUMN_METADATA_SQL, tableName);
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
			columnMetadata.setOptions(options);
			comment = getCanonicalComment(comment);
			columnMetadata.setCommon(StringUtils.isBlank(comment) ? name : comment);
			Object defaultValue = rs.getObject("defaultValue");
			columnMetadata.setDefaultValue(defaultValue);
			columnMetadatas.add(columnMetadata);
		}
		tableMetadata.setColumnMetadatas(columnMetadatas);
		String tableComment = jdbcTemplate.queryForObject(TABLE_METADATA_SQL, String.class, tableName);
		tableMetadata.setComment(StringUtils.isBlank(tableComment) ? tableName : tableComment);
		logger.debug("Load metadata success--> " + tableName);
		return tableMetadata;
	}

	public String getEntityIdDeclare(String tableName) {
		String declare = "@GeneratedValue";
		return declare;
	}

	private int transformDataType(String xtype) {

		if (StringUtils.equalsIgnoreCase(xtype, "int")) {
			return TableColumn.DATATYPE_INT;
		} else if (StringUtils.containsIgnoreCase(xtype, "bigint") || StringUtils.containsIgnoreCase(xtype, "numeric")
				|| StringUtils.containsIgnoreCase(xtype, "decimal")) {
			return TableColumn.DATATYPE_LONG;
		} else if (xtype.equalsIgnoreCase("DATE") || xtype.equalsIgnoreCase("DATETIME")) {
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
			logger.warn("parse property comment to options Map fail. --> " + comment, e);
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

	@Override
	public Database getDatabase() {
		return Database.MYSQL;
	}

}
