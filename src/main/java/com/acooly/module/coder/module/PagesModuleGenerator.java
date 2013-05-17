package com.acooly.module.coder.module;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acooly.module.coder.GenerateContext;
import com.acooly.module.coder.db.metadata.ColumnMetadata;

public class PagesModuleGenerator extends FreeMarkerModuleGenerator {

	private static final Logger logger = LoggerFactory
			.getLogger(PagesModuleGenerator.class);

	@Override
	protected void onGenerate(GenerateContext generateContext) {
		List<ColumnMetadata> columns = generateContext.getTable()
				.getColumnMetadatas();
		for (ColumnMetadata column : columns) {
			if (StringUtils.isBlank(column.getCommon())) {
				continue;
			}
			Map<Integer, String> options = parseCommentToOptionMap(column
					.getCommon());
			if (options != null && options.size() > 0) {
				generateContext.appendData("all" + column.getPropertyName()
						+ "s", options);
			}
		}
	}

	@Override
	protected String getOutputPath(GenerateContext generateContext,
			String template) {
		return generateContext.getNames().getPagePath();
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext,
			String template) {
		if (template.equalsIgnoreCase("listPage.ftl")) {
			return generateContext.getNames().getListPageName();
		} else if (template.equalsIgnoreCase("editPage.ftl")) {
			return generateContext.getNames().getEditPageName();
		} else {
			return generateContext.getNames().getImportPageName();
		}

	}

	/**
	 * 解析字段的备注中的定义的可选值，转换为MAP,用于界面开发
	 * 
	 * 字段备注格式： 字段名称 (key:value,key:value...)
	 * 
	 * @return
	 */
	private Map<Integer, String> parseCommentToOptionMap(String comment) {

		try {
			Matcher m = Pattern.compile("\\(.+\\)").matcher(comment);
			if (m.find()) {
				String data = m.group();
				data = StringUtils.substringAfter(data, "(");
				data = StringUtils.substringBefore(data, ")");
				String[] enties = data.split(",");
				Map<Integer, String> options = new TreeMap<Integer, String>();
				for (String entity : enties) {
					options.put(Integer.parseInt(entity.split(":")[0]),
							entity.split(":")[1]);
				}
				return options;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.warn("parse property comment to options Map fail.", e);
			return null;
		}
	}

	public static void main(String[] args) {
		String comment = "客户类型 (1:普通,2:VIP)";

		Pattern p = Pattern.compile("\\(.+\\)");
		Matcher m = p.matcher(comment);
		System.out.println(m.matches());
		System.out.println(m.find());
		System.out.println(m.start() + "," + m.end());

	}
}
