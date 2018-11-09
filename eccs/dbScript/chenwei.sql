
----------------------------------------2016/05/12----------------------------------------
--project表增加customer_id字段存储项目立项时选择的委托单位(客户ID)
ALTER TABLE project ADD COLUMN customer_id VARCHAR (16) COMMENT '委托单位(客户ID)';

--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('002001010', '002001', '项目立项-打印', '/project/print.htm', '1');
INSERT INTO `res` VALUES ('002001011', '002001', '项目立项-查询业务范围', '/project/select.htm', '1');
INSERT INTO `res` VALUES ('002001012', '002001', '人员分配', '/project/checkPerson.htm', '1');
INSERT INTO `res` VALUES ('002001013', '002001', '保存人员分配', '/project/savePerson.htm', '1');

----------------------------------------以上为示例，请按上面格式编写SQL脚本----------------------------------------
-----------------------------------------2017/01/09--------------------------------------------------------------------
--step1表增加start_work_tim,end_work_time字段存储工期要求：开始时间和终止时间
ALTER TABLE step1 ADD COLUMN start_work_time VARCHAR (64) COMMENT '工期要求：开始时间';
ALTER TABLE step1 ADD COLUMN end_work_time VARCHAR (64) COMMENT '工期要求：终止时间';
--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('001004003', '001004', '修改个人资料-获取', '/t_reso/revise.htm', '2');
INSERT INTO `res` VALUES ('001004004', '001004', '修改个人密码-获取', '/user/revisepass.htm', '2');
INSERT INTO `res` VALUES ('001004005', '001004', '修改个人密码-验证', '/user/checkPassword.htm', '2');
INSERT INTO `res` VALUES ('001004006', '001004', '修改个人密码-提交', '/user/revisepassword.htm', '2');

-----------------------------------------2017/01/10--------------------------------------------------------------------
--step4表增加standard,other字段存储规范、软件和其他相关资料
ALTER TABLE step4 ADD COLUMN standard VARCHAR (256) COMMENT '规范、软件';
ALTER TABLE step4 ADD COLUMN other VARCHAR (256) COMMENT '其他相关资料';
--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('003003006', '003003', '实施计划-打印', '/step3/print.htm', '2');
INSERT INTO `res` VALUES ('003004006', '003004', '整理资料清单-打印', '/step4/print.htm', '2');
INSERT INTO `res` VALUES ('003005006', '003005', '勘察记录-打印', '/step5/print.htm', '2');
INSERT INTO `res` VALUES ('003006006', '003006', '底稿编制-打印', '/step6/print.htm', '2');
INSERT INTO `res` VALUES ('003007007', '003007', '校审-打印', '/step7/print.htm', '2');
INSERT INTO `res` VALUES ('003008007', '003008', '审核-打印', '/step8/print.htm', '2');
INSERT INTO `res` VALUES ('003009007', '003009', '审定-打印', '/step9/print.htm', '2');
INSERT INTO `res` VALUES ('003010008', '003010', '征求意见-打印', '/step10/print.htm', '2');
INSERT INTO `res` VALUES ('003011007', '003011', '编制成果文件-打印', '/step11/print.htm', '2');
INSERT INTO `res` VALUES ('003012006', '003012', '成果文件签订-打印', '/step12/print.htm', '2');
INSERT INTO `res` VALUES ('003013007', '003013', '回访记录-打印', '/step13/print.htm', '2');
INSERT INTO `res` VALUES ('003014006', '003014', '项目总结-打印', '/step14/print.htm', '2');
INSERT INTO `res` VALUES ('003015008', '003015', '资料归档-打印', '/step15/print.htm', '2');
--修改字段类型
ALTER TABLE step7  MODIFY COLUMN validate_date datetime;
ALTER TABLE step8  MODIFY COLUMN validate_date datetime;
ALTER TABLE step9  MODIFY COLUMN validate_date datetime;
-----------------------------------------2017/01/11--------------------------------------------------------------------
--权限配置表增加如下权限配置
update `res` set name='征求意见-编制信息保存',url='/step10/writeSave.htm' where id='003010004';
INSERT INTO `res` VALUES ('003010009', '003010', '征求意见-编制信息保存', '/step10/writeSave.htm', '2');
INSERT INTO `res` VALUES ('003010010', '003010', '征求意见-提交审定', '/step10/toValidate.htm', '2');
INSERT INTO `res` VALUES ('003010011', '003010', '征求意见-审定信息保存', '/step10/validateSave.htm', '2');
INSERT INTO `res` VALUES ('001004007', '001004', '项目立项-选择协作负责人', '/user/getUserByDeptIds.htm', '2');
--step10表增加validate_status字段存储当前操作
ALTER TABLE step10 ADD COLUMN validate_status int(11) DEFAULT '0' COMMENT '0：编制 1：审批';

