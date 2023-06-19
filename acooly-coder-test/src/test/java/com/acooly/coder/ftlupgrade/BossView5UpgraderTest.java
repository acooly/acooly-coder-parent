/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-06-17 23:16
 */
package com.acooly.coder.ftlupgrade;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2023-06-17 23:16
 */
@Slf4j
public class BossView5UpgraderTest {

    @Test
    public void testUpgradeList() {
        BossView5Upgrader.upgradeList("classpath:templates/manage/coder/demo1/customer1.ftl");
    }

}
