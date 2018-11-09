
----------------------------------------2016/05/12----------------------------------------
--project表增加customer_id字段存储项目立项时选择的委托单位(客户ID)
ALTER TABLE project ADD COLUMN customer_id VARCHAR (16) COMMENT '委托单位(客户ID)';

--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('002001010', '002001', '项目立项-打印', '/project/print.htm', '1');
INSERT INTO `res` VALUES ('002001011', '002001', '项目立项-查询业务范围', '/project/select.htm', '1');
INSERT INTO `res` VALUES ('002001012', '002001', '人员分配', '/project/checkPerson.htm', '1');
INSERT INTO `res` VALUES ('002001013', '002001', '保存人员分配', '/project/savePerson.htm', '1');

----------------------------------------以上为示例，请按上面格式编写SQL脚本----------------------------------------

----------------------------------------2017/01/09----------------------------------------
--backlog表增加project_step字段，存储待办事项所处的步骤
ALTER TABLE backlog ADD COLUMN project_step INT(11) NOT NULL DEFAULT '0' COMMENT '当前的待办事项处于哪一步';

--权限配置表增加或修改
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003001011', '003001', '咨询任务书-打印', '/step1/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('001001005', '001001', '已办事项', '/backlog/getMyFinishedBacklog.htm', '2');
UPDATE eccs.res SET url='/t_seal/choose.htm' WHERE id='007004009';

--权限配置表增加
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003002006', '003002', '接收资料登记-打印', '/step2/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003003006', '003003', '实施计划-打印', '/step3/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003004006', '003004', '整理资料清单-打印', '/step4/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003005006', '003005', '勘察记录-打印', '/step5/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003006006', '003006', '底稿编制-打印', '/step6/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003007007', '003007', '校审-打印', '/step7/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003008007', '003008', '审核-打印', '/step8/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003009007', '003009', '审定-打印', '/step9/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003010008', '003010', '征求意见-打印', '/step10/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003011007', '003011', '编制成果文件-打印', '/step11/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003012006', '003012', '成果文件签订-打印', '/step12/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003013007', '003013', '回访记录-打印', '/step13/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003014006', '003014', '项目总结-打印', '/step14/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('003015008', '003015', '资料归档-打印', '/step15/print.htm', '2');

--bid_plan表修改handler字段长度
ALTER TABLE eccs.bid_plan modify COLUMN handler varchar(64);

----------------------------------------2017/01/11----------------------------------------
--权限配置表增加
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('002009002', '002009', '客户信息统计-打印', '/t_customer/squeryPrint.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('002008011', '002008', '客户管理-打印', '/t_customer/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('002001024', '002001', '任务分配-打印', '/project/printCheckPerson.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('006001009', '006001', '发票管理-打印', '/t_invoice/print.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('006001010', '006001', '发票管理-统计打印', '/t_invoice/reportPrint.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('006003009', '006003', '收款管理-统计打印', '/t_fire/reportPrint.htm', '2');
INSERT INTO `eccs`.`res` (`id`, `pid`, `name`, `url`, `status`) VALUES ('007004010', '007004', '印章借用-查看', '/t_seal/show.htm', '1');


--点击财务管理中项目请款报异常了，缺少字段contact_on
ALTER TABLE requisition ADD COLUMN contact_on VARCHAR (100) COMMENT '合同编号';

--增加表step12中的字段
ALTER TABLE step12 ADD COLUMN next_worker_id int (11) COMMENT '下一步处理人';
ALTER TABLE step12 ADD COLUMN step int (11) NOT NULL DEFAULT '1' COMMENT '当前所处的步骤：1:、审核；2、审定';

--修改权限表
UPDATE eccs.res SET url='/t_seal/manage.htm' WHERE id='007004006';




