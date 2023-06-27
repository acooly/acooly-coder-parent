/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-06-27 09:54
 */
package com.acooly.coder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangpu
 * @date 2023-06-27 09:54
 */
@Slf4j
public class ConnUrlParser {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/cool?serverTimezone=Asia/Shanghai&useTimezone=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false";
        if (StringUtils.contains(url, "?")) {
            url = StringUtils.substringBeforeLast(url, "?");
        }
        System.out.println(StringUtils.substringAfterLast(url, "/"));
    }
}
