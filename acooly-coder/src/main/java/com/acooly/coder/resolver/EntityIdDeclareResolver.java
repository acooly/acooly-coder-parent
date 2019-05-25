/**
 * create by zhangpu
 * date:2015年8月30日
 */
package com.acooly.coder.resolver;

import com.acooly.coder.config.Database;
import com.acooly.coder.domain.Table;

/**
 * 实体Id解析器
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public interface EntityIdDeclareResolver {

	String getEntityIdDeclare(Database database, Table table);

}
