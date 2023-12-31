package com.acooly.coder.support;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigurableConstants {
    protected static Logger logger = LogManager.getLogger(ConfigurableConstants.class);
    protected static Properties p = new Properties();

    protected static void init(String propertyFileName) {
        InputStream in = null;
        try {
            in = ConfigurableConstants.class.getClassLoader().getResourceAsStream(propertyFileName);
            if (in != null) {
                p.load(in);
                logger.info("load config:" + propertyFileName);
            } else {
                throw new RuntimeException("加载配置文件失败. file: " + propertyFileName);
            }
        } catch (IOException e) {
            logger.warning("load " + propertyFileName + " into Constants error!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.warning("close " + propertyFileName + " error!");
                }
            }
        }
    }

    /**
     * 支持profile
     *
     * @param propertyFileName
     */
    protected static void initWithProfile(String propertyFileName) {
        String activeProfile = System.getProperty("spring.profiles.active");
        String configFile = propertyFileName;
        if (StringUtils.isNotBlank(activeProfile)) {
            configFile = StringUtils.substringBeforeLast(configFile, ".") + "." + activeProfile + "."
                    + StringUtils.substringAfterLast(configFile, ".");
        }
        init(configFile);
    }

    protected static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }

}
