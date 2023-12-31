package com.acooly.coder.module;

import com.acooly.coder.generate.GenerateContext;

/**
 * 模块生成器
 * 
 * @author zhangpu
 * @date 2015年8月30日
 */
public interface ModuleGenerator {

	void generate(GenerateContext generateContext);

	String getGenerateKey();

}
