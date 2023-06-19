/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo1.entity;


import com.acooly.coder.demo1.common.enums.CustomerTypeEnum;
import com.acooly.coder.demo1.common.enums.IdcardTypeEnum;
import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.common.enums.Gender;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.SimpleStatus;
import com.acooly.core.utils.enums.WhetherStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 代码生成客户信息 Entity
 *
 * @author acooly
 * @date 2023-06-16 15:53:17
 */
@Entity
@Table(name = "acooly_coder_customer")
@Getter
@Setter
public class Customer extends AbstractEntity {

    /**
     * 用户名
     */
	@NotBlank
	@Size(max = 32)
    private String username;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * 生肖
     */
    @Enumerated(EnumType.STRING)
    private AnimalSign animal;

    /**
     * 姓名
     */
	@NotBlank
	@Size(max = 16)
    private String realName;

    /**
     * 证件类型
     */
    @Enumerated(EnumType.STRING)
	@NotNull
    private IdcardTypeEnum idcardType;

    /**
     * 身份证号码
     */
	@NotBlank
	@Size(max = 48)
    private String idcardNo;

    /**
     * 银行卡卡号
     */
	@NotBlank
	@Size(max = 48)
    private String bankCardNo;

    /**
     * 手机号码
     */
	@Size(max = 11)
    private String mobileNo;

    /**
     * 邮件
     */
	@Size(max = 64)
    private String mail;

    /**
     * 客户类型
     */
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum customerType;

    /**
     * 摘要
     */
	@Size(max = 128)
    private String subject;

    /**
     * 详情
     */
	@Size(max = 999999999)
    private String content;

    /**
     * 完成度
     */
    private Integer doneRatio;

    /**
     * 付款率
     */
    private Long payRate;

    /**
     * 薪水
     */
    private Money salary;

    /**
     * 注册渠道
     */
    @Enumerated(EnumType.STRING)
    private ChannelEnum registryChannel;

    /**
     * 推送广告
     */
    @Enumerated(EnumType.STRING)
    private WhetherStatus pushAdv;

    /**
     * 数字类型
     */
    private Integer numStatus;

    /**
     * 网址
     */
	@Size(max = 128)
    private String website;

    /**
     * 照片
     */
	@Size(max = 128)
    private String photoPath;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
	@NotNull
    private SimpleStatus status;

    /**
     * 备注
     */
	@Size(max = 255)
    private String comments;

}
