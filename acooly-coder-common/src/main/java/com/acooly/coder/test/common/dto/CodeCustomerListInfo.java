/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-18
*/
package com.acooly.coder.test.common.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

/**
* acoolycoder测试 List DTO
* For facade and openApi
*
* @author acooly
* @date 2021-04-18 00:31:50
*/
@Getter
@Setter
public class CodeCustomerListInfo extends InfoBase {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    private Long id;

}
