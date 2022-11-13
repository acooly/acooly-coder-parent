package com.acooly.coder.support;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 位运算权限控制工具
 *
 * @author zhangpu
 * @date 2018-12-06 15:43
 */
public class BitPermissions {


    /**
     * 与运算权限控制：判断是否有权限
     *
     * @param permValue 你的权限值 (你拥有权限的权限单元值的代数和，如：3表示你拥有1+2两个权限)
     * @param permUnit  权限单元值，每个值代表一个权限 （2的幂次方：1,2,4,8,16,32,64,128...）
     * @return
     */
    public static boolean hasPerm(int permValue, int permUnit) {
        checkPermUnit(permUnit);
        return (permValue & permUnit) == permUnit;
    }


    /**
     * 与运算权限控制：计算权限值
     *
     * @param permUnits 权限单元值集合
     * @return
     */
    public static int calcPerm(Integer... permUnits) {
        return calcPerm(Arrays.asList(permUnits));
    }

    public static int calcPerm(List<Integer> permUnits) {
        int permValue = 0;
        for (int permUnit : permUnits) {
            checkPermUnit(permUnit);
            permValue = permValue + permUnit;
        }
        return permValue;
    }


    private static void checkPermUnit(int permUnit) {
        if ((permUnit & (permUnit - 1)) != 0) {
            throw new RuntimeException("权限单元permUnit必须是2的幂次方");
        }
    }

}
