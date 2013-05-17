package com.acooly.module.coder.db.dialect;

import java.util.List;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.db.metadata.TableMetadata;

public interface MetadataLoadDialect {

	Database getDatabase();

	TableMetadata loadTableMetadata(String tableName);

	String getEntityIdDeclare(String tableName);

	List<String> loadTables();

}
