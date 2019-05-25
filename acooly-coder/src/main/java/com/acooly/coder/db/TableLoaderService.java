package com.acooly.coder.db;

import com.acooly.coder.config.Database;
import com.acooly.coder.domain.Table;

import java.util.List;

/**
 * 元数据loader方言
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public interface TableLoaderService {

	Database getDatabase();

	Table loadTable(String tableName);

	List<String> getTableNames();

}
