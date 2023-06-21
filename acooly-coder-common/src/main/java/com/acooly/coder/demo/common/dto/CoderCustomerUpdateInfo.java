/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.common.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.core.common.facade.InfoBase;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.acooly.coder.demo.common.enums.IdcardTypeEnum;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.SimpleStatus;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.coder.demo.common.enums.CustomerTypeEnum;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.common.enums.Gender;

/**
* 代码生成客户信息 Info Dto
* For facade and openApi
*
* @author acooly
* @date 2023-06-16 15:23:11
*/
@Getter
@Setter
public class CoderCustomerUpdateInfo extends InfoBase {
    /**
     * ID
     */
    @NotNull
    @OpenApiField(desc = "id", constraint = "ID", demo = "6", ordinal = 1)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 32)
    @OpenApiField(desc = "username", constraint = "用户名", demo = "Acooly@123!", ordinal = 2)
    private String username;
    /**
     * 生日
     */
    @OpenApiField(desc = "birthday", constraint = "生日", demo = "2023-06-16", ordinal = 3)
    private Date birthday;
    /**
     * 年龄
     */
    @OpenApiField(desc = "age", constraint = "年龄", demo = "1", ordinal = 4)
    private Integer age;
    /**
     * 性别
     */
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
     * 银行卡卡号
     */
    @NotBlank
    @Size(max = 48)
    @OpenApiField(desc = "bankCardNo", constraint = "银行卡卡号", demo = "667688929983998701", ordinal = 10)
    private String bankCardNo;
    /**
     * 手机号码
     */
    @Size(max = 11)
    @OpenApiField(desc = "mobileNo", constraint = "手机号码", demo = "13787655390", ordinal = 11)
    private String mobileNo;
    /**
     * 邮件
     */
    @Size(max = 64)
    @OpenApiField(desc = "mail", constraint = "邮件", demo = "demo@acooly.cn", ordinal = 12)
    private String mail;
    /**
     * 客户类型
     */
    @OpenApiField(desc = "customerType", constraint = "客户类型", demo = "normal", ordinal = 13)
    private CustomerTypeEnum customerType;
    /**
     * 摘要
     */
    @Size(max = 128)
    @OpenApiField(desc = "subject", constraint = "摘要", demo = "P9hhdmnL390Crmqtd1iuqI8v9fOZE3L5", ordinal = 14)
    private String subject;
    /**
     * 详情
     */
    @OpenApiField(desc = "content", constraint = "详情", demo = "该值为大对象字段，可以输入较多的文本数据", ordinal = 15)
    private String content;
    /**
     * 完成度
     */
    @OpenApiField(desc = "doneRatio", constraint = "完成度", demo = "10", ordinal = 16)
    private Integer doneRatio;
    /**
     * 付款率
     */
    @OpenApiField(desc = "payRate", constraint = "付款率", demo = "90.50", ordinal = 17)
    private Money payRate;
    /**
     * 薪水
     */
    @OpenApiField(desc = "salary", constraint = "薪水", demo = "100.00", ordinal = 18)
    private Money salary;
    /**
     * 注册渠道
     */
    @OpenApiField(desc = "registryChannel", constraint = "注册渠道", demo = "WECHAT", ordinal = 19)
    private ChannelEnum registryChannel;
    /**
     * 推送广告
     */
    @OpenApiField(desc = "pushAdv", constraint = "推送广告", demo = "yes", ordinal = 20)
    private WhetherStatus pushAdv;
    /**
     * 数字类型
     */
    @OpenApiField(desc = "numStatus", constraint = "数字类型", demo = "1", ordinal = 21)
    private Integer numStatus;
    /**
     * 网址
     */
    @Size(max = 128)
    @OpenApiField(desc = "website", constraint = "网址", demo = "https://acooly.cn", ordinal = 22)
    private String website;
    /**
     * 照片
     */
    @Size(max = 128)
    @OpenApiField(desc = "photoPath", constraint = "照片", demo = "/aaa/bbb/ccc.png", ordinal = 23)
    private String photoPath;
    /**
     * 状态
     */
    @NotNull
    @OpenApiField(desc = "status", constraint = "状态", demo = "enable", ordinal = 24)
    private SimpleStatus status;
    /**
     * 备注
     */
    @Size(max = 255)
    @OpenApiField(desc = "comments", constraint = "备注", demo = "lMQJzXf0OEx49FOldJj70rTLXtvtVBlh", ordinal = 25)
    private String comments;
}
