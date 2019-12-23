/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 */
 package ${nameScheme.daoPackage};

import com.acooly.module.mybatis.EntityMybatisDao;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

/**
 * ${table.comment} Mybatis Dao
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${nameScheme.daoClassName} extends EntityMybatisDao<${nameScheme.domainClassName}> {

}
