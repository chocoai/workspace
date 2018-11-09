ALTER TABLE `project_info`
ADD COLUMN `proNo`  varchar(100) NULL COMMENT '项目编号' AFTER `id`;
ADD COLUMN `receive_type`  int(11) NULL COMMENT '承接类型0直接承接1招投标' AFTER `ctime`,
ADD COLUMN `service_type_id`  int(11) NULL COMMENT '服务类型' AFTER `receive_type`,
ADD COLUMN `agentcompany`  varchar(64) NULL COMMENT '代理公司' AFTER `service_type_id`,
ADD COLUMN `signcity`  varchar(64) NULL COMMENT '报名城市' AFTER `agentcompany`;
ADD COLUMN `buildmoney`  int(11) NULL COMMENT '建设投资' AFTER `signcity`,
ADD COLUMN `project_profile`  varchar(512) NULL COMMENT '工程概况' AFTER `buildmoney`;
ADD COLUMN `next_operator_id`  varchar(16) NULL COMMENT '下一环节处理人' AFTER `project_profile`;

ALTER TABLE `requisition`
ADD COLUMN `next_operator_id`  varchar(64) NULL COMMENT '下一环节处理人' AFTER `contact_on`;


ALTER TABLE `project_process_history`
ADD COLUMN `proinfo_id`  int(11) NULL COMMENT '项目信息ID' AFTER `project_id`;

ALTER TABLE `project_process_state`
ADD COLUMN `proinfo_id`  int(11) NULL COMMENT '项目信息ID' AFTER `project_id`;

ALTER TABLE `proce_step_def`
ADD COLUMN `sort`  int(11) NULL COMMENT '排序' AFTER `url`;
INSERT INTO proce_step_def (step_name,step_code,business_type,step_desc,url,sort) VALUE ('请款跟踪',17,1,'请款跟踪','/requ/edit.htm',13)
UPDATE proce_step_def SET sort =0 where  step_code='0';
UPDATE proce_step_def SET sort =1 where  step_code='1';
UPDATE proce_step_def SET sort =2 where  step_code='2';
UPDATE proce_step_def SET sort =3 where  step_code='3';
UPDATE proce_step_def SET sort =4 where  step_code='4';
UPDATE proce_step_def SET sort =5 where  step_code='5';
UPDATE proce_step_def SET sort =6 where  step_code='6';
UPDATE proce_step_def SET sort =7 where  step_code='7';
UPDATE proce_step_def SET sort =8 where  step_code='8';
UPDATE proce_step_def SET sort =9 where  step_code='9';
UPDATE proce_step_def SET sort =10 where  step_code='10';
UPDATE proce_step_def SET sort =11 where  step_code='11';
UPDATE proce_step_def SET sort =12 where  step_code='12';
UPDATE proce_step_def SET sort =14 where  step_code='13';
UPDATE proce_step_def SET sort =15 where  step_code='14';
UPDATE proce_step_def SET sort =16 where  step_code='15';
UPDATE proce_step_def SET sort =17 where  step_code='16';
UPDATE proce_step_def SET sort =13 where  step_code='17';
UPDATE proce_step_def SET url ='/project/new.htm' where  step_code='-5';
ALTER TABLE `step1`
ADD COLUMN `step_type`  varchar(64) NULL COMMENT '项目类型' AFTER `end_work_time`;

--------权限---------
INSERT INTO res (id,pid,name,url,status) VALUES ('002010008','002010','项目信息-提交','/information/nextStep.htm',1);
INSERT INTO res (id,pid,name,url,status) VALUES ('006005007','006005','项目请款-提交下一步','/requ/nextStep.htm',1);

--------紧急与否常量修改---------
UPDATE urgent_type  SET name = '是' where id = 1;
UPDATE urgent_type  SET name = '否' where id = 3;

------------------------------------------------------------------------------------------------------------
-- author: zjd
-- date: 2017-10-11
-- description:

ALTER TABLE `eccs`.`bid_apply_assess`     CHANGE `bid_open_date` `bid_open_date` VARCHAR(19) NULL  COMMENT '开标日期',     CHANGE `consult_fee` `consult_fee` VARCHAR(100) NULL  COMMENT '咨询费估算（万元）';
ALTER TABLE `eccs`.`project_info`     CHANGE `zixungusuan` `zixungusuan` VARCHAR(101) NULL ,     CHANGE `signcity` `signcity` VARCHAR(64) NULL  COMMENT '报名城市',     CHANGE `buildmoney` `buildmoney` VARCHAR(100) NULL  COMMENT '建设投资';

-- author: zjd
-- date: 2017-11-21
-- description:
UPDATE service_type SET name = '土地咨询' WHERE id = '6' 