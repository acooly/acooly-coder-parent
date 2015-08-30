package com.acooly.module.coder.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acooly.module.coder.generate.CodeGeneratorFactory;

/**
 * asdfs
 * 
 * Date : 2012-12-12
 * 
 * @author zhangpu
 * 
 */

public class CodeGeneratorMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
		CodeGeneratorFactory codeGeneratorFactory = (CodeGeneratorFactory) context.getBean("codeGeneratorFactory");
		codeGeneratorFactory.generateTable("MERCHANT");
	}

}
