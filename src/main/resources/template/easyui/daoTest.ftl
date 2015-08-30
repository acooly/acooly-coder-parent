package ${nameScheme.daoTestPackage};

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.acooly.core.common.test.SpringTransactionalTests;
import ${nameScheme.domainPackage}.${nameScheme.domainClassName};

@ActiveProfiles("development")
@ContextConfiguration(locations = "classpath:applicationContext-test-main.xml")
@TransactionConfiguration(defaultRollback = true)
public class ${nameScheme.daoTestClassName} extends SpringTransactionalTests {

	private static final Logger logger = LoggerFactory.getLogger(${nameScheme.daoTestClassName}.class);
	private static final String TABLE_NAME = "${table.name}";
	
	@Resource
	${nameScheme.daoClassName} ${nameScheme.daoClassName?uncap_first};

	@Test
	public void testCreate() {
		${nameScheme.domainClassName} ${nameScheme.domainClassName?uncap_first} = generateNewEntity();
		try {
			${nameScheme.daoClassName?uncap_first}.create(${nameScheme.domainClassName?uncap_first});
		} catch (Exception e) {
			Assert.fail("testCreate fail. --> " + e.getMessage());
		}
		Assert.assertNotNull("testCreate fail. saved Id is null", ${nameScheme.domainClassName?uncap_first}.getId());
		logger.info("test ${nameScheme.domainClassName} Create Successful.");
	}

	@Test
	public void testUpdate() {
		${nameScheme.domainClassName} ${nameScheme.domainClassName?uncap_first} = generateNewEntity();
		try {
			// 先创建一个对象，再修改
			${nameScheme.daoClassName?uncap_first}.create(${nameScheme.domainClassName?uncap_first});
			${nameScheme.daoClassName?uncap_first}.update(${nameScheme.domainClassName?uncap_first});
		} catch (Exception e) {
			Assert.fail("testUpdate fail. --> " + e.getMessage());
		}
		logger.info("test ${nameScheme.daoClassName?uncap_first} Update Successful.");
	}

	@Test
	public void testDelete() {
		${nameScheme.domainClassName} ${nameScheme.domainClassName?uncap_first} = generateNewEntity();
		try {
			${nameScheme.daoClassName?uncap_first}.save(${nameScheme.domainClassName?uncap_first});
			Long savedId = ${nameScheme.domainClassName?uncap_first}.getId();
			${nameScheme.daoClassName?uncap_first}.delete(savedId);
		} catch (Exception e) {
			Assert.fail("testDelete fail. --> " + e.getMessage());
		}
		logger.info("test ${nameScheme.daoClassName?uncap_first} Delete Successful.");
	}

	@Test
	public void testGet() {
		${nameScheme.domainClassName} ${nameScheme.domainClassName?uncap_first} = generateNewEntity();
		try {
			${nameScheme.daoClassName?uncap_first}.save(${nameScheme.domainClassName?uncap_first});
			${nameScheme.domainClassName} get${nameScheme.domainClassName} = ${nameScheme.daoClassName?uncap_first}.get(${nameScheme.domainClassName?uncap_first}.getId());
			Assert.assertNotNull(get${nameScheme.domainClassName});
		} catch (Exception e) {
			Assert.fail("testGet fail. --> " + e.getMessage());
		}
		logger.info("test ${nameScheme.daoClassName?uncap_first} Get Successful.");
	}

	

	@After
	public void clean() {
		deleteFromTables(TABLE_NAME);
	}

	private ${nameScheme.domainClassName} generateNewEntity() {
		${nameScheme.domainClassName} ${nameScheme.domainClassName?uncap_first} = new ${nameScheme.domainClassName}();
		simpleFillEntity(${nameScheme.domainClassName?uncap_first});
		${nameScheme.domainClassName?uncap_first}.setId(null);
		return ${nameScheme.domainClassName?uncap_first};
	}

}
