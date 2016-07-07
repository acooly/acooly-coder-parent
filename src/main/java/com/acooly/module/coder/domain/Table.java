package com.acooly.module.coder.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

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
	private Map<String, Object> properties = new HashMap<String, Object>();
	/** 列信息 */
	private List<Column> columns = new LinkedList<Column>();

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

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Set<String> getImportDeclares() {
		Set<String> declares = new HashSet<String>();
		for (Column col : columns) {
			if (StringUtils.isNotBlank(col.getDataType().getJavaDeclare())) {
				declares.add(col.getDataType().getJavaDeclare());
			}
		}
		return declares;
	}

}
