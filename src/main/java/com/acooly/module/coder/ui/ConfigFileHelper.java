package com.acooly.module.coder.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigFileHelper {

	private static final String FILENAME = "application.properties";

	public static Properties loadProperty() {
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = ConfigFileHelper.class.getClassLoader().getResourceAsStream(FILENAME);
			if (in != null)
				p.load(in);
		} catch (Exception e) {
			System.out.println("load " + FILENAME + " int Properties error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("close " + FILENAME + " error!");
				}
			}
		}
		return p;
	}

	public static void saveProperties(Map<String, String> data) {

		try {
			PropertiesConfiguration config = new PropertiesConfiguration(FILENAME);
			for (Map.Entry<String, String> entry : data.entrySet()) {
				config.setProperty(entry.getKey(), entry.getValue());
			}
			config.save();
		} catch (Exception e) {
			System.out.println("save Properties to " + FILENAME + " error!");
		}

	}

	public static void saveDatabaseSetting(String driver) {

	}

	public static void saveRuleSetting() {

	}

	public static void saveParameters() {

	}

	public static void main(String[] args) throws Exception {
		PropertiesConfiguration config = new PropertiesConfiguration("application.properties");
		config.setProperty("generator.pagePath", "/admin/business");
		config.save();
		// System.out.println(config.getString("generator.pagePath"));

	}

}
