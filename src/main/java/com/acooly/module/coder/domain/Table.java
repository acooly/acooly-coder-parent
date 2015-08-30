package com.acooly.module.coder.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 表元数据
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public class Table {

	/** 表名 */
	private String name;
	/** 表备注 */
	private String comment;
	/** 扩展信息 */
	private Map<String, Object> properties = Maps.newHashMap();
	/** 列信息 */
	private List<TableColumn> columnMetadatas = new LinkedList<TableColumn>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<TableColumn> getColumnMetadatas() {
		return columnMetadatas;
	}

	public void setColumnMetadatas(List<TableColumn> columnMetadatas) {
		this.columnMetadatas = columnMetadatas;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
