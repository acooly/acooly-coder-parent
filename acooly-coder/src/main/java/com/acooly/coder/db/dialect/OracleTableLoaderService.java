package com.acooly.coder.db.dialect;

import com.acooly.coder.config.Database;
import com.acooly.coder.db.AbstractTableLoaderService;
import com.acooly.coder.db.TableLoaderService;
import com.acooly.coder.domain.Column;
import com.acooly.coder.domain.ColumnDataType;
import com.acooly.coder.domain.JavaType;
import com.acooly.coder.domain.Table;
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
 * Oracle 实现
 * 
 * @author zhangpu
 */
public class OracleTableLoaderService extends AbstractTableLoaderService implements TableLoaderService {
	protected static Logger logger = Logger.getLogger(MySQLTableLoaderService.class.getSimpleName());

	/** 列相关元数据SQL */
	protected final static String COLUMN_METADATA_SQL = "Select t1.column_name as name,t1.data_type as type,case when data_type = 'NUMBER' then t1.data_precision else t1.data_length end length,t1.data_scale,t1.nullable,t2.comments,t1.DATA_DEFAULT From user_tab_cols t1 left join user_col_comments t2 on t1.column_name=t2.column_name and t1.table_name=t2.table_name where t1.table_name=? ORDER BY COLUMN_ID";
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
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_TABLES);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
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
			stmt.setString(1, tableName);
			ResultSet rs = stmt.executeQuery();
			List<Column> columns = new LinkedList<Column>();
			Column column = null;
			String databaseType = null;
			while (rs.next()) {
				column = new Column();
				String name = rs.getString("NAME");
				column.setName(name);
				column.setLength(rs.getInt("LENGTH"));
				column.setScale(rs.getInt("DATA_SCALE"));
				column.setNullable(rs.getString("NULLABLE").equalsIgnoreCase("Y"));
				String comment = rs.getString("comments");
				column.setOptions(parseOptions(comment));
				comment = parseCanonicalComment(comment);
				column.setCommon(StringUtils.isBlank(comment) ? name : comment);
				String defaultValue = rs.getString("DATA_DEFAULT");
				if (defaultValue != null) {
					defaultValue = defaultValue.trim();
				}
				column.setDefaultValue(defaultValue);
				// 最后处理数据类型
				databaseType = rs.getString("type");
				column.setDataType(parseJavaType(databaseType, column));
				columns.add(column);
			}
			if (columns == null || columns.size() == 0) {
				throw new RuntimeException("表不存在或没有正确定义");
			}
			tableMetadata.setColumns(columns);
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

	public String getEntityIdDeclare(String tableName) {
		String declare = "@GeneratedValue(generator = \"sequence\")\n"
		        + "	@GenericGenerator(name = \"sequence\", strategy = \"sequence\", parameters = { @Parameter(name = \"sequence\", value = \"SEQ_"
		        + tableName + "\") })";
		return declare;
	}

	protected String getTableComment(String tableName) {
		Connection conn = null;
		String tableComments = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(TABLE_METADATA_SQL);
			stmt.setString(1, tableName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tableComments = rs.getString(1);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			tableComments = tableName;
			logger.warning("获取表备注失败，采用表名称作为备注名。");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// ig
				}
			}
		}
		return tableComments;
	}

	@Override
	protected ColumnDataType doConvertJavaType(String databaseType, Column column) {
		JavaType javaType = null;
		if (StringUtils.equalsIgnoreCase(databaseType, "NUMBER")) {
			if (column.getScale() > 0) {
				javaType = JavaType.pDouble;
			} else {
				javaType = column.getLength() <= 4 ? JavaType.Int : JavaType.Long;
			}
		} else if (databaseType.equalsIgnoreCase("DATE") || databaseType.equalsIgnoreCase("timestamp")) {
			return new ColumnDataType(databaseType, JavaType.Date, "java.util.Date");
		} else {
			javaType = JavaType.String;
		}
		return new ColumnDataType(databaseType, javaType);

	}

	@Override
	public Database getDatabase() {
		return Database.ORACLE;
	}

}
