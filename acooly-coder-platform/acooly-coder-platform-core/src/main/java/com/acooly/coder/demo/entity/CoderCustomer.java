/*
* acooly.cn Inc.
* Copyright (c) 2023 All Rights Reserved.
* create by acooly
* date:2023-06-16
*/
package com.acooly.coder.demo.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.acooly.core.utils.ie.anno.ExportColumn;
import com.acooly.core.utils.ie.anno.ExportModel;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import com.acooly.core.common.domain.AbstractEntity;
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
 * 代码生成客户信息 Entity
 *
 * @author acooly
 * @date 2023-06-16 15:23:11
 */
@Entity
@Table(name = "acooly_coder_customer")
@Getter
@Setter
@ExportModel(name = "代码生成客户信息", border = true, headerShow = true)
public class CoderCustomer extends AbstractEntity {

    /**
     * 用户名
     */
	@NotBlank
	@Size(max = 32)
    @ExportColumn(header = "用户名", order = 1)
    private String username;

    /**
     * 生日
     */
    @ExportColumn(header = "生日", order = 2)
    private Date birthday;

    /**
     * 年龄
     */
    @ExportColumn(header = "年龄", order = 3)
    private Integer age;

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    @ExportColumn(header = "性别", order = 4)
    private Gender gender;

    /**
     * 生肖
     */
    @Enumerated(EnumType.STRING)
    @ExportColumn(header = "生肖", order = 5)
    private AnimalSign animal;

    /**
     * 姓名
     */
	@NotBlank
	@Size(max = 16)
    @ExportColumn(header = "姓名", order = 6)
    private String realName;

    /**
     * 证件类型
     */
    @Enumerated(EnumType.STRING)
	@NotNull
    @ExportColumn(header = "证件类型", order = 7)
    private IdcardTypeEnum idcardType;

    /**
     * 身份证号码
     * 请使用真实的18位身份证号码
     */
	@NotBlank
	@Size(max = 48)
    @ExportColumn(header = "身份证号码", order = 8)
    private String idcardNo;

    /**
     * 银行卡卡号
     * 请使用你常用的银行卡号，该卡号用于绑定验证身份和提现收益账户
     */
	@NotBlank
	@Size(max = 48)
    @ExportColumn(header = "银行卡卡号", order = 9)
    private String bankCardNo;

    /**
     * 手机号码
     * 请手机号码是自有使用，以确保后续所有业务通知您能收到。
     */
	@Size(max = 11)
    @ExportColumn(header = "手机号码", order = 10)
    private String mobileNo;

    /**
     * 邮件
     */
	@Size(max = 64)
    @ExportColumn(header = "邮件", order = 11)
    private String mail;

    /**
     * 客户类型
     */
    @Enumerated(EnumType.STRING)
    @ExportColumn(header = "客户类型", order = 12)
    private CustomerTypeEnum customerType;

    /**
     * 摘要
     */
	@Size(max = 128)
    @ExportColumn(header = "摘要", order = 13)
    private String subject;

    /**
     * 详情
     */
    @ExportColumn(header = "详情", order = 14)
    private String content;

    /**
     * 完成度
     * 任务完成度，根据不同完成度获得对应的特权。<li>1、50%以下：基本会员权限</li><li>1、50%以上：VIP会员权限</li>
     */
    @ExportColumn(header = "完成度", order = 15)
    private Integer doneRatio;

    /**
     * 付款率
     * 演示说明：付款率字段采用支持2位小数的百分数（15.55%）。注意点如下: <li>1、数据库字段类型采用BIGINT</li><li>2、实体类型采用Money</li><li>3、数据库保存的是万分位值（例如:1555表示15.55%）</li>
     */
    @ExportColumn(header = "付款率", order = 16)
    private Money payRate;

    /**
     * 薪水
     */
    @ExportColumn(header = "薪水", order = 17)
    private Money salary;

    /**
     * 注册渠道
     * alias属性演示：采用内置channel别名对应的枚举`ChannelEnum`生成下拉列表
     */
    @Enumerated(EnumType.STRING)
    @ExportColumn(header = "注册渠道", order = 18)
    private ChannelEnum registryChannel;

    /**
     * 推送广告
     */
    @Enumerated(EnumType.STRING)
    @ExportColumn(header = "推送广告", order = 19)
    private WhetherStatus pushAdv;

    /**
     * 数字类型
     */
    @ExportColumn(header = "数字类型", order = 20)
    private Integer numStatus;

    /**
     * 网址
     */
	@Size(max = 128)
    @ExportColumn(header = "网址", order = 21)
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
    @ExportColumn(header = "状态", order = 23)
    private SimpleStatus status;

    /**
     * 备注
     */
	@Size(max = 255)
    private String comments;

}
