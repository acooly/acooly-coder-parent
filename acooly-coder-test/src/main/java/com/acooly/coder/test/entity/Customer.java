/*
* acooly.cn Inc.
* Copyright (c) 2020 All Rights Reserved.
* create by acooly
* date:2020-06-07
*/
package com.acooly.coder.test.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.coder.test.enums.IdcardTypeEnum;
import java.util.Date;
import com.acooly.coder.test.enums.CustomerTypeEnum;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.enums.SimpleStatus;
import com.acooly.core.common.enums.AnimalSign;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.common.enums.ChannelEnum;
import com.acooly.core.common.enums.Gender;

/**
 * acoolycoder测试 Entity
 *
 * @author acooly
 * @date 2020-06-07 14:53:19
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
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
	@NotNull
    private Date birthday;

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
	@NotNull
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

    /**
     * 网址
     */
	@Size(max = 128)
    private String website;

}
