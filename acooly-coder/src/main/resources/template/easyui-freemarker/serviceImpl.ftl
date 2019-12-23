/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 */
package ${nameScheme.serviceImplPackage};

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
import ${nameScheme.daoPackage}.${nameScheme.daoClassName};
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

/**
 * ${table.comment} Service实现
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
@Service("${nameScheme.serviceClassName?uncap_first}")
public class ${nameScheme.serviceImplClassName} extends EntityServiceImpl<${nameScheme.domainClassName}, ${nameScheme.daoClassName}> implements ${nameScheme.serviceClassName} {

}
