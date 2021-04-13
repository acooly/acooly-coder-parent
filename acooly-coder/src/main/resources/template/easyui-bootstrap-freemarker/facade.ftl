/*
 * ${configuration.codeCopyright} Inc.
 * Copyright (c) ${datetime("yyyy")} All Rights Reserved.
 * create by ${configuration.codeAuthor}
 * date:${datetime("yyyy-MM-dd")}
 *
 */
package ${nameScheme.facade.packageName};

import ${nameScheme.dto.packageName}.*;
import com.acooly.core.common.facade.*;

/**
 * ${table.comment} RemoteService接口
 *
 * @author ${configuration.codeAuthor}
 * @date ${datetime("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${nameScheme.facade.className} {

    /**
     * 分页查询
     *
     * @param pageOrder
     * @return
     */
    PageResult<${nameScheme.domainClassName}ListInfo> list(PageOrder pageOrder);

    /**
     * 新增
     *
     * @param order
     * @return
     */
    ResultBase create(SingleOrder<${nameScheme.domainClassName}CreateInfo> order);

    /**
     * 根据ID查询项目详情
     *
     * @param order
     * @return
     */
    SingleResult<${nameScheme.domainClassName}Info> read(SingleOrder<Long> order);


    /**
     * 更新
     *
     * @param order
     * @return
     */
    ResultBase update(SingleOrder<${nameScheme.domainClassName}UpdateInfo> order);

    /**
     * 根据ID删除
     *
     * @param order
     * @return
     */
    ResultBase delete(SingleOrder<Long> order);

}