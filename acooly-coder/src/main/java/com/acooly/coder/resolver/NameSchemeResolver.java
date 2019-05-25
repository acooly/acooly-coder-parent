package com.acooly.coder.resolver;

/**
 * 命名解析器 接口
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public interface NameSchemeResolver {

	NameScheme resolve(String tableName);

}
