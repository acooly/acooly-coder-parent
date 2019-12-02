package com.acooly.coder.generate;

import com.acooly.coder.config.GenerateConfig;

/**
 * 代码生成服务接口
 *
 * @author zhangpu
 */
public interface CodeGenerateService {

    /**
     * 根据配置文件生成代码
     *
     * @param tableNames
     */
    void generateTable(String... tableNames);

    /**
     * 可覆盖配置文件参数生成代码
     *
     * @param config
     * @param tableNames
     */
    void generateTable(GenerateConfig config, String... tableNames);

}
