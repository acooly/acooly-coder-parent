package com.acooly.module.coder.db.dialect;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.TableColumn;
import com.acooly.module.coder.domain.Table;

/**
 * Oracle 实现
 * 
 * @author zhangpu
 *
 */
public class PostgresTableLoaderDialect implements TableLoaderService {

	private static final Logger logger = LoggerFactory.getLogger(PostgresTableLoaderDialect.class);

	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "Select t1.column_name as name,t1.data_type as type,t1.data_length as length,t1.nullable,t2.comments From user_tab_cols t1 left join user_col_comments t2 on t1.column_name=t2.column_name and t1.table_name=t2.table_name where t1.table_name=?";
	/** 表备注元数据SQL */
	protected final static String TABLE_METADATA_SQL = "select COMMENTS from user_tab_comments where table_name = ?";

	private JdbcTemplate jdbcTemplate;

	public PostgresTableLoaderDialect(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<String> getTableNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table loadTable(String tableName) {
		logger.info("Loading metadata --> " + tableName);
		Table tableMetadata = new Table();
		tableMetadata.setName(tableName);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(COLUMN_METADATA_SQL, tableName);
		List<TableColumn> columnMetadatas = new LinkedList<TableColumn>();
		TableColumn columnMetadata = null;
		while (rs.next()) {
			columnMetadata = new TableColumn();
			String name = rs.getString("NAME");
			columnMetadata.setName(name);
			columnMetadata.setDataType(transformDataType(rs.getString("TYPE")));
			columnMetadata.setDefaultValue(rs.getString("DEFAULTVALUE"));
			columnMetadata.setLength(rs.getInt("LENGTH"));
			columnMetadata.setNullable(rs.getString("nullable").equalsIgnoreCase("Y"));
			String comment = rs.getString("comments");
			columnMetadata.setCommon(StringUtils.isBlank(comment) ? name : comment);
			columnMetadatas.add(columnMetadata);
		}
		String tableComment = jdbcTemplate.queryForObject(TABLE_METADATA_SQL, String.class, tableName);
		tableMetadata.setComment(StringUtils.isNotBlank(tableComment) ? tableName : tableComment);
		return tableMetadata;
	}

	public String getEntityIdDeclare(String tableName) {
		return null;
	}

	private int transformDataType(String xtype) {

		if (xtype.equals("NUMBER")) {
			return TableColumn.DATATYPE_LONG;
		} else if (xtype.equals("DATE")) {
			return TableColumn.DATATYPE_DATE;
		} else {
			return TableColumn.DATATYPE_STRING;
		}

	}

	@Override
	public Database getDatabase() {
		return Database.ORACLE;
	}

}
