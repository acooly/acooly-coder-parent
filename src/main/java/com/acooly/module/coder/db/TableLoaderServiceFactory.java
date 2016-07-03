package com.acooly.module.coder.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.acooly.module.coder.config.DatabaseConfig;
import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.db.dialect.MySQLTableLoaderService;
import com.acooly.module.coder.db.dialect.OracleTableLoaderService;
import com.acooly.module.coder.support.SimpleDataSource;

/**
 * 数据库库元数据loader工厂
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public class TableLoaderServiceFactory {

	public static TableLoaderService getTableLoaderDialect() {
		DataSource dataSource = getDataSource();
		String jdbcUrl = getJdbcUrlFromDataSource(dataSource);
		// 根据jdbc url判断dialect
		TableLoaderService dialect = null;
		if (StringUtils.contains(jdbcUrl, ":mysql:")) {
			dialect = new MySQLTableLoaderService(dataSource, getMysqlschema(jdbcUrl));
		} else if (StringUtils.contains(jdbcUrl, ":oracle:")) {
			dialect = new OracleTableLoaderService(dataSource);
		} else {
			throw new IllegalArgumentException("Unknown Database of " + jdbcUrl);
		}
		return dialect;
	}

	private static DataSource getDataSource() {
		DatabaseConfig config = GenerateConfig.INSTANCE().getDatabaseConfig();
		System.out.println("database config: " + config);
		return new SimpleDataSource(config.getDriver(), config.getUrl(), config.getUsername(), config.getPassword());
	}

	private static String getMysqlschema(String jdbcUrl) {
		String scheme = StringUtils.substringAfterLast(jdbcUrl, "/");
		if (StringUtils.contains(scheme, "?")) {
			scheme = StringUtils.substringBefore(scheme, "?");
		}
		return scheme;
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
