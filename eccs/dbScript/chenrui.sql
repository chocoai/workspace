
----------------------------------------2016/05/12----------------------------------------
--project表增加customer_id字段存储项目立项时选择的委托单位(客户ID)
ALTER TABLE project ADD COLUMN customer_id VARCHAR (16) COMMENT '委托单位(客户ID)';

--权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('002001010', '002001', '项目立项-打印', '/project/print.htm', '1');
INSERT INTO `res` VALUES ('002001011', '002001', '项目立项-查询业务范围', '/project/select.htm', '1');
INSERT INTO `res` VALUES ('002001012', '002001', '人员分配', '/project/checkPerson.htm', '1');
INSERT INTO `res` VALUES ('002001013', '002001', '保存人员分配', '/project/savePerson.htm', '1');

----------------------------------------以上为示例，请按上面格式编写SQL脚本----------------------------------------
--2016/5/12 打印
--2016/5/11打印 res表
INSERT INTO `res` VALUES ('003001011', '003001', '打印step1', '/step1/print.htm', '2');
INSERT INTO `res` VALUES ('003002006', '003002', '打印step2', '/step2/print.htm', '2');
INSERT INTO `res` VALUES ('003003006', '003003', '打印step3', '/step3/print.htm', '2');
INSERT INTO `res` VALUES ('003004006', '003004', '打印step4', '/step4/print.htm', '2');
INSERT INTO `res` VALUES ('003005006', '003005', '打印step5', '/step5/print.htm', '2');
INSERT INTO `res` VALUES ('003006006', '003006', '打印step6', '/step6/print.htm', '2');
INSERT INTO `res` VALUES ('003007007', '003007', '打印step7', '/step7/print.htm', '2');
INSERT INTO `res` VALUES ('003008007', '003008', '打印step8', '/step8/print.htm', '2');
INSERT INTO `res` VALUES ('003009007', '003009', '打印step9', '/step9/print.htm', '2');
INSERT INTO `res` VALUES ('003010008', '003010', '打印step10', '/step10/print.htm', '2');
INSERT INTO `res` VALUES ('003011007', '003011', '打印step11', '/step11/print.htm', '2');
INSERT INTO `res` VALUES ('003012006', '003012', '打印step12', '/step12/print.htm', '2');
INSERT INTO `res` VALUES ('003013007', '003013', '打印step13', '/step13/print.htm', '2');
INSERT INTO `res` VALUES ('003014006', '003014', '打印step14', '/step14/print.htm', '2');
INSERT INTO `res` VALUES ('003015008', '003015', '打印step15', '/step15/print.htm', '2');
INSERT INTO `res` VALUES ('002001024', '002001', '打印人员分配表', '/project/printCheckPerson.htm', '2');
-----字段短了，加长 2016/5/13
alter table step3 modify column  master_sign VARCHAR(200);
alter table annex modify column  path VARCHAR(256);
alter table step7_item modify column  content VARCHAR(200);
alter table step8_item modify column  content VARCHAR(200);
alter table step9_item modify column  content VARCHAR(200);
--增加查勘历史记录 2016/5/18 
ALTER TABLE step5 ADD COLUMN history_content VARCHAR (1000) COMMENT '查勘历史记录 ';
--删除step2和step4手写的文件
INSERT INTO `res` VALUES ('003002007', '003002', '删除文件', '/step2/deleteFile.htm', '2');
INSERT INTO `res` VALUES ('003004007', '003004', '删除文件', '/step4/deleteFile.htm', '2');

