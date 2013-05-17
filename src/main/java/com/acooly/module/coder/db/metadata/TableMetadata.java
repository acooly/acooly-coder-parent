package com.acooly.module.coder.db.metadata;

import java.util.LinkedList;
import java.util.List;

public class TableMetadata {

	private String name;
	private String comment;
	/** 列信息 */
	private List<ColumnMetadata> columnMetadatas = new LinkedList<ColumnMetadata>();
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
	public List<ColumnMetadata> getColumnMetadatas() {
		return columnMetadatas;
	}
	public void setColumnMetadatas(List<ColumnMetadata> columnMetadatas) {
		this.columnMetadatas = columnMetadatas;
	}

}
