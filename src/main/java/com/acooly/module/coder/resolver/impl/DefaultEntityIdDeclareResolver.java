/**
 * create by zhangpu
 * date:2015年8月30日
 */
package com.acooly.module.coder.resolver.impl;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.resolver.EntityIdDeclareResolver;

/**
 * @author zhangpu
 * @date 2015年8月30日
 */
public class DefaultEntityIdDeclareResolver implements EntityIdDeclareResolver {

	@Override
	public String getEntityIdDeclare(Database database, Table table) {
		return "@GeneratedValue";
	}

}
