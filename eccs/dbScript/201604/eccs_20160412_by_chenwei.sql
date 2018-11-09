----权限配置表增加如下权限配置
INSERT INTO `res` VALUES ('004001010', '004001', '分页选择多用户', '/user/selectUser4.htm', '2');
INSERT INTO `res` VALUES ('007009010', '007009', '培训管理-分页选择多用户', '/t_cult/selectUser1.htm', '2');
DELETE FROM `res`where id like '002007%';
DELETE FROM `role_res`WHERE res_id LIKE '002007%';
update res set pid='006' where id=006005;
update res set pid='006005' where id LIKE '0060050%';

