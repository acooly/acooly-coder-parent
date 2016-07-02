package com.acooly.module.coder.db.dialect;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.config.Database;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.domain.TableColumn;

/**
 * Oracle 实现
 * 
 * @author zhangpu
 */
public class OracleTableLoaderService implements TableLoaderService {

	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "Select t1.column_name as name,t1.data_type as type,case when data_type = 'NUMBER' then t1.data_precision else t1.data_length end length,t1.nullable,t2.comments From user_tab_cols t1 left join user_col_comments t2 on t1.column_name=t2.column_name and t1.table_name=t2.table_name where t1.table_name=?";
	/** 表备注元数据SQL */
	protected final static String TABLE_METADATA_SQL = "select COMMENTS from user_tab_comments where table_name = ?";

	protected final static String SELECT_ALL_TABLES = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY TABLE_NAME";

	private DataSource dataSource;

	public OracleTableLoaderService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<String> getTableNames() {
		List<String> tables = new ArrayList<String>();
		// List<String> tables = jdbcTemplate.queryForList(SELECT_ALL_TABLES,
		// String.class);
		return tables;
	}

	@Override
	public Table loadTable(String tableName) {
		Table tableMetadata = new Table();
		tableMetadata.setName(tableName);
		// SqlRowSet rs = jdbcTemplate.queryForRowSet(COLUMN_METADATA_SQL,
		// tableName);
		// List<TableColumn> columnMetadatas = new LinkedList<TableColumn>();
		// TableColumn columnMetadata = null;
		// while (rs.next()) {
		// columnMetadata = new TableColumn();
		// String name = rs.getString("NAME");
		// columnMetadata.setName(name);
		// columnMetadata.setLength(rs.getInt("LENGTH"));
		// columnMetadata.setDataType(transformDataType(rs.getString("TYPE"),
		// columnMetadata.getLength()));
		// columnMetadata.setNullable(rs.getString("nullable").equalsIgnoreCase("Y"));
		// String comment = rs.getString("comments");
		// Map<String, String> options = parseJsonComment(comment);
		// columnMetadata.setOptions(options);
		// comment = getCanonicalComment(comment);
		// columnMetadata.setCommon(StringUtils.isBlank(comment) ? name :
		// comment);
		// columnMetadatas.add(columnMetadata);
		// }
		// tableMetadata.setColumnMetadatas(columnMetadatas);
		// String tableComment = jdbcTemplate.queryForObject(TABLE_METADATA_SQL,
		// String.class, tableName);
		// tableMetadata.setComment(StringUtils.isBlank(tableComment) ?
		// tableName : tableComment);
		// logger.debug("Load metadata success--> " + tableName);
		return tableMetadata;
	}

	public String getEntityIdDeclare(String tableName) {
		String declare = "@GeneratedValue(generator = \"sequence\")\n"
				+ "	@GenericGenerator(name = \"sequence\", strategy = \"sequence\", parameters = { @Parameter(name = \"sequence\", value = \"SEQ_"
				+ tableName + "\") })";
		return declare;
	}

	private int transformDataType(String xtype, int length) {

		if (xtype.equals("NUMBER")) {
			return length <= 4 ? TableColumn.DATATYPE_INT : TableColumn.DATATYPE_LONG;
		} else if (xtype.equals("DATE")) {
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
			// logger.warn("parse property comment to options Map fail. ", e);
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
		return Database.ORACLE;
	}

}
