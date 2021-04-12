package com.acooly.coder.generate.impl;

import com.acooly.coder.config.Database;
import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.db.TableLoaderService;
import com.acooly.coder.db.TableLoaderServiceFactory;
import com.acooly.coder.domain.Column;
import com.acooly.coder.domain.JavaType;
import com.acooly.coder.domain.Table;
import com.acooly.coder.generate.CodeGenerateService;
import com.acooly.coder.generate.GenerateContext;
import com.acooly.coder.generate.event.AcoolyCoderEvent;
import com.acooly.coder.generate.event.AcoolyCoderListener;
import com.acooly.coder.module.ModuleGenerator;
import com.acooly.coder.module.ModuleGeneratorFactory;
import com.acooly.coder.resolver.EntityIdDeclareResolver;
import com.acooly.coder.resolver.NameScheme;
import com.acooly.coder.resolver.NameSchemeResolver;
import com.acooly.coder.resolver.impl.AcoolyNameSchemeResolver;
import com.acooly.coder.resolver.impl.DefaultEntityIdDeclareResolver;
import com.acooly.coder.support.GenerateUtils;
import com.acooly.coder.support.LogManager;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 代码生成 默认实现
 *
 * @author zhangpu
 * @date 2015年4月30日
 */
public class DefaultCodeGenerateService implements CodeGenerateService {

    protected static Logger logger = LogManager.getLogger(DefaultCodeGenerateService.class);

    private GenerateConfig generateConfig = GenerateConfig.INSTANCE();

    private TableLoaderService tableLoaderService = TableLoaderServiceFactory.getTableLoaderDialect();

    private NameSchemeResolver nameSchemeResolver = new AcoolyNameSchemeResolver();

    private EntityIdDeclareResolver entityIdDeclareResolver = new DefaultEntityIdDeclareResolver();


    @Override
    public void generateTable(AcoolyCoderListener listener, String... tableNames) {
        Set<String> tables = getTables(tableNames);
        for (String tableName : tables) {
            doGenerate(tableName, tables, listener);
        }
    }


    @Override
    public void generateTable(String... tableNames) {
        generateTable(null, tableNames);
    }


    protected void doGenerate(String tableName, Set<String> tables, AcoolyCoderListener acoolyCoderListener) {
        try {
            GenerateContext generateContext = loadGenerateContext(tableName);
            Map<String, ModuleGenerator> moduleGeneratorMaps = ModuleGeneratorFactory.registies(generateConfig);
            List<String> steps = moduleGeneratorMaps.values().stream().map(ModuleGenerator::getGenerateKey).collect(Collectors.toList());
            for (Map.Entry<String, ModuleGenerator> entry : moduleGeneratorMaps.entrySet()) {
                entry.getValue().generate(generateContext);
                if (acoolyCoderListener != null) {
                    AcoolyCoderEvent event = new AcoolyCoderEvent();
                    event.setTables(new ArrayList<>(tables));
                    event.setTable(tableName);
                    event.setSteps(steps);
                    event.setStep(entry.getValue().getGenerateKey());
                    acoolyCoderListener.notice(event);
                }
            }
            logger.info("success generated table: " + tableName);
        } catch (Exception e) {
            logger.warning("Generate Table fail. tableName: " + tableName + ", e:" + e.getMessage());
        }
    }

    protected Set<String> getTables(String... tableNames) {
        Set<String> tables = new HashSet<>();
        for (String tableName : tableNames) {
            if (tableName.contains("*")) {
                List<String> allTables = tableLoaderService.getTableNames();
                if (tableName.equals("*")) {
                    tables.addAll(allTables);
                    break;
                } else if (tableName.endsWith("*")) {
                    String tb = tableName.replace("*", "");
                    for (String name : tableNames) {
                        if (name.startsWith(tb)) {
                            tables.add(tb);
                        }
                    }
                }
            } else {
                tables.add(tableName);
            }
        }
        if (tables == null || tables.size() == 0) {
            throw new RuntimeException("tableName不能为空,支持*");
        }
        return tables;
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
        // 对枚举类型，如果generator.enum.enable=false，则全部转换为String
        if (!generateContext.getConfiguration().isEnumEnable()) {
            logger.info("配置参数generator.enum.enable=false，不生成枚举方案");
            for (Column column : generateContext.getTable().getColumns()) {
                if (column.getDataType().getJavaType() == JavaType.Enum) {
                    column.getDataType().setJavaType(JavaType.String);
                    column.getDataType().setJavaDeclare(null);
                    column.setOptions(null);
                }
            }
            return;
        }


        // 对枚举类型，rebuild package declare
        String enumName = null;
        for (Column column : generateContext.getTable().getColumns()) {
            if (column.getDataType().getJavaType() != JavaType.Enum) {
                continue;
            }
            if (StringUtils.isNotBlank(column.getDataType().getJavaName())) {
                continue;
            }
            enumName = StringUtils.capitalize(column.getPropertyName()) + "Enum";
            if (generateContext.getConfiguration().isEnumNameAssemble()) {
                enumName = generateContext.getNameScheme().getDomainClassName() + enumName;
            }
            column.getDataType().setJavaName(GenerateUtils.getCanonicalClassName(enumName));
            column.getDataType().setJavaDeclare(generateContext.getNameScheme().getEnums().getPackageName() + "."
                    + GenerateUtils.getCanonicalClassName(enumName));
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
