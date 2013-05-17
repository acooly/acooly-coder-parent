package com.acooly.module.coder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Generate {

	public static void main(String[] args) {

		// create the command line parser
		CommandLineParser parser = new PosixParser();
		// create the Options
		Options options = new Options();
		options.addOption("W", "workspace", true,
				"Code generation target main directory. you can also be configured in application.properties");
		options.addOption("P", "package", true,
				"The root of the package of the generated code. you can also be configured in application.properties");

		String workspace = null;
		String rootPackage = null;
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
			tables = line.getArgs();
			if (tables == null || tables.length == 0) {
				throw new RuntimeException();
			}
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
		codeGeneratorFactory.generateTables(tables);

	}

}
