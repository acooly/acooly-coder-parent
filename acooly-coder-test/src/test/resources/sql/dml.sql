CREATE TABLE `acooly_coder_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `birthday` date NOT NULL COMMENT '生日',
  `gender` tinyint(4) NOT NULL COMMENT '性别 {1:男,2:女,3:人妖}',
  `real_name` varchar(16) NOT NULL COMMENT '姓名',
  `idcard_type` varchar(18) NOT NULL COMMENT '证件类型 {cert:身份证,pass:护照,other:其他}',
  `idcard_no` varchar(48) NOT NULL COMMENT '身份证号码',
  `mobile_no` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `mail` varchar(64) DEFAULT NULL COMMENT '邮件',
  `subject` varchar(64) DEFAULT NULL COMMENT '摘要',
  `customer_type` varchar(16) DEFAULT NULL COMMENT '客户类型 {normal:普通,vip:重要,sepc:特别}',
  `fee` decimal(12,2) DEFAULT NULL COMMENT '手续费',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 {0:无效,1:有效}',
  `content` text COMMENT '测试Text类型',
  `salary` int(11) DEFAULT NULL COMMENT '薪水(元)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='acoolycoder测试';