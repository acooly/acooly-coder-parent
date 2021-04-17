/*
* acooly.cn Inc.
* Copyright (c) 2021 All Rights Reserved.
* create by acooly
* date:2021-04-18
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
* @date 2021-04-18 00:31:50
*/
@Getter
@Setter
public class CodeCustomerUpdateInfo extends InfoBase {
    /**
     * ID
     */
    @NotNull
    @OpenApiField(desc = "id", constraint = "ID", demo = "4", ordinal = 1)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 32)
    @OpenApiField(desc = "username", constraint = "用户名", demo = "Acooly@123!", ordinal = 2)
    private String username;
    /**
     * 年龄
     */
    @OpenApiField(desc = "age", constraint = "年龄", demo = "6", ordinal = 3)
    private Integer age;
    /**
     * 生日
     */
    @NotNull
    @OpenApiField(desc = "birthday", constraint = "生日", demo = "2021-04-18", ordinal = 4)
    private Date birthday;
    /**
     * 性别
     */
    @NotNull
    @OpenApiField(desc = "gender", constraint = "性别", demo = "male", ordinal = 5)
    private Gender gender;
    /**
     * 生肖
     */
    @OpenApiField(desc = "animal", constraint = "生肖", demo = "Rat", ordinal = 6)
    private AnimalSign animal;
    /**
     * 姓名
     */
    @NotBlank
    @Size(max = 16)
    @OpenApiField(desc = "realName", constraint = "姓名", demo = "只允许输入纯中文", ordinal = 7)
    private String realName;
    /**
     * 证件类型
     */
    @NotNull
    @OpenApiField(desc = "idcardType", constraint = "证件类型", demo = "other", ordinal = 8)
    private IdcardTypeEnum idcardType;
    /**
     * 身份证号码
     */
    @NotBlank
    @Size(max = 48)
    @OpenApiField(desc = "idcardNo", constraint = "身份证号码", demo = "130928198905281793", ordinal = 9)
    private String idcardNo;
    /**
     * 手机号码
     */
    @Size(max = 11)
    @OpenApiField(desc = "mobileNo", constraint = "手机号码", demo = "13787655390", ordinal = 10)
    private String mobileNo;
    /**
     * 邮件
     */
    @Size(max = 64)
    @OpenApiField(desc = "mail", constraint = "邮件", demo = "demo@acooly.cn", ordinal = 11)
    private String mail;
    /**
     * 客户类型
     */
    @OpenApiField(desc = "customerType", constraint = "客户类型", demo = "sepc", ordinal = 12)
    private CustomerTypeEnum customerType;
    /**
     * 摘要
     */
    @Size(max = 128)
    @OpenApiField(desc = "subject", constraint = "摘要", demo = "EyGSvHnhGP", ordinal = 13)
    private String subject;
    /**
     * 详情
     */
    @OpenApiField(desc = "content", constraint = "详情", demo = "该值为大对象字段，可以输入较多的文本数据", ordinal = 14)
    private String content;
    /**
     * 完成度
     */
    @OpenApiField(desc = "doneRatio", constraint = "完成度", demo = "10.20", ordinal = 15)
    private Integer doneRatio;
    /**
     * 薪水
     */
    @OpenApiField(desc = "salary", constraint = "薪水", demo = "100.00", ordinal = 16)
    private Money salary;
    /**
     * 注册渠道
     */
    @OpenApiField(desc = "registryChannel", constraint = "注册渠道", demo = "WECHAT", ordinal = 17)
    private ChannelEnum registryChannel;
    /**
     * 推送广告
     */
    @OpenApiField(desc = "pushAdv", constraint = "推送广告", demo = "yes", ordinal = 18)
    private WhetherStatus pushAdv;
    /**
     * 数字类型
     */
    @OpenApiField(desc = "numStatus", constraint = "数字类型", demo = "1", ordinal = 19)
    private Integer numStatus;
    /**
     * 网址
     */
    @Size(max = 128)
    @OpenApiField(desc = "website", constraint = "网址", demo = "https://acooly.cn", ordinal = 20)
    private String website;
    /**
     * 照片
     */
    @Size(max = 128)
    @OpenApiField(desc = "photoPath", constraint = "照片", demo = "/aaa/bbb/ccc.png", ordinal = 21)
    private String photoPath;
    /**
     * 状态
     */
    @NotNull
    @OpenApiField(desc = "status", constraint = "状态", demo = "enable", ordinal = 22)
    private SimpleStatus status;
    /**
     * 备注
     */
    @Size(max = 255)
    @OpenApiField(desc = "comments", constraint = "备注", demo = "vrHSfigdYk", ordinal = 23)
    private String comments;
}
