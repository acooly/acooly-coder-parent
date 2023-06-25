/**
 * acooly-coder-parent
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2023-06-17 23:16
 */
package com.acooly.coder.ftlupgrade;

import com.acooly.coder.upgrade.BossFtl5Upgrader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2023-06-17 23:16
 */
@Slf4j
public class BossView5UpgraderTest {

    String testPath = "/Users/zhangpu/workspace/acooly/v5.2/acooly-coder-parent/acooly-coder-platform/acooly-coder-platform-core/src/main/resources/templates/manage/coder/demo1/";

    @Test
    public void testUpgradeList() {
        BossFtl5Upgrader.upgradeList(
                testPath + "customer1.ftl",
                testPath + "customer.ftl");
    }

    @Test
    public void testUpgradeEdit() {
        BossFtl5Upgrader.upgradeEdit(testPath + "customerEdit1.ftl",
                testPath + "customerEdit.ftl");
    }

}
