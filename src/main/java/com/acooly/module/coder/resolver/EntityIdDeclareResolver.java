/**
 * create by zhangpu
 * date:2015年8月30日
 */
package com.acooly.module.coder.resolver;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.domain.Table;

/**
 * 实体Id解析器
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public interface EntityIdDeclareResolver {

	String getEntityIdDeclare(Database database, Table table);

}
