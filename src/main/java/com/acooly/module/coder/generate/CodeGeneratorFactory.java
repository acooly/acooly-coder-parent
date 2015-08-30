package com.acooly.module.coder.generate;

public interface CodeGeneratorFactory {

	void generateTable(String tableName);

	void generateTables(String... tableNames);

}
