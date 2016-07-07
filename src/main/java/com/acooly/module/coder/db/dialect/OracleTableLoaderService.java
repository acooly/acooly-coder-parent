package com.acooly.module.coder.db.dialect;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.acooly.module.coder.config.Database;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.Table;

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

	// @Override
	// protected JavaType doConvertJavaType(String databaseType, int length) {
	// if (databaseType.equals("NUMBER")) {
	// return length <= 4 ? JavaType.Integer : JavaType.Long;
	// } else if (databaseType.equals("DATE")) {
	// return JavaType.Date;
	// } else {
	// return JavaType.String;
	// }
	// }

	@Override
	public Database getDatabase() {
		return Database.ORACLE;
	}

}
