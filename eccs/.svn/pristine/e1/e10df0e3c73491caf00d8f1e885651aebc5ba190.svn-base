USE `eccs`;

/*Table structure for table `bid_apply_assess` */

DROP TABLE IF EXISTS `bid_apply_assess`;

CREATE TABLE `bid_apply_assess` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL COMMENT '项目名称',
  `bid_pkg` varchar(64) DEFAULT NULL COMMENT '标包',
  `customer_id` int(11) DEFAULT NULL COMMENT '招标人',
  `consult_type` varchar(32) DEFAULT NULL COMMENT '咨询类型',
  `call_bid_file` varchar(128) DEFAULT NULL COMMENT '招标文件（公告）',
  `agent_company` varchar(64) DEFAULT NULL COMMENT '代理公司',
  `apply_date` varchar(64) DEFAULT NULL COMMENT '报名日期',
  `bid_open_date` varchar(19) DEFAULT NULL COMMENT '开标日期',
  `consult_fee` double DEFAULT NULL COMMENT '咨询费估算（万元）',
  `signup_city` varchar(32) DEFAULT NULL COMMENT '报名城市',
  `assess_result` int(11) DEFAULT NULL COMMENT '评估结果',
  `appraisalnotes` varchar(128) DEFAULT NULL COMMENT '不参与的评估说明',
  `complete_status` int(11) DEFAULT NULL COMMENT '报名完成情况',
  `get_file_date` varchar(19) DEFAULT NULL COMMENT '领取招标文件日期',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投标报名评估';

/*Table structure for table `bid_bond_assess` */

DROP TABLE IF EXISTS `bid_bond_assess`;

CREATE TABLE `bid_bond_assess` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `apply_no` int(11) DEFAULT NULL COMMENT '申请编号',
  `projectInfo_id` int(11) DEFAULT NULL,
  `bid_pkg` varchar(32) DEFAULT NULL COMMENT '标包',
  `remittances_name` varchar(32) DEFAULT NULL COMMENT '汇款户名',
  `remittances_account` varchar(32) DEFAULT NULL COMMENT '汇款账号',
  `deposit_bank` varchar(32) DEFAULT NULL COMMENT '开户银行',
  `bank_account` varchar(32) DEFAULT NULL COMMENT '行号',
  `remittance_amount` double DEFAULT NULL COMMENT '汇款金额',
  `deadline` varchar(19) DEFAULT NULL COMMENT '保证金递交截止时间',
  `customer_id` int(11) DEFAULT NULL COMMENT '招标人',
  `bidder_tel` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `bid_agent` varchar(32) DEFAULT NULL COMMENT '招标代理人',
  `agent_tel` varchar(16) DEFAULT NULL COMMENT '联系人电话',
  `special_requirements` varchar(64) DEFAULT NULL COMMENT '特殊要求',
  `rreturn_time` varchar(19) DEFAULT NULL COMMENT '退还保证金时间',
  `returm_special_instr` varchar(128) DEFAULT NULL COMMENT '退保证金特殊说明',
  `audit_opinion` varchar(128) DEFAULT NULL COMMENT '审核意见',
  `finance_handle_situation` int(11) DEFAULT NULL COMMENT '财务部处理情况',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投标保证金评估';

/*Table structure for table `bid_file_assess` */

DROP TABLE IF EXISTS `bid_file_assess`;

CREATE TABLE `bid_file_assess` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL COMMENT '招标人',
  `consult_type` varchar(32) DEFAULT NULL COMMENT '咨询类型',
  `bid_open_date` varchar(19) DEFAULT NULL COMMENT '开标日期',
  `responsible_person` varchar(32) DEFAULT NULL COMMENT '投标文件编制责任人',
  `firstdraft_time` varchar(19) DEFAULT NULL COMMENT '初稿提交时间',
  `complete_status` varchar(32) DEFAULT NULL COMMENT '编制完成情况',
  `submission_time` varchar(19) DEFAULT NULL COMMENT '实际提交时间',
  `audit_result` int(11) DEFAULT NULL COMMENT '审核情况',
  `audit_assess` int(11) DEFAULT NULL COMMENT '审核评定',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投标文件评估';

/*Table structure for table `bid_info` */

DROP TABLE IF EXISTS `bid_info`;

