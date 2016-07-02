/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月1日
 *
 */
package com.acooly.module.coder.support;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.acooly.module.coder.config.GenerateConfig;

/**
 * 简单 datasource 实现
 * 
 * @author zhangpu
 */
public class SimpleDataSource implements DataSource {

	private String driver = GenerateConfig.INSTANCE().getDatabaseConfig().getDriver();
	private String url = GenerateConfig.INSTANCE().getDatabaseConfig().getUrl();
	private String username = GenerateConfig.INSTANCE().getDatabaseConfig().getUsername();
	private String password = GenerateConfig.INSTANCE().getDatabaseConfig().getPassword();
	private int loginTimeout = 0;
	private PrintWriter logWriter = new PrintWriter(System.out);

	public SimpleDataSource() {
	}

	/**
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 */
	public SimpleDataSource(String driver, String url, String username, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return logWriter;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		this.logWriter = out;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		loginTimeout = seconds;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return loginTimeout;
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Class.forName(this.driver);
		} catch (Exception e) {
			// ig
		}
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		this.username = username;
		this.password = password;
		return getConnection();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return Logger.getLogger(this.getClass().getName());
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		if (iface == null) {
			return false;
		}

		if (iface.isInstance(this)) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface == null) {
			return null;
		}

		if (iface.isInstance(this)) {
			return (T) this;
		}

		return null;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
