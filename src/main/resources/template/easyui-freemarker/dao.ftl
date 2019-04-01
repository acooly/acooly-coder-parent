/*
* ${configuration.codeCopyright} Inc.
* Copyright (c) ${datetime("yyyy")} All Rights Reserved.
* create by ${configuration.codeAuthor}
* date:${datetime("yyyy-MM-dd")}
*/
package ${nameScheme.daoPackage};

import com.acooly.module.jpa.EntityJpaDao;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

/**
 * ${table.comment} JPA Dao
 *
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 * @author ${configuration.codeAuthor}
 *
 */
public interface ${nameScheme.daoClassName} extends EntityJpaDao<${nameScheme.domainClassName}, Long> {

}
