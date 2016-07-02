/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhangpu 
 * date:2016年7月1日
 *
 */
package com.acooly.module.coder.config;

import com.acooly.module.coder.GenerateConstants;

/**
 * @author zhangpu
 */
public class DatabaseConfig {

	private String driver = GenerateConstants.JDBC_DRIVER;
	private String url = GenerateConstants.JDBC_URL;
	private String username = GenerateConstants.JDBC_USERNAME;
	private String password = GenerateConstants.JDBC_PASSWORD;

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

	@Override
	public String toString() {
		return "DatabaseConfig [url=" + url + ", username=" + username + "]";
	}

}
