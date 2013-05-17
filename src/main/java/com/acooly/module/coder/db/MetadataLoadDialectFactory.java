package com.acooly.module.coder.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.acooly.module.coder.db.dialect.MSSQLMetadataLoadDialect;
import com.acooly.module.coder.db.dialect.MetadataLoadDialect;
import com.acooly.module.coder.db.dialect.MySQLMetadataLoadDialect;
import com.acooly.module.coder.db.dialect.OracleMetadataLoadDialect;

public class MetadataLoadDialectFactory {

	public static MetadataLoadDialect getMetadataLoadDialect(JdbcTemplate jdbcTemplate) {
		String jdbcUrl = getJdbcUrlFromDataSource(jdbcTemplate.getDataSource());
		// 根据jdbc url判断dialect
		MetadataLoadDialect dialect = null;
		if (StringUtils.contains(jdbcUrl, ":sqlserver:")) {
			dialect = new MSSQLMetadataLoadDialect(jdbcTemplate);
		} else if (StringUtils.contains(jdbcUrl, ":mysql:")) {
			dialect = new MySQLMetadataLoadDialect(jdbcTemplate);
		} else if (StringUtils.contains(jdbcUrl, ":oracle:")) {
			dialect = new OracleMetadataLoadDialect(jdbcTemplate);
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
