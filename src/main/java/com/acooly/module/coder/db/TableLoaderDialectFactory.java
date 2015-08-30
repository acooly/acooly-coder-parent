package com.acooly.module.coder.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.acooly.module.coder.db.dialect.MSSQLTableLoaderDialect;
import com.acooly.module.coder.db.dialect.MySQLTableLoaderDialect;
import com.acooly.module.coder.db.dialect.OracleTableLoaderDialect;

/**
 * 数据库库元数据loader工厂
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public class TableLoaderDialectFactory {

	public static TableLoaderService getTableLoaderDialect(JdbcTemplate jdbcTemplate) {
		String jdbcUrl = getJdbcUrlFromDataSource(jdbcTemplate.getDataSource());
		// 根据jdbc url判断dialect
		TableLoaderService dialect = null;
		if (StringUtils.contains(jdbcUrl, ":sqlserver:")) {
			dialect = new MSSQLTableLoaderDialect(jdbcTemplate);
		} else if (StringUtils.contains(jdbcUrl, ":mysql:")) {
			dialect = new MySQLTableLoaderDialect(jdbcTemplate);
		} else if (StringUtils.contains(jdbcUrl, ":oracle:")) {
			dialect = new OracleTableLoaderDialect(jdbcTemplate);
		} else {
			throw new IllegalArgumentException("Unknown Database of " + jdbcUrl);
		}
		return dialect;
	}

	private static String getJdbcUrlFromDataSource(DataSource dataSource) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection == null) {
				throw new IllegalStateException("Connection returned by DataSource [" + dataSource + "] was null");
			}
			return connection.getMetaData().getURL();
		} catch (SQLException e) {
			throw new RuntimeException("Could not get database url", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
