package com.acooly.module.coder.generate.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import com.acooly.module.coder.config.Database;
import com.acooly.module.coder.config.GenerateConfig;
import com.acooly.module.coder.db.TableLoaderService;
import com.acooly.module.coder.db.TableLoaderServiceFactory;
import com.acooly.module.coder.domain.Column;
import com.acooly.module.coder.domain.JavaType;
import com.acooly.module.coder.domain.Table;
import com.acooly.module.coder.generate.CodeGenerateService;
import com.acooly.module.coder.generate.GenerateContext;
import com.acooly.module.coder.module.ModuleGenerator;
import com.acooly.module.coder.module.ModuleGeneratorFactory;
import com.acooly.module.coder.resolver.EntityIdDeclareResolver;
import com.acooly.module.coder.resolver.NameScheme;
import com.acooly.module.coder.resolver.NameSchemeResolver;
import com.acooly.module.coder.resolver.impl.AcoolyNameSchemeResolver;
import com.acooly.module.coder.resolver.impl.DefaultEntityIdDeclareResolver;
import com.acooly.module.coder.support.GenerateUtils;

/**
 * 代码生成 默认实现
 * 
 * @author zhangpu
 * @date 2015年4月30日
 */
public class DefaultCodeGenerateService implements CodeGenerateService {

	protected static Logger logger = Logger.getLogger(DefaultCodeGenerateService.class.getSimpleName());

	private GenerateConfig generateConfig = GenerateConfig.INSTANCE();

	private TableLoaderService tableLoaderService = TableLoaderServiceFactory.getTableLoaderDialect();

	private NameSchemeResolver nameSchemeResolver = new AcoolyNameSchemeResolver();

	private EntityIdDeclareResolver entityIdDeclareResolver = new DefaultEntityIdDeclareResolver();

	@Override
	public void generateTable(String tableName) {
		try {
			GenerateContext generateContext = loadGenerateContext(tableName);
			Map<String, ModuleGenerator> moduleGeneratorMaps = ModuleGeneratorFactory.getModuleGenerators();
			for (Map.Entry<String, ModuleGenerator> entry : moduleGeneratorMaps.entrySet()) {
				entry.getValue().generate(generateContext);
			}
			logger.info("Generate table success: " + tableName);
		} catch (Exception e) {
			logger.warning("Generate Table fail. tableName: " + tableName + ", e:" + e.getMessage());
		}
	}

	@Override
	public void generateTables(String... tableNames) {
		System.out.println("GenerateConfig:\n" + generateConfig);
		System.out.println("GenerateTables:" + Arrays.toString(tableNames));
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
		generateContext.setConfiguration(getGenerateConfiguration());
		generateContext.setTable(table);
		generateContext.setEntityIdDeclare(entityIdDeclare);
		generateContext.setNameScheme(nameScheme);
		onLoadGenerateContext(generateContext);
		return generateContext;
	}

	protected void onLoadGenerateContext(GenerateContext generateContext) {
		// 对枚举类型，rebuild package declare
		for (Column column : generateContext.getTable().getColumns()) {
			if (column.getDataType().getJavaType() == JavaType.Enum) {
				column.getDataType().setJavaDeclare(generateContext.getNameScheme().getEnumPackage() + "."
				        + GenerateUtils.getCanonicalClassName(column.getPropertyName()));
			}
		}
	}

	public GenerateConfig getGenerateConfiguration() {
		return generateConfig;
	}

	public void setGenerateConfiguration(GenerateConfig generateConfiguration) {
		this.generateConfig = generateConfiguration;
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

}
