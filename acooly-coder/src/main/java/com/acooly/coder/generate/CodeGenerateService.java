package com.acooly.coder.generate;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.event.AcoolyCoderListener;

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

    void generateTable(AcoolyCoderListener acoolyCoderListener, String... tableNames);


}
