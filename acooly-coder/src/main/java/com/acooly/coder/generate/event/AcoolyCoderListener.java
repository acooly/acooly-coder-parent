/**
 * acooly-coder
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-06-10 08:51
 */
package com.acooly.coder.generate.event;

/**
 * @author zhangpu
 * @date 2020-06-10 08:51
 */
@FunctionalInterface
public interface AcoolyCoderListener {

    void notice(AcoolyCoderEvent event);

}