CREATE TABLE `bid_info` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL,
  `bid_open_date` varchar(19) DEFAULT NULL COMMENT '开标日期',
  `bid_open_person` varchar(32) DEFAULT NULL COMMENT '开标人员',
  `bid_number` int(11) DEFAULT NULL COMMENT '投标家数',
  `bid_winner` varchar(32) DEFAULT NULL COMMENT '中标单位',
  `is_success` int(11) DEFAULT NULL COMMENT '是否中标',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `bid_winner_price` double DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开标中标情况一览表';

/*Table structure for table `bid_info_quote` */

DROP TABLE IF EXISTS `bid_info_quote`;

CREATE TABLE `bid_info_quote` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL,
  `company` varchar(32) DEFAULT NULL COMMENT '参与单位',
  `quoted_price` double DEFAULT NULL COMMENT '报价（万元）',
  `budgetary_price` double DEFAULT NULL COMMENT '预算价（万元）',
  `ratio` double DEFAULT NULL COMMENT '比例',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='招标参与单位报价一览表';

/*Table structure for table `call_bid_assess` */

DROP TABLE IF EXISTS `call_bid_assess`;

CREATE TABLE `call_bid_assess` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL,
  `consult_type` varchar(32) DEFAULT NULL COMMENT '咨询类型',
  `project_affiliation` varchar(32) DEFAULT NULL COMMENT '项目归属',
  `other` varchar(128) DEFAULT NULL COMMENT '其他',
  `lose_score_case` varchar(256) DEFAULT NULL COMMENT '失分情况',
  `specific_requirements` varchar(256) DEFAULT NULL COMMENT '特殊要求',
  `assess_result` int(11) DEFAULT NULL COMMENT '评估结果',
  `appraisalnotes` varchar(128) DEFAULT NULL COMMENT '不参与的评估说明',
  `responsible_person` varchar(32) DEFAULT NULL COMMENT '投标文件编制责任人',
  `audit_date` varchar(19) DEFAULT NULL COMMENT '初稿提交（审核）日期',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='招标文件评估';

/*Table structure for table `project_summary` */

DROP TABLE IF EXISTS `project_summary`;

