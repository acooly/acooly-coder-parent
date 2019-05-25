package ${names.serviceImplPackage};

import org.springframework.stereotype.Service;

import com.feinno.framework.common.service.EntityServiceImpl;
import ${names.servicePackage}.${names.serviceClassName};
import ${names.daoPackage}.${names.daoClassName};
import ${names.domainPackage}.${names.domainClassName};

@Service("${names.serviceClassName?uncap_first}")
public class ${names.serviceImplClassName} extends EntityServiceImpl<${names.domainClassName}, ${names.daoClassName}> implements ${names.serviceClassName} {

}