--step5表删除单位和法人字段
alter table `step5` drop column audit_dept_name;
alter table `step5` drop column audit_user_name;
alter table `step5` drop column build_dept_name;
alter table `step5` drop column build_user_name;
alter table `step5` drop column project_dept_name;
alter table `step5` drop column project_user_name;
alter table `step5` drop column exp_date;
alter table `step5` drop column exp_content;
alter table `step5` drop column record_user_name;

--修改字段类型(主要是添加注释)
ALTER TABLE step5  MODIFY COLUMN confirm_time datetime DEFAULT NULL COMMENT '最近 一次勘察时间';
ALTER TABLE step5  ADD COLUMN history_content longtext DEFAULT NULL COMMENT '最近一次勘察记录';
--将projectAddress字段名改为project_address
alter table step5 change projectAddress project_address varchar(100);

--创建step5Item表单保存单位
CREATE TABLE `step5_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `step5_id` int(11) DEFAULT NULL,
  `company` varchar(64) DEFAULT NULL COMMENT '单位',
  `company_ren` varchar(64) DEFAULT NULL COMMENT '法人',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='勘察单位记录表';

-----------------------------------------2017/01/12--------------------------------------------------------------------
--创建step5Logo表单保存勘察日志
CREATE TABLE `step5_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `step5_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '勘察人',
  `logo_next` longtext COMMENT '勘察记录',
  `ctime` datetime DEFAULT NULL COMMENT '勘察时间',
  `status` int(11) DEFAULT NULL COMMENT '勘察状态 0 ：正常',
  `dept_id` varchar(30) DEFAULT NULL COMMENT '勘察部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='勘察记录表';

-----------------------------------------2017/01/21--------------------------------------------------------------------
--修改字段长度
alter table project modify column handle_dept_id varchar(256);
-----------------------------------------2017/01/23--------------------------------------------------------------------
--修改字段长度
ALTER TABLE step3  MODIFY COLUMN master_sign VARCHAR(256);

-----------------------------------------2017/02/07--------------------------------------------------------------------
--权限配置表增加如下权限配置
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('007005013', '007005', '信息登记-删除附件', '/t_reso/deletefile.htm', '2');
-----------------------------------------2017/02/15--------------------------------------------------------------------
--step3_worker表增加work_user_name字段存储处理人
ALTER TABLE step3_worker ADD COLUMN work_user_name varchar(256) COMMENT '人员姓名';
--step6表增加current_users字段存储处理人
ALTER TABLE step6 ADD COLUMN current_users varchar(256) COMMENT '处理人';
--修改字段类型
ALTER TABLE step3_worker  MODIFY COLUMN work_user_id varchar(256);
--step6表增加current_users字段存储处理人
ALTER TABLE step10 ADD COLUMN validate_user varchar(256) COMMENT '审批人';
-----------------------------------------2017/02/16--------------------------------------------------------------------
--step6表增加current_users字段存储处理人
ALTER TABLE step5 ADD COLUMN current_users varchar(256) COMMENT '第五步骤的处理用户（包含转交）';
--step3表增加basis_compilation字段编制依据
ALTER TABLE step3 ADD COLUMN basis_compilation varchar(1000) COMMENT '编制依据';
