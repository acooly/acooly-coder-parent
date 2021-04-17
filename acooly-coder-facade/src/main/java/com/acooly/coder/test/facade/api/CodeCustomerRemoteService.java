/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by acooly
 * date:2021-04-18
 *
 */
package com.acooly.coder.test.facade.api;

import com.acooly.coder.test.common.dto.*;
import com.acooly.core.common.facade.*;

/**
 * acoolycoder测试 RemoteService接口
 *
 * @author acooly
 * @date 2021-04-18 00:31:50
 */
public interface CodeCustomerRemoteService {

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    PageResult<CodeCustomerListInfo> list(PageOrder pageOrder);

    /**
     * 新增
     *
     * @param order
     * @return
     */
    ResultBase create(SingleOrder<CodeCustomerCreateInfo> order);

    /**
     * 根据ID查询项目详情
     *
     * @param order
     * @return
     */
    SingleResult<CodeCustomerInfo> read(SingleOrder<Long> order);


    /**
     * 更新
     *
     * @param order
     * @return
     */
    ResultBase update(SingleOrder<CodeCustomerUpdateInfo> order);

    /**
     * 根据ID删除
     *
     * @param order
     * @return
     */
    ResultBase delete(SingleOrder<Long> order);

}