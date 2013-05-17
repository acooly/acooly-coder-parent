package com.acooly.module.coder;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.acooly.module.coder.db.dialect.MetadataLoadDialect;
import com.acooly.module.coder.db.metadata.TableMetadata;
import com.acooly.module.coder.module.ModuleGenerator;
import com.acooly.module.coder.parser.NamesHold;
import com.acooly.module.coder.parser.NamesResolver;

public class DefaultCodeGeneratorFactory implements CodeGeneratorFactory, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(DefaultCodeGeneratorFactory.class);

	private GenerateConfiguration generateConfiguration;

	private MetadataLoadDialect metadataLoadDialect;

	private NamesResolver namesResolver;

	private ApplicationContext applicationContext;

	@Override
	public void generateTable(String tableName) {
		try {
			GenerateContext generateContext = loadGenerateContext(tableName);
			Map<String, ModuleGenerator> moduleGeneratorMaps = applicationContext.getBeansOfType(ModuleGenerator.class);
			logger.debug("Find reigstered ModuleGenerator: " + moduleGeneratorMaps.size() + " --> " + moduleGeneratorMaps);
			for (Map.Entry<String, ModuleGenerator> entry : moduleGeneratorMaps.entrySet()) {
				entry.getValue().generate(generateContext);
			}
			logger.info("Generate from [" + tableName + "] to Code/Resource/Pages success.");
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
		TableMetadata tableMetadata = getMetadataLoadDialect().loadTableMetadata(tableName);
		String entityIdDeclare = getMetadataLoadDialect().getEntityIdDeclare(tableName);
		NamesHold namesHold = namesResolver.resolve(tableName);
		GenerateContext generateContext = new GenerateContext();
		logger.debug("Configurations:\n"+getGenerateConfiguration());
		generateContext.setConfiguration(getGenerateConfiguration());
		generateContext.setTable(tableMetadata);
		generateContext.setEntityIdDeclare(entityIdDeclare);
		generateContext.setNames(namesHold);
		return generateContext;
	}

	public GenerateConfiguration getGenerateConfiguration() {
		return generateConfiguration;
	}

	public MetadataLoadDialect getMetadataLoadDialect() {
		return metadataLoadDialect;
	}

	public void setMetadataLoadDialect(MetadataLoadDialect metadataLoadDialect) {
		this.metadataLoadDialect = metadataLoadDialect;
	}

	public void setGenerateConfiguration(GenerateConfiguration generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}

	public NamesResolver getNamesResolver() {
		return namesResolver;
	}

	public void setNamesResolver(NamesResolver namesResolver) {
		this.namesResolver = namesResolver;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
