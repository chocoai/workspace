----权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('002008010', '002008', '客户管理-删除附件', '/t_customer/deletefile.htm', '1');
INSERT INTO `res` VALUES ('006001006', '006001', '发票管理-查看', '/t_invoice/show.htm', '1');
INSERT INTO `res` VALUES ('006003005', '006003', '收款管理-收款明细', '/t_fire/adetail.htm', '1');
INSERT INTO `res` VALUES ('006003006', '006003', '收款管理-查看', '/t_fire/show.htm', '1');

INSERT INTO `res` VALUES ('007001007', '007001', '证照登记-查看', '/t_liregistration/show.htm', '1');
INSERT INTO `res` VALUES ('007001008', '007001', '证照登记-删除附件', '/t_liregistration/deletefile.htm', '1');
INSERT INTO `res` VALUES ('007001009', '007001', '证照操作-下载附件', '/t_file/xiazai.htm', '1');

INSERT INTO `res` VALUES ('007002006', '007002', '证照借用-处理', '/t_liborrow/manage.htm', '1');
INSERT INTO `res` VALUES ('007002007', '007002', '证照借用-查看', '/t_liborrow/show.htm', '1');
INSERT INTO `res` VALUES ('007002008', '007002', '证照借用-删除借用证照', '/t_liborrow/deletecls.htm', '1');
INSERT INTO `res` VALUES ('007002009', '007002', '证照借用-处理保存', '/t_liborrow/managesave.htm', '1');

-- 我的日程
CREATE TABLE `calendar` (
  `calId` varchar(16) NOT NULL COMMENT '主键ID',
  `calTitle` varchar(128) DEFAULT NULL COMMENT '日程标题',
  `calType` int(11) DEFAULT NULL COMMENT '日程类型',
  `calContent` varchar(512) DEFAULT NULL COMMENT '日程内容',
  `calStartTime` datetime DEFAULT NULL COMMENT '开始时间',
  `calEndTime` datetime DEFAULT NULL COMMENT '结束时间',
  `calAddress` varchar(64) DEFAULT NULL COMMENT '地点',
  `calPerson` varchar(64) DEFAULT NULL COMMENT '参与人物',
  `calUserId` varchar(16) DEFAULT NULL COMMENT '创建用户ID',
  `calDeptId` varchar(16) DEFAULT NULL COMMENT '创建者所属部门',
  `calCreateTime` datetime DEFAULT NULL COMMENT '日程创建时间',
  PRIMARY KEY (`calId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的日程';

ALTER TABLE t_seal_borrow ADD COLUMN gh_userid int COMMENT '归还人';
ALTER TABLE t_seal_borrow ADD COLUMN gh_date datetime COMMENT '实际归还时间';

ALTER TABLE t_li_borrow ADD COLUMN gh_userid int COMMENT '归还人';
ALTER TABLE t_li_borrow ADD COLUMN gh_date datetime COMMENT '实际归还时间';

ALTER TABLE project ADD COLUMN next_worker_id int;

