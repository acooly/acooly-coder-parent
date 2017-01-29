package com.acooly.coder.support;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class DatetimeMethod implements TemplateMethodModel {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
		Date date = new Date();
		String pattern = args.get(0).toString();
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (RuntimeException e) {
			return new SimpleDateFormat(DEFAULT_PATTERN).format(date);
		}
	}

}
