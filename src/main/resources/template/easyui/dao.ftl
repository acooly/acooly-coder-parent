package ${names.daoPackage};

import com.acooly.core.common.dao.jpa.EntityJpaDao;
import ${names.domainPackage}.${names.domainClassName};

/**
 * ${table.comment} JPA Dao
 *
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 *
 * @author Acooly Code Generator
 *
 */
public interface ${names.daoClassName} extends EntityJpaDao<${names.domainClassName}, Long> {

}
