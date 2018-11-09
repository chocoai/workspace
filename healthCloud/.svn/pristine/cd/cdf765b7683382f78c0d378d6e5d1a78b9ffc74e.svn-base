------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-08-04
-- description: 创建运动目标设置表、血压安全范围设置表

CREATE TABLE `step_setting` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `target_step` int(11) DEFAULT NULL COMMENT '运动目标',
  `update_time` varchar(19) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运动目标设置';

CREATE TABLE `blood_pressure_setting` (
  `cid` int(11) NOT NULL COMMENT '主键',
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `dbp` int(11) DEFAULT NULL COMMENT '舒张压',
  `sdp` int(11) DEFAULT NULL COMMENT '收缩压',
  `alarm_switch` int(11) DEFAULT NULL COMMENT '告警开关',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='血压安全范围设置';

INSERT INTO `sys_sequence` VALUES ('497da65c269542759951c90f2d2513da', 'blood_pressure_setting', '血压安全范围设置', '1001');
INSERT INTO `sys_sequence` VALUES ('c53a8828a85342bdb21046cea02fef8a', 'step_setting', '运动目标设置', '1001');

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-08-07
-- description: 创建预警消息定义表、预警消息表

CREATE TABLE `alarm_define` (
  `cid` varchar(32) NOT NULL COMMENT '主键ID',
  `alarm_type` varchar(16) DEFAULT NULL COMMENT '预警类型',
  `alarm_msg` varchar(256) DEFAULT NULL COMMENT '预警消息',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `uk_alarm_type` (`alarm_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警消息定义表';

CREATE TABLE `alarm_msg` (
  `cid` int(11) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `alarm_type` varchar(16) DEFAULT NULL COMMENT '预警类型',
  `alarm_content` varchar(256) DEFAULT NULL COMMENT '预警内容',
  `is_read` int(11) DEFAULT '0' COMMENT '是否已读',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警消息表';

INSERT INTO `sys_sequence` VALUES ('95b789fccb3f4b7d9e34046088b8a909', 'alarm_msg', '预警消息表', '1001');
------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-08-08
-- description: 修改血压安全范围设置表部分字段
ALTER TABLE `blood_pressure_setting`
CHANGE COLUMN `dbp` `dbp_max`  int(11) NULL DEFAULT NULL COMMENT '舒张压最大值' AFTER `member_id`,
CHANGE COLUMN `sdp` `sbp_min`  int(11) NULL DEFAULT NULL COMMENT '收缩压最小值' AFTER `dbp_max`,
ADD COLUMN `dbp_min`  int NULL COMMENT '舒张压最小值' AFTER `member_id`,
ADD COLUMN `sbp_max`  int NULL COMMENT '收缩压最大值' AFTER `sbp_min`;
------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-08-11
-- description: 第三方登录关联表
CREATE TABLE `third_login` (
  `cid` varchar(32) NOT NULL DEFAULT '' COMMENT '主键ID',
  `login_type` varchar(8) DEFAULT NULL COMMENT '登录类型',
  `uid` varchar(32) DEFAULT NULL COMMENT '第三方平台用户唯一标识ID',
  `user_id` varchar(16) DEFAULT NULL COMMENT '健康养老平台用户ID(关联登录账号表user_id)',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方登录关联表';
------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-08-11
-- description: 
ALTER TABLE `doctor`
CHANGE COLUMN `identity_card_no2` `identity_card`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码' AFTER `specialty`;
------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-09-11
-- description: 
ALTER TABLE `hd_sleep`
ADD COLUMN `sleep_quality`  int NULL COMMENT '睡眠质量' AFTER `deep_sleep_duration`;
INSERT INTO `alarm_define` VALUES ('9ac4a36b68414a8a80457fb451a3364c', 'fallDown', '会员{}跌倒了，当前位置经度：{}，纬度：{}', '2017-08-07 10:55:55', '0', null);

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-09-21
-- description:
ALTER TABLE `organization`
DROP COLUMN `type_dict_id`,
DROP COLUMN `nature_dict_id`,
DROP COLUMN `org_words`,
DROP COLUMN `icon`,
MODIFY COLUMN `longitude`  double NULL DEFAULT NULL COMMENT '经度' AFTER `area_id`,
MODIFY COLUMN `latitude`  double NULL DEFAULT NULL COMMENT '纬度' AFTER `longitude`,
ADD COLUMN `org_type`  varchar(32) NULL COMMENT '机构类型' AFTER `parent_org_id`,
ADD COLUMN `org_nature`  varchar(32) NULL COMMENT '机构性质' AFTER `org_type`,
ADD COLUMN `org_tag`  varchar(32) NULL COMMENT '机构标签（关键字）' AFTER `org_nature`,
ADD COLUMN `org_logo`  varchar(32) NULL COMMENT '机构logo' AFTER `org_tag`,
ADD COLUMN `title_img`  varchar(64) NULL COMMENT '机构标题图' AFTER `org_logo`,
ADD COLUMN `org_score`  double NULL COMMENT '机构评分' AFTER `postcode`,
ADD COLUMN `org_intro`  varchar(256) NULL COMMENT '机构简介' AFTER `org_score`,
ADD COLUMN `content`  text NULL COMMENT '机构详细介绍' AFTER `org_intro`,
ADD COLUMN `area_id`  varchar(32) NULL COMMENT '所属区域ID' AFTER `title_img`;

INSERT INTO `sys_sequence` VALUES ('28e4e8269c5b4b46ac5a0830d77540a5', 'yw_image', '图片表(存储系统中所有的图片信息)', '1002');
INSERT INTO `sys_sequence` VALUES ('b4c58d055c7d41aeb8f48ff468e4336d', 'service', '服务表', '1002');

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-09-21
-- description:
ALTER TABLE `sys_dictionary` ADD COLUMN `dict_icon`  varchar(128) NULL COMMENT '字典图标' AFTER `dict_desc`;

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-11-30
-- description:
ALTER TABLE `hd_sleep` ADD COLUMN `sleep_duration`  int NULL COMMENT '睡眠时长' AFTER `member_id`;
ALTER TABLE `member_device` ADD COLUMN `is_default`  int NULL DEFAULT NULL COMMENT '是否默认主设备(1是，0否)' AFTER `status`;
------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-01-15
-- description:会员扩展信息表增加以下属性
ALTER TABLE `member_info`
ADD COLUMN `native_place`  varchar(16) NULL COMMENT '籍贯' AFTER `dext2`,
ADD COLUMN `district`  varchar(64) NULL COMMENT '所在地区' AFTER `native_place`,
ADD COLUMN `politics_status`  varchar(16) NULL COMMENT '政治面貌' AFTER `district`,
ADD COLUMN `religion`  varchar(32) NULL COMMENT '宗教信仰' AFTER `politics_status`,
ADD COLUMN `professional_title`  varchar(32) NULL COMMENT '职称' AFTER `religion`,
ADD COLUMN `service_type`  varchar(16) NULL COMMENT '服务类型' AFTER `professional_title`;

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-01-19
-- description:新增服务供应商表

CREATE TABLE `service_provider` (
  `provider_id` int(11) NOT NULL DEFAULT '0' COMMENT '服务供应商ID',
  `org_id` int(11) DEFAULT NULL COMMENT '所属组织机构',
  `provider_name` varchar(128) DEFAULT NULL COMMENT '服务供应商名称',
  `provider_type` varchar(32) DEFAULT NULL COMMENT '服务供应商类型',
  `provider_nature` varchar(32) DEFAULT NULL COMMENT '服务供应商性质',
  `provider_tag` varchar(32) DEFAULT NULL COMMENT '服务供应商标签（关键字）',
  `provider_logo` varchar(128) DEFAULT NULL COMMENT '服务供应商logo',
  `title_img` varchar(128) DEFAULT NULL COMMENT '服务供应商标题图',
  `area_code` varchar(32) DEFAULT NULL COMMENT '所属区域编码',
  `area` varchar(32) DEFAULT NULL COMMENT '归属区域',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `linkman` varchar(16) DEFAULT NULL COMMENT '服务供应商负责人',
  `phone_number` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(16) DEFAULT NULL COMMENT '固话号码',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `fax` varchar(16) DEFAULT NULL COMMENT '传真',
  `address` varchar(128) DEFAULT NULL COMMENT '联系地址',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `business_license` varchar(128) DEFAULT NULL COMMENT '工商营业执照',
  `license_no` varchar(36) DEFAULT NULL COMMENT '执照号码',
  `provider_facility` text COMMENT '服务供应商设施',
  `provider_score` double DEFAULT NULL COMMENT '服务供应商评分',
  `provider_intro` varchar(2048) DEFAULT NULL COMMENT '服务供应商简介',
  `content` text COMMENT '服务供应商详细介绍',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(19) DEFAULT NULL COMMENT '修改时间',
  `order_num` int(11) DEFAULT NULL COMMENT '排序号',
  `status` int(11) DEFAULT NULL COMMENT '状态（0：标识无效 1：标识有效）',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`provider_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='服务供应商';

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2017-02-10
-- description:新增服务服务价格信息字段
ALTER TABLE `work_order`
ADD COLUMN `service_price`  varchar(32) NULL COMMENT '服务价格信息' AFTER `unit_price`;

------------------------------------------------------------------------------------------------------------
-- author: raopanfeng
-- date: 2018-03-20
-- description:新增权限编码字段
ALTER TABLE `sys_res`
ADD COLUMN `permission`  varchar(64) NULL COMMENT '权限字符串' AFTER `res_code`;

