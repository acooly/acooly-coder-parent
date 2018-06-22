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
 * <p><b>注意:此类所有的方法都在事务中执行。<b/>
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 *
 * @author ${configuration.codeAuthor}
 *
 */
@Service("${nameScheme.serviceClassName?uncap_first}")
public class ${nameScheme.serviceImplClassName} extends EntityServiceImpl<${nameScheme.domainClassName}, ${nameScheme.daoClassName}> implements ${nameScheme.serviceClassName} {

}
