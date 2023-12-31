/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-01-28 20:27 创建
 */
package com.acooly.coder.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author acooly
 */
public class LogManager {
//
//    // 初始化LogManager
//    static {
//        System.setProperty("org.freemarker.loggerLibrary","JUL");
//        // 读取配置文件
//        ClassLoader cl = LogManager.class.getClassLoader();
//        InputStream inputStream = null;
//        if (cl != null) {
//            inputStream = cl.getResourceAsStream("log.properties");
//        } else {
//            inputStream = ClassLoader
//                    .getSystemResourceAsStream("log.properties");
//        }
//        java.util.logging.LogManager logManager = java.util.logging.LogManager
//                .getLogManager();
//        try {
//            // 重新初始化日志属性并重新读取日志配置。
//            logManager.readConfiguration(inputStream);
//        } catch (SecurityException e) {
//            System.err.println(e);
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }

    /**
     * 获取日志对象
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class clazz) {
        Logger logger = Logger.getLogger("AcoolyCoder");
        return logger;
    }
}
