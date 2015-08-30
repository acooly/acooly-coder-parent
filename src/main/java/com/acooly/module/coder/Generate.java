package com.acooly.module.coder;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acooly.module.coder.generate.impl.DefaultCodeGeneratorFactory;
import com.google.common.collect.Lists;

public class Generate {

	public static void main(String[] args) {
		CommandLineParser parser = new PosixParser();
		Options options = new Options();
		options.addOption("i", "ignorePrefix", true,
				"Ignore prefix that the table name convert to entity name. you can also be configured in application.properties");
		options.addOption("v", "viewPath", true,
				"The views relative path in your webapp. you can also be configured in application.properties");
		options.addOption("P", "package", true,
				"The root of the package of the generated code. you can also be configured in application.properties");
		options.addOption("W", "workspace", true,
				"Code generation target main directory. you can also be configured in application.properties");

		String workspace = null;
		String rootPackage = null;
		String viewPath = null;
		String ignorePrefix = null;
		String[] tables = null;
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("workspace")) {
				workspace = StringUtils.trimToEmpty(line.getOptionValue("workspace"));
			}
			if (line.hasOption("package")) {
				rootPackage = StringUtils.trimToEmpty(line.getOptionValue("package"));
			}
			if (line.hasOption("viewPath")) {
				viewPath = StringUtils.trimToEmpty(line.getOptionValue("viewPath"));
			}
			if (line.hasOption("ignorePrefix")) {
				ignorePrefix = StringUtils.trimToEmpty(line.getOptionValue("ignorePrefix"));
			}
			String[] tbs = line.getArgs();
			if (tbs == null || tbs.length == 0) {
				throw new RuntimeException();
			}
			List<String> listTbs = Lists.newArrayList();
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
			formatter.printHelp("generate [options] tableName1 tableName2 tableNameN ...", options);
			return;
		}

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
		DefaultCodeGeneratorFactory codeGeneratorFactory = (DefaultCodeGeneratorFactory) context
				.getBean("codeGeneratorFactory");

		if (StringUtils.isNotBlank(workspace)) {
			codeGeneratorFactory.getGenerateConfiguration().setWorkspace(workspace);
		}
		if (StringUtils.isNotBlank(rootPackage)) {
			codeGeneratorFactory.getGenerateConfiguration().setRootPackage(rootPackage);
		}
		if (StringUtils.isNotBlank(viewPath)) {
			codeGeneratorFactory.getGenerateConfiguration().setPagePath(viewPath);
		}
		if (StringUtils.isNotBlank(ignorePrefix)) {
			codeGeneratorFactory.getGenerateConfiguration().setTableToEntityIgnorPrefix(ignorePrefix);
		}
		codeGeneratorFactory.generateTables(tables);

	}

}
