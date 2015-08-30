package com.acooly.module.coder.generate.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.acooly.module.coder.db.Database;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.generate.CodeGeneratorFactory;
import com.acooly.module.coder.generate.GenerateConfiguration;
import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.module.ModuleGenerator;
import com.acooly.module.coder.resolver.EntityIdDeclareResolver;
import com.acooly.module.coder.resolver.NameScheme;
import com.acooly.module.coder.resolver.NameSchemeResolver;

/**
 * 代码生成 默认实现
 * 
 * @author zhangpu
 * @date 2015年4月30日
 */
public class DefaultCodeGeneratorFactory implements CodeGeneratorFactory, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(DefaultCodeGeneratorFactory.class);

	private GenerateConfiguration generateConfiguration;

	private TableLoaderService tableLoaderService;

	private NameSchemeResolver nameSchemeResolver;

	private EntityIdDeclareResolver entityIdDeclareResolver;

	private ApplicationContext applicationContext;

	@Override
	public void generateTable(String tableName) {
		try {
			GenerateContext generateContext = loadGenerateContext(tableName);
			Map<String, ModuleGenerator> moduleGeneratorMaps = applicationContext.getBeansOfType(ModuleGenerator.class);
			logger.debug("Find reigstered ModuleGenerator: " + moduleGeneratorMaps.size() + " --> "
					+ moduleGeneratorMaps);
			for (Map.Entry<String, ModuleGenerator> entry : moduleGeneratorMaps.entrySet()) {
				entry.getValue().generate(generateContext);
			}
			logger.info("Generate from [{}] success.", tableName);
		} catch (Exception e) {
			logger.error("Generate Table fail. --> tableName: " + tableName, e);
		}

	}

	@Override
	public void generateTables(String... tableNames) {
		for (String tableName : tableNames) {
			generateTable(tableName);
		}
	}

	protected GenerateContext loadGenerateContext(String tableName) {
		Table table = tableLoaderService.loadTable(tableName);
		Database database = tableLoaderService.getDatabase();
		String entityIdDeclare = entityIdDeclareResolver.getEntityIdDeclare(database, table);
		NameScheme nameScheme = nameSchemeResolver.resolve(tableName);
		GenerateContext generateContext = new GenerateContext();
		logger.debug("Configurations:\n" + getGenerateConfiguration());
		generateContext.setConfiguration(getGenerateConfiguration());
		generateContext.setTable(table);
		generateContext.setEntityIdDeclare(entityIdDeclare);
		generateContext.setNameScheme(nameScheme);
		return generateContext;
	}

	public GenerateConfiguration getGenerateConfiguration() {
		return generateConfiguration;
	}

	public void setGenerateConfiguration(GenerateConfiguration generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}

	public void setTableLoaderService(TableLoaderService tableLoaderService) {
		this.tableLoaderService = tableLoaderService;
	}

	public void setNameSchemeResolver(NameSchemeResolver nameSchemeResolver) {
		this.nameSchemeResolver = nameSchemeResolver;
	}

	public void setEntityIdDeclareResolver(EntityIdDeclareResolver entityIdDeclareResolver) {
		this.entityIdDeclareResolver = entityIdDeclareResolver;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
