/**
 * create by zhangpu
 * date:2015年8月30日
 */
package com.acooly.coder.resolver.impl;

import com.acooly.coder.config.Database;
import com.acooly.coder.domain.Table;
import com.acooly.coder.resolver.EntityIdDeclareResolver;

/**
 * @author zhangpu
 * @date 2015年8月30日
 */
public class DefaultEntityIdDeclareResolver implements EntityIdDeclareResolver {

	@Override
	public String getEntityIdDeclare(Database database, Table table) {

		if (database == Database.ORACLE) {
			String declare = "@GeneratedValue(generator = \"sequence\")\n"
			        + "	@GenericGenerator(name = \"sequence\", strategy = \"sequence\", parameters = { @Parameter(name = \"sequence\", value = \"SEQ_"
			        + table.getName() + "\") })";
			return declare;
		}
		return "@GeneratedValue";
	}

}
