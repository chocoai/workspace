
----------------------------------------2016/05/12----------------------------------------
--project表增加customer_id字段存储项目立项时选择的委托单位(客户ID)
ALTER TABLE project ADD COLUMN customer_id VARCHAR (16) COMMENT '委托单位(客户ID)';

--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('002001010', '002001', '项目立项-打印', '/project/print.htm', '1');
INSERT INTO `res` VALUES ('002001011', '002001', '项目立项-查询业务范围', '/project/select.htm', '1');
INSERT INTO `res` VALUES ('002001012', '002001', '人员分配', '/project/checkPerson.htm', '1');
INSERT INTO `res` VALUES ('002001013', '002001', '保存人员分配', '/project/savePerson.htm', '1');

----------------------------------------以上为示例，请按上面格式编写SQL脚本----------------------------------------

----------------------------------------2016/05/12----------------------------------------
ALTER TABLE `person_fenpei` MODIFY COLUMN `pstart`  varchar(32) NULL DEFAULT NULL COMMENT '开始时间' AFTER `user_id`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `pcontinue`  varchar(32) NULL DEFAULT NULL COMMENT '开始-持续时间' AFTER `pstart`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `realstart`  varchar(32) NULL DEFAULT NULL COMMENT '实际开始' AFTER `pcontinue`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `realcontinue`  varchar(32) NULL DEFAULT NULL COMMENT '实际持续' AFTER `realstart`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `complet`  varchar(16)  NULL DEFAULT NULL COMMENT '完成百分比' AFTER `realcontinue`;

----------------------------------------2016/05/16----------------------------------------
INSERT INTO `res` VALUES ('002010007', '002010', '项目信息-打印', '/information/print.htm', '2');
----------------------------------------2016/05/17----------------------------------------
INSERT INTO `res` VALUES ('002011004', '002011', '投标人员分配-打印', '/person/print.htm', '2');
INSERT INTO `res` VALUES ('002002007', '002002', '投标策划-打印', '/bid/print.htm', '2');
INSERT INTO `res` VALUES ('002005010', '002005', '合同评审-打印', '/review/print.htm', '2');
INSERT INTO `res` VALUES ('002004009', '002004', '合同登记-打印', '/contract/print.htm', '2');
INSERT INTO `res` VALUES ('002003010', '002003', '投标总结-打印', '/bid/printSummy.htm', '2');

----------------------------------------2017/01/10----------------------------------------
INSERT INTO `res` VALUES ('004004011', '004004', '部门管理-部门选择(多选)', '/dept/selectMultiDept.htm', '2');
----------------------------------------2017/01/11----------------------------------------
INSERT INTO `res` VALUES ('004001012', '004001', '根据部门ID选择用户', '/user/getUserByDeptIds.htm', '2');
INSERT INTO `res` VALUES ('004001013', '004001', '根据部门ID选择用户', '/user/getUserByDeptId.htm', '2');
----------------------------------------2017/01/13----------------------------------------
ALTER TABLE `step12` MODIFY COLUMN `step`  int(11) NULL DEFAULT 1 COMMENT '当前所处的步骤：1:、审核；2、审定' AFTER `next_worker_id`;


----------------------------------------2017/01/13----------------------------------------
--公共表：
--1、流程环节定义表：定义流程的各个环节(proce_step_def)
--2、项目进程状态表，记录当前项目所处的环节、状态及当前处理人(project_process_state)
--3、项目进程处理历史记录表，记录项目各个环节的处理情况(project_process_history)
drop table if exists proce_step_def;

/*==============================================================*/
/* Table: proce_step_def                                        */
/*==============================================================*/
create table proce_step_def
(
   uuid                 int not null comment '主键ID',
   step_name            varchar(32) comment '环节名称',
   step_code            varchar(8) comment '环节编码',
   step_desc            varchar(128) comment '环节描述',
   primary key (uuid)
);

alter table proce_step_def comment '流程环节定义表';

drop table if exists project_process_state;

/*==============================================================*/
/* Table: project_process_state                                 */
/*==============================================================*/
create table project_process_state
(
   uuid                 int not null comment '主键ID',
   project_id           int comment '项目ID',
   current_step         varchar(32) comment '当前所处环节',
   current_state        varchar(16) comment '当前状态',
   current_user_id      varchar(64) comment '当前处理人ID',
   primary key (uuid)
);

alter table project_process_state comment '项目进程状态表';

drop table if exists project_process_history;

/*==============================================================*/
/* Table: project_process_history                               */
/*==============================================================*/
CREATE TABLE `project_process_history` (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `operate_step` varchar(32) DEFAULT NULL COMMENT '处理环节',
  `operate_user` varchar(32) DEFAULT NULL COMMENT '处理人',
  `operate_time` varchar(32) DEFAULT NULL COMMENT '处理时间',
  `operate_type` varchar(16) DEFAULT NULL COMMENT '处理方式',
  `next_step` varchar(32) DEFAULT NULL COMMENT '下一环节',
  `next_handler` varchar(32) DEFAULT NULL COMMENT '下一环节处理人',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='项目进程处理历史记录表';

alter table project_process_history comment '项目进程处理历史记录表';


ALTER TABLE `project`
MODIFY COLUMN `handle_dept_id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协作部门' AFTER `sender_date`;
ALTER TABLE `project`
ADD COLUMN `process_type`  varchar(1) NULL COMMENT '流程类型(0表示先经营管理后项目实施，1表示经营管理和项目实施可同时独立进行)' AFTER `projectInfoId`;
ALTER TABLE `project`
ADD COLUMN `pm_operator_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目管理人员分配处理人' AFTER `process_type`;

INSERT INTO `res` VALUES ('002001025', '002001', '查询项目的流程处理历史记录', '/project/viewProcessHistory.htm', '2');

update eccs.res t set t.url ='/project/toNextStep.htm' where t.id = '002001006';


----------------------------------------2017/02/08----------------------------------------
ALTER TABLE `project_process_state`
ADD COLUMN `last_update_time`  varchar(32) NULL COMMENT '最后更新时间' AFTER `current_user_id`;
ALTER TABLE `project_process_state`
ADD COLUMN `type`  varchar(1) NULL COMMENT '类型，(0：经营管理，1：项目管理)' AFTER `current_user_id`;

----------------------------------------2017/02/15----------------------------------------
INSERT INTO `res` VALUES ('001001006', '001001', '待办事项-所有项目', '/backlog/getAll.htm', '2');

----------------------------------------2017/02/23----------------------------------------
ALTER TABLE `project`
ADD COLUMN `project_profile`  varchar(512) NULL COMMENT '工程概况' AFTER `pm_operator_id`;
INSERT INTO `res` VALUES ('001001007', '001001', '统计待办事项数目', '/backlog/countBacklog.htm', '2');
INSERT INTO `res` VALUES ('001002003', '001002', '查看所有我处理过的或者需要我处理的项目', '/project/getAllProject.htm', '2');











