package com.acooly.coder.generate;

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

    /**
     * 代码生成（支持事件回调监听）
     *
     * @param acoolyCoderListener
     * @param tableNames
     */
    void generateTable(AcoolyCoderListener acoolyCoderListener, String... tableNames);

}
