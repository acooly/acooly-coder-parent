package ${nameScheme.daoPackage};

import com.acooly.core.common.dao.jpa.EntityJpaDao;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

/**
 * ${table.comment} JPA Dao
 *
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 *
 * @author Acooly Code Generator
 *
 */
public interface ${nameScheme.daoClassName} extends EntityJpaDao<${nameScheme.domainClassName}, Long> {

}
