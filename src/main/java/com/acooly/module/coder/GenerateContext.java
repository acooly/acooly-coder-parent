package com.acooly.module.coder;

import java.util.HashMap;
import java.util.Map;

import com.acooly.module.coder.db.metadata.TableMetadata;
import com.acooly.module.coder.parser.NamesHold;

/**
 * 代码生成器上下文
 * 
 * @author zhangpu
 * 
 */
public class GenerateContext {

	private GenerateConfiguration configuration;
	private TableMetadata table;
	private String entityIdDeclare;
	private NamesHold Names;

	private Map<String, Object> data = new HashMap<String, Object>();

	public GenerateConfiguration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(GenerateConfiguration configuration) {
		this.configuration = configuration;
	}
	public TableMetadata getTable() {
		return table;
	}
	public void setTable(TableMetadata table) {
		this.table = table;
	}
	public NamesHold getNames() {
		return Names;
	}
	public void setNames(NamesHold names) {
		Names = names;
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
