CREATE TABLE `acooly_coder_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) NOT NULL COMMENT '{title:’用户名’,type:’account’}',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `birthday` date NOT NULL COMMENT '生日',
  `gender` varchar(16) NOT NULL COMMENT '{title:''性别’,alias: ‘gender’}',
  `animal` varchar(16) DEFAULT NULL COMMENT '{title:’生肖’, alias: ‘animal’}',
  `real_name` varchar(16) NOT NULL COMMENT '{title:’姓名’,type:’chinese’}',
  `idcard_type` varchar(18) NOT NULL COMMENT '{title:’证件类型’, type:’option’,options:{cert:’身份证‘,pass:’护照‘,other:’其他‘}}',
  `idcard_no` varchar(48) NOT NULL COMMENT '{title:’身份证号码’,type:’idcard’}',
  `mobile_no` varchar(11) DEFAULT NULL COMMENT '{title:’手机号码’,type:’mobile’}',
  `mail` varchar(64) DEFAULT NULL COMMENT '{title:’邮件’,type:’email’}',
  `customer_type` varchar(16) DEFAULT NULL COMMENT '{title:’客户类型’, type:’option’,options:{normal:’普通‘,vip:’重要‘,sepc:’特别‘}}',
  `subject` varchar(128) DEFAULT NULL COMMENT '摘要',
  `content` text COMMENT '详情',
  `done_ratio` int(11) DEFAULT NULL COMMENT '{title:’完成度‘,type:’percent’}',
  `salary` int(11) DEFAULT NULL COMMENT '{title:’薪水’,type:’money’}',
  `registry_channel` varchar(16) DEFAULT NULL COMMENT '{title:’注册渠道’, alias: ‘channel’}',
  `push_adv` varchar(16) DEFAULT NULL COMMENT '{title:’推送广告’, alias:’whether’}',
  `num_status` tinyint(4) DEFAULT NULL COMMENT '数字类型{1:A,2:B,3:C类型}',
  `status` varchar(16) NOT NULL DEFAULT '1' COMMENT '{title:’状态’, alias:’simple’}',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `website` varchar(128) DEFAULT NULL COMMENT '{title:’网址’,type:’url’}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='acoolycoder测试';


// mysql
CREATE TABLE `dm_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` datetime NOT NULL COMMENT '生日',
  `gender` int(255) NOT NULL COMMENT '性别 {1:男,2:女,3:人妖}',
  `real_name` varchar(16) NOT NULL COMMENT '姓名',
  `idcard_type` varchar(16) NOT NULL COMMENT '证件类型 {cert:身份证,pass:护照,other:其他}',
  `idcard_no` varchar(32) NOT NULL COMMENT '身份证号码',
  `mobile_no` varchar(24) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(64) DEFAULT NULL COMMENT '邮件',
  `subject` varchar(64) DEFAULT NULL COMMENT '摘要',
  `customer_type` varchar(16) DEFAULT NULL COMMENT '客户类型 {normal:普通,vip:重要,sepc:特别}',
  `fee` decimal(5,2) DEFAULT NULL COMMENT '手续费',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 {0:无效,1:有效}',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;



// oracle

CREATE SEQUENCE "SEQ_DM_CUSTOMER"
INCREMENT BY 1
START WITH 1
 MAXVALUE 9999999999
 MINVALUE 1
CYCLE
 CACHE 20;

CREATE TABLE "DM_CUSTOMER" (
  "ID" NUMBER(10,0) NOT NULL,
  "USERNAME" VARCHAR2(16) DEFAULT 'zhangpu' NOT NULL,
  "AGE" NUMBER(4,0), "BIRTHDAY" DATE NOT NULL,
  "GENDER" NUMBER(4,0) NOT NULL,
  "REAL_NAME" VARCHAR2(16) NOT NULL,
  "IDCARD_TYPE" VARCHAR2(16) NOT NULL,
  "IDCARD_NO" VARCHAR2(32) NOT NULL,
  "MOBILE_NO" VARCHAR2(24),
  "MAIL" VARCHAR2(64),
  "SUBJECT" VARCHAR2(64),
  "CUSTOMER_TYPE" VARCHAR2(16),
  "FEE" NUMBER(10,2) DEFAULT 0  ,
  "STATUS" NUMBER(4,0) DEFAULT 1   NOT NULL,
  "CREATE_TIME" DATE NOT NULL,
  "UPDATE_TIME" DATE NOT NULL,
  "COMMENTS" VARCHAR2(128),
  CONSTRAINT PK_DM_CUSTOMER_ID PRIMARY KEY (ID)
);
COMMENT ON TABLE "DM_CUSTOMER" IS '客户信息表';
COMMENT ON COLUMN "DM_CUSTOMER"."ID" IS 'ID';
COMMENT ON COLUMN "DM_CUSTOMER"."USERNAME" IS '用户名';
COMMENT ON COLUMN "DM_CUSTOMER"."AGE" IS '年龄';
COMMENT ON COLUMN "DM_CUSTOMER"."BIRTHDAY" IS '生日';
COMMENT ON COLUMN "DM_CUSTOMER"."GENDER" IS '性别 {1:男,2:女,3:人妖}';
COMMENT ON COLUMN "DM_CUSTOMER"."REAL_NAME" IS '姓名';
COMMENT ON COLUMN "DM_CUSTOMER"."IDCARD_TYPE" IS '证件类型 {cert:身份证,pass:护照,other:其他}';
COMMENT ON COLUMN "DM_CUSTOMER"."IDCARD_NO" IS '证件号码';
COMMENT ON COLUMN "DM_CUSTOMER"."MOBILE_NO" IS '手机号码';
COMMENT ON COLUMN "DM_CUSTOMER"."MAIL" IS '邮件';
COMMENT ON COLUMN "DM_CUSTOMER"."SUBJECT" IS '摘要';
COMMENT ON COLUMN "DM_CUSTOMER"."CUSTOMER_TYPE" IS '客户类型 {normal:普通,vip:重要,sepc:特别}';
COMMENT ON COLUMN "DM_CUSTOMER"."FEE" IS '手续费';
COMMENT ON COLUMN "DM_CUSTOMER"."STATUS" IS '状态 {0:无效,1:有效}';
COMMENT ON COLUMN "DM_CUSTOMER"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "DM_CUSTOMER"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "DM_CUSTOMER"."COMMENTS" IS '备注';

CREATE UNIQUE INDEX UK_USERNAME ON DM_CUSTOMER (USERNAME ASC);



