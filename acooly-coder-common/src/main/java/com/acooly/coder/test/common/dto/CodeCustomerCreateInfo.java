/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-15
*/
package com.acooly.coder.test.common.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.acooly.coder.test.common.enums.IdcardTypeEnum;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.SimpleStatus;
import com.acooly.coder.test.common.enums.CustomerTypeEnum;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.common.enums.Gender;

/**
* acoolycoder测试 Info Dto
* For facade and openApi
*
* @author acooly
* @date 2021-04-15 17:29:17
*/
@Getter
@Setter
public class CodeCustomerCreateInfo extends InfoBase {
    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 32)
    @OpenApiField(desc = "username", constraint = "用户名", demo = "Acooly@123!", ordinal = 1)
    private String username;
    /**
     * 年龄
     */
    @OpenApiField(desc = "age", constraint = "年龄", demo = "2", ordinal = 2)
    private Integer age;
    /**
     * 生日
     */
    @NotNull
    @OpenApiField(desc = "birthday", constraint = "生日", demo = "2021-04-15", ordinal = 3)
    private Date birthday;
    /**
     * 性别
     */
    @NotNull
    @OpenApiField(desc = "gender", constraint = "性别", demo = "male", ordinal = 4)
    private Gender gender;
    /**
     * 生肖
     */
    @OpenApiField(desc = "animal", constraint = "生肖", demo = "Rat", ordinal = 5)
    private AnimalSign animal;
    /**
     * 姓名
     */
    @NotBlank
    @Size(max = 16)
    @OpenApiField(desc = "realName", constraint = "姓名", demo = "只允许输入纯中文", ordinal = 6)
    private String realName;
    /**
     * 证件类型
     */
    @NotNull
    @OpenApiField(desc = "idcardType", constraint = "证件类型", demo = "other", ordinal = 7)
    private IdcardTypeEnum idcardType;
    /**
     * 身份证号码
     */
    @NotBlank
    @Size(max = 48)
    @OpenApiField(desc = "idcardNo", constraint = "身份证号码", demo = "130928198905281793", ordinal = 8)
    private String idcardNo;
    /**
     * 手机号码
     */
    @Size(max = 11)
    @OpenApiField(desc = "mobileNo", constraint = "手机号码", demo = "13787655390", ordinal = 9)
    private String mobileNo;
    /**
     * 邮件
     */
    @Size(max = 64)
    @OpenApiField(desc = "mail", constraint = "邮件", demo = "demo@acooly.cn", ordinal = 10)
    private String mail;
    /**
     * 客户类型
     */
    @OpenApiField(desc = "customerType", constraint = "客户类型", demo = "sepc", ordinal = 11)
    private CustomerTypeEnum customerType;
    /**
     * 摘要
     */
    @Size(max = 128)
    @OpenApiField(desc = "subject", constraint = "摘要", demo = "TfRxBI7jWC", ordinal = 12)
    private String subject;
    /**
     * 详情
     */
    @OpenApiField(desc = "content", constraint = "详情", demo = "该值为大对象字段，可以输入较多的文本数据", ordinal = 13)
    private String content;
    /**
     * 完成度
     */
    @OpenApiField(desc = "doneRatio", constraint = "完成度", demo = "10.20", ordinal = 14)
    private Integer doneRatio;
    /**
     * 薪水
     */
    @OpenApiField(desc = "salary", constraint = "薪水", demo = "100.00", ordinal = 15)
    private Money salary;
    /**
     * 注册渠道
     */
    @OpenApiField(desc = "registryChannel", constraint = "注册渠道", demo = "WECHAT", ordinal = 16)
    private ChannelEnum registryChannel;
    /**
     * 推送广告
     */
    @OpenApiField(desc = "pushAdv", constraint = "推送广告", demo = "yes", ordinal = 17)
    private WhetherStatus pushAdv;
    /**
     * 数字类型
     */
    @OpenApiField(desc = "numStatus", constraint = "数字类型", demo = "1", ordinal = 18)
    private Integer numStatus;
    /**
     * 网址
     */
    @Size(max = 128)
    @OpenApiField(desc = "website", constraint = "网址", demo = "https://acooly.cn", ordinal = 19)
    private String website;
    /**
     * 照片
     */
    @Size(max = 128)
    @OpenApiField(desc = "photoPath", constraint = "照片", demo = "/aaa/bbb/ccc.png", ordinal = 20)
    private String photoPath;
    /**
     * 状态
     */
    @NotNull
    @OpenApiField(desc = "status", constraint = "状态", demo = "enable", ordinal = 21)
    private SimpleStatus status;
    /**
     * 备注
     */
    @Size(max = 255)
    @OpenApiField(desc = "comments", constraint = "备注", demo = "xdqygXxCyd", ordinal = 22)
    private String comments;
}
