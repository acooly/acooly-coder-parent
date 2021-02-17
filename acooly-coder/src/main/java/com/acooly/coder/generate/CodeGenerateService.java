package com.acooly.coder.generate;

import com.acooly.coder.config.GenerateConfig;
import com.acooly.coder.generate.event.AcoolyCoderListener;

/**
 * 代码生成服务接口
 *
 * @author zhangpu
 * TODO: 增加对file上传的支持 2021/2/18
 * TODO: BUG:采用alisa方式的选项没有在controler中设置mapping
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
