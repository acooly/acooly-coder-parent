/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by acooly
* date:2019-05-25
*/
package com.acooly.coder.test.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import com.acooly.core.common.domain.AbstractEntity;
import java.math.BigDecimal;
import com.acooly.coder.test.enums.IdcardTypeEnum;
import java.util.Date;
import com.acooly.coder.test.enums.CustomerTypeEnum;

/**
 * acoolycoder测试 Entity
 *
 * @author acooly
 * Date: 2019-05-25 21:19:36
 */
@Entity
@Table(name = "acooly_coder_customer")
@Getter
@Setter
public class Customer extends AbstractEntity {

    /**
     * 用户名
     */
	@NotEmpty
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
	@NotNull
    private Integer gender;

    /**
     * 姓名
     */
	@NotEmpty
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
	@NotEmpty
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
     * 摘要
     */
	@Size(max = 64)
    private String subject;

    /**
     * 客户类型
     */
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum customerType;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 状态
     */
	@NotNull
    private Integer status;

    /**
     * 测试Text类型
     */
	@Size(max = 100000)
    private String content;

    /**
     * 薪水
     */
    private Integer salary;

    /**
     * 备注
     */
	@Size(max = 255)
    private String comments;

}
