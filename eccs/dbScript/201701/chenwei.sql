
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