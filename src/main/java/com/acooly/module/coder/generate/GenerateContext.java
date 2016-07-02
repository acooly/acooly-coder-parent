package com.acooly.module.coder.generate;

import java.util.HashMap;
import java.util.Map;

import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.resolver.NameScheme;

/**
 * 代码生成器上下文
 * 
 * @author zhangpu
 * 
 */
public class GenerateContext {

	private GenerateConfig configuration;
	private Table table;
	private String entityIdDeclare;
	private NameScheme nameScheme;
	private Map<String, Object> data = new HashMap<String, Object>();

	public GenerateConfig getConfiguration() {
		return configuration;
	}

	public void setConfiguration(GenerateConfig configuration) {
		this.configuration = configuration;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public NameScheme getNameScheme() {
		return nameScheme;
	}

	public void setNameScheme(NameScheme nameScheme) {
		this.nameScheme = nameScheme;
	}

	public String getEntityIdDeclare() {
		return entityIdDeclare;
	}

	public void setEntityIdDeclare(String entityIdDeclare) {
		this.entityIdDeclare = entityIdDeclare;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void appendData(String key, Object value) {
		this.data.put(key, value);
	}

}
