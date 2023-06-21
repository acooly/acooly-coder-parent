/*
 * acooly.cn Inc.
 * Copyright (c) 2023 All Rights Reserved.
 * create by acooly
 * date:2023-06-16
 *
 */
package com.acooly.coder.demo.facade.api;

import com.acooly.coder.demo.common.dto.*;
import com.acooly.core.common.facade.*;

/**
 * 代码生成客户信息 RemoteService接口
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
public interface CoderCustomerRemoteService {

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    PageResult<CoderCustomerListInfo> list(PageOrder pageOrder);

    /**
     * 新增
     *
     * @param order
     * @return
     */
    ResultBase create(SingleOrder<CoderCustomerCreateInfo> order);

    /**
     * 根据ID查询项目详情
     *
     * @param order
     * @return
     */
    SingleResult<CoderCustomerInfo> read(SingleOrder<Long> order);


    /**
     * 更新
     *
     * @param order
     * @return
     */
    ResultBase update(SingleOrder<CoderCustomerUpdateInfo> order);

    /**
     * 根据ID删除
     *
     * @param order
     * @return
     */
    ResultBase delete(SingleOrder<Long> order);

}