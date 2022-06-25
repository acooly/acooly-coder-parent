package com.acooly.coder;

import com.acooly.coder.generate.CodeGenerateService;
import com.acooly.coder.generate.impl.DefaultCodeGenerateService;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成CLI工具
 *
 * @author zhangpu
 * <p>
 * todo:支持sortTime的排序功能生成
 * ok:解决实体生成的javadoc多了一个空行问题
 * todo:优化easyui的默认功能的图标（去除lg）
 * todo: 优化日期字段的实时验证（失去焦点时）
 */
public class Generator {

    public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();
        Options options = new Options();
        Option viewPathOption = Generator.buildOption("v",
                "The views relative path in your webapp. you can also be configured in application.properties",
                "viewPath");

        Option packageOption = Generator.buildOption("p",
                "The root of the package of the generated code. you can also be configured in application.properties",
                "package");

        Option workspaceOption = Generator.buildOption("w",
                "Code generation target main directory. you can also be configured in application.properties",
                "workspace");

        Option ignorePrefixOption = Generator.buildOption("i",
                "Ignore prefix that the table name convert to entity name. you can also be configured in application.properties",
                "ignorePrefix");

        Option tableOption = Generator.buildOption("t", "target tables or views separated by space in your database.", "table");

        options.addOption(tableOption);
        options.addOption(viewPathOption);
        options.addOption(packageOption);
        options.addOption(workspaceOption);
        options.addOption(ignorePrefixOption);

        String workspace = null;
        String rootPackage = null;
        String viewPath = null;
        String ignorePrefix = null;
        String tableViews = null;
        String[] tables = null;
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("w")) {
                workspace = StringUtils.trimToEmpty(line.getOptionValue("w"));
            }
            if (line.hasOption("p")) {
                rootPackage = StringUtils.trimToEmpty(line.getOptionValue("p"));
            }
            if (line.hasOption("v")) {
                viewPath = StringUtils.trimToEmpty(line.getOptionValue("v"));
            }
            if (line.hasOption("i")) {
                ignorePrefix = StringUtils.trimToEmpty(line.getOptionValue("i"));
            }
            if (line.hasOption("t")) {
                tableViews = StringUtils.trimToEmpty(line.getOptionValue("t"));
            }
            String[] tbs = StringUtils.split(tableViews);
            if (tbs == null || tbs.length == 0) {
                throw new RuntimeException();
            }
            List<String> listTbs = new ArrayList<String>();
            for (String tb : tbs) {
                if (!StringUtils.contains(tb, ",")) {
                    listTbs.add(tb);
                    continue;
                }
                for (String t : StringUtils.split(tb, ",")) {
                    listTbs.add(t);
                }
            }
            tables = listTbs.toArray(new String[0]);
        } catch (Exception exp) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("generator [options] -t tableName1 tableName2 tableNameN ...", options);
            return;
        }

        DefaultCodeGenerateService codeGeneratorFactory = new DefaultCodeGenerateService();

        if (StringUtils.isNotBlank(workspace)) {
            codeGeneratorFactory.getGenerateConfiguration().setWorkspace(workspace);
        }
        if (StringUtils.isNotBlank(rootPackage)) {
            codeGeneratorFactory.getGenerateConfiguration().setRootPackage(rootPackage);
        }
        if (StringUtils.isNotBlank(viewPath)) {
            codeGeneratorFactory.getGenerateConfiguration().setManagePath(viewPath);
        }
        if (StringUtils.isNotBlank(ignorePrefix)) {
            codeGeneratorFactory.getGenerateConfiguration().setTableToEntityIgnorPrefix(ignorePrefix);
        }
        codeGeneratorFactory.generateTable(tables);
    }

    @SuppressWarnings("static-access")
    private static Option buildOption(String arg, String desc, String argName) {
        Option viewPathOption = OptionBuilder.withArgName(argName).hasArg().withDescription(desc).create(arg);
        viewPathOption.setOptionalArg(true);
        return viewPathOption;
    }

    public static CodeGenerateService getGenerator() {
        return new DefaultCodeGenerateService();
    }

}
