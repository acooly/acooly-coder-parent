/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.common.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

/**
* 代码生成客户信息 List DTO
* For facade and openApi
*
* @author acooly
* @date 2023-06-16 15:23:11
*/
@Getter
@Setter
public class CoderCustomerListInfo extends InfoBase {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
