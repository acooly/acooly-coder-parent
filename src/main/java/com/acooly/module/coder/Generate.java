package com.acooly.module.coder;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acooly.module.coder.generate.impl.DefaultCodeGeneratorFactory;
import com.google.common.collect.Lists;

public class Generate {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		CommandLineParser parser = new PosixParser();
		Options options = new Options();
		Option o = OptionBuilder
				.withArgName("ignorePrefix")
				.hasArg()
				.withDescription(
						"Ignore prefix that the table name convert to entity name. you can also be configured in application.properties")
				.create("i");
		o.setOptionalArg(true);
		options.addOption(o);

		o = OptionBuilder
				.withArgName("viewPath")
				.hasArg()
				.withDescription(
						"The views relative path in your webapp. you can also be configured in application.properties")
				.create("v");
		o.setOptionalArg(true);
		options.addOption(o);

		o = OptionBuilder
				.withArgName("package")
				.hasArg()
				.withDescription(
						"The root of the package of the generated code. you can also be configured in application.properties")
				.create("p");
		o.setOptionalArg(true);
		options.addOption(o);

		o = OptionBuilder
				.withArgName("workspace")
				.hasArg()
				.withDescription(
						"Code generation target main directory. you can also be configured in application.properties")
				.create("w");
		o.setOptionalArg(true);
		options.addOption(o);

		o = OptionBuilder
				.withArgName("tableViews")
				.hasArg()
				.withDescription(
						"target tables or views in your database.")
				.create("t");
		o.setOptionalArg(false);
		options.addOption(o);

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
