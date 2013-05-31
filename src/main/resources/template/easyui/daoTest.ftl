package ${names.daoTestPackage};

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
import ${names.domainPackage}.${names.domainClassName};

@ActiveProfiles("development")
@ContextConfiguration(locations = "classpath:applicationContext-test-main.xml")
@TransactionConfiguration(defaultRollback = true)
public class ${names.daoTestClassName} extends SpringTransactionalTests {

	private static final Logger logger = LoggerFactory.getLogger(${names.daoTestClassName}.class);
	private static final String TABLE_NAME = "${table.name}";
	
	@Resource
	${names.daoClassName} ${names.daoClassName?uncap_first};

	@Test
	public void testCreate() {
		${names.domainClassName} ${names.domainClassName?uncap_first} = generateNewEntity();
		try {
			${names.daoClassName?uncap_first}.create(${names.domainClassName?uncap_first});
		} catch (Exception e) {
			Assert.fail("testCreate fail. --> " + e.getMessage());
		}
		Assert.assertNotNull("testCreate fail. saved Id is null", ${names.domainClassName?uncap_first}.getId());
		logger.info("test ${names.domainClassName} Create Successful.");
	}

	@Test
	public void testUpdate() {
		${names.domainClassName} ${names.domainClassName?uncap_first} = generateNewEntity();
		try {
			// 先创建一个对象，再修改
			${names.daoClassName?uncap_first}.create(${names.domainClassName?uncap_first});
			${names.daoClassName?uncap_first}.update(${names.domainClassName?uncap_first});
		} catch (Exception e) {
			Assert.fail("testUpdate fail. --> " + e.getMessage());
		}
		logger.info("test ${names.daoClassName?uncap_first} Update Successful.");
	}

	@Test
	public void testDelete() {
		${names.domainClassName} ${names.domainClassName?uncap_first} = generateNewEntity();
		try {
			${names.daoClassName?uncap_first}.save(${names.domainClassName?uncap_first});
			Long savedId = ${names.domainClassName?uncap_first}.getId();
			${names.daoClassName?uncap_first}.delete(savedId);
		} catch (Exception e) {
			Assert.fail("testDelete fail. --> " + e.getMessage());
		}
		logger.info("test ${names.daoClassName?uncap_first} Delete Successful.");
	}

	@Test
	public void testGet() {
		${names.domainClassName} ${names.domainClassName?uncap_first} = generateNewEntity();
		try {
			${names.daoClassName?uncap_first}.save(${names.domainClassName?uncap_first});
			${names.domainClassName} get${names.domainClassName} = ${names.daoClassName?uncap_first}.get(${names.domainClassName?uncap_first}.getId());
			Assert.assertNotNull(get${names.domainClassName});
		} catch (Exception e) {
			Assert.fail("testGet fail. --> " + e.getMessage());
		}
		logger.info("test ${names.daoClassName?uncap_first} Get Successful.");
	}

	

	@After
	public void clean() {
		deleteFromTables(TABLE_NAME);
	}

	private ${names.domainClassName} generateNewEntity() {
		${names.domainClassName} ${names.domainClassName?uncap_first} = new ${names.domainClassName}();
		simpleFillEntity(${names.domainClassName?uncap_first});
		${names.domainClassName?uncap_first}.setId(null);
		return ${names.domainClassName?uncap_first};
	}

}
