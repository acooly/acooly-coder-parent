package ${nameScheme.serviceImplPackage};

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import ${nameScheme.servicePackage}.${nameScheme.serviceClassName};
import ${nameScheme.daoPackage}.${nameScheme.daoClassName};
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

@Service("${nameScheme.serviceClassName?uncap_first}")
public class ${nameScheme.serviceImplClassName} extends EntityServiceImpl<${nameScheme.domainClassName}, ${nameScheme.daoClassName}> implements ${nameScheme.serviceClassName} {

}