CREATE TABLE `project_summary` (
  `cid` int(11) NOT NULL COMMENT '主键ID',
  `projectInfo_id` int(11) DEFAULT NULL,
  `summary` varchar(128) DEFAULT NULL COMMENT '汇总小结',
  `perfect_measures` varchar(128) DEFAULT NULL COMMENT '完善措施',
  `archive_situation` varchar(128) DEFAULT NULL COMMENT '归档情况',
  `other` varchar(128) DEFAULT NULL COMMENT '其他',
  `create_time` varchar(128) DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目总结';

/*Table structure for table `project_transfer_record` */

DROP TABLE IF EXISTS `project_transfer_record`;

CREATE TABLE `project_transfer_record` (
  `cid` int(11) NOT NULL COMMENT '主键',
  `transfer_no` int(11) DEFAULT NULL COMMENT '移交单编号',
  `projectInfo_id` int(11) DEFAULT NULL,
  `consult_type` varchar(32) DEFAULT NULL COMMENT '咨询类型',
  `service_content` varchar(64) DEFAULT NULL COMMENT '主要服务内容及范围',
  `bid_price` double DEFAULT NULL COMMENT '中标价',
  `current_status` varchar(64) DEFAULT NULL COMMENT '目前状态',
  `notice_url` varchar(128) DEFAULT NULL COMMENT '中标公示网址',
  `call_bid_file` varchar(128) DEFAULT NULL COMMENT '招标文件',
  `bid_file` varchar(128) DEFAULT NULL COMMENT '投标文件',
  `owner_company` varchar(64) DEFAULT NULL COMMENT '业主单位',
  `owner_name` varchar(32) DEFAULT NULL COMMENT '业主姓名',
  `owner_position` varchar(32) DEFAULT NULL COMMENT '业主职务',
  `owner_tel` varchar(16) DEFAULT NULL COMMENT '业主电话',
  `owner_addr` varchar(128) DEFAULT NULL COMMENT '业主地址',
  `event` varchar(32) DEFAULT NULL COMMENT '事项',
  `event_time` varchar(19) DEFAULT NULL COMMENT '时间',
  `event_addr` varchar(19) DEFAULT NULL COMMENT '地点',
  `others` varchar(128) DEFAULT NULL COMMENT '其他要求',
  `transfer_person` varchar(64) DEFAULT NULL COMMENT '移交人',
  `receiver` varchar(32) DEFAULT NULL COMMENT '接收人',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` varchar(19) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `transfer_time` varchar(19) DEFAULT NULL COMMENT '移交时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目移交单';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/* 步骤表添加数据 */

INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('报名评估', '-401', '0', '报名评估','/bid/apply/edit.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('报名情况', '-4011', '0', '报名情况','/bid/apply/check.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('招标评估', '-402', '0', '招标评估','/bid/call/edit.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('保证金申请', '-403', '0', '保证金申请','/bid/bond/edit.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('保证金审核', '-4031', '0', '保证金审核','/bid/bond/check.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('投标文件提交', '-404', '0', '投标文件提交','/bid/file/edit.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('投标文件审核', '-4041', '0', '投标文件审核','/bid/file/check.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('开标中标情况', '-405', '0', '开标中标情况','/bid/info/edit.htm');
INSERT INTO `eccs`.`proce_step_def` (`step_name`, `step_code`, `business_type`, `step_desc`, `url`) VALUES ('项目移交', '-406', '0', '项目移交','/bid/transfer/edit.htm');

/* res表添加的url */
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008','002002','报名评估列表','/bid/apply/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008001','002002008','报名评估编辑','/bid/apply/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008002','002002008','报名评估审核','/bid/apply/check.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008003','002002008','报名评估查看','/bid/apply/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008004','002002008','报名评估删除','/bid/apply/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008005','002002008','报名评估保存','/bid/apply/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008006','002002008','报名评估审核结果保存','/bid/apply/save2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008007','002002008','报名评估提交','/bid/apply/toNextStep.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002008008','002002008','报名评估审核提交','/bid/apply/toNextStep2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009','002002','保证金评估列表','/bid/bond/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009001','002002009','保证金评估编辑','/bid/bond/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009002','002002009','保证金评估审核','/bid/bond/check.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009003','002002009','保证金评估查看','/bid/bond/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009004','002002009','保证金评估删除','/bid/bond/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009005','002002009','保证金评估保存','/bid/bond/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009006','002002009','保证金评估审核保存','/bid/bond/save2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009007','002002009','保证金评估提交','/bid/bond/toNextStep.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002009008','002002009','保证金评估审核提交','/bid/bond/toNextStep2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010','002002','招标评估列表','/bid/call/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010001','002002010','招标评估编辑','/bid/call/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010002','002002010','招标评估查看','/bid/call/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010003','002002010','招标评估删除','/bid/call/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010004','002002010','招标评估保存','/bid/call/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002010005','002002010','招标评估提交','/bid/call/toNextStep.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011','002002','投标文件评估列表','/bid/file/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011001','002002011','投标文件评估编辑','/bid/file/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011002','002002011','投标文件评估审核','/bid/file/check.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011003','002002011','投标文件评估查看','/bid/file/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011004','002002011','投标文件评估删除','/bid/file/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011005','002002011','投标文件评估保存','/bid/file/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011006','002002011','投标文件评估审核保存','/bid/file/save2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011007','002002011','投标文件评估提交','/bid/file/toNextStep.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002011008','002002011','投标文件评估审核提交','/bid/file/toNextStep2.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012','002002','开标中标情况列表','/bid/info/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012001','002002012','开标中标情况编辑','/bid/info/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012002','002002012','开标中标情况查看','/bid/info/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012003','002002012','开标中标情况删除','/bid/info/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012004','002002012','开标中标情况保存','/bid/info/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012005','002002012','开标中标情况提交','/bid/info/toNextStep.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002012006','002002012','招标参与单位删除','/bid/info/deleteInfoQuote.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002013','002002','项目总结列表','/bid/summary/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002013001','002002013','项目总结编辑','/bid/summary/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002013002','002002013','项目总结查看','/bid/summary/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002013003','002002013','项目总结删除','/bid/summary/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002013004','002002013','项目总结保存','/bid/summary/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014','002002','项目移交列表','/bid/transfer/list.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014001','002002014','项目移交编辑','/bid/transfer/edit.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014002','002002014','项目移交查看','/bid/transfer/show.htm','2');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014003','002002014','项目移交删除','/bid/transfer/delete.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014004','002002014','项目移交保存','/bid/transfer/save.htm','1');
insert into `res` (`id`, `pid`, `name`, `url`, `status`) values('002002014005','002002014','项目移交提交','/bid/transfer/toNextStep.htm','1');
/* 更新投标策划url */
UPDATE res SET url = '/bidPlanning.htm' WHERE id = 002002;
/* 更新投标总结url */
UPDATE proce_step_def SET url = '/bid/summary/edit.htm' WHERE step_code = '-3'