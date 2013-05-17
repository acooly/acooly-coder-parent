package com.acooly.module.coder;

public interface CodeGeneratorFactory {

	void generateTable(String tableName);

	void generateTables(String... tableNames);

}
