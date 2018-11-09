ALTER TABLE `bid_plan` ADD COLUMN `handler` varchar(16) COMMENT '处理人';
ALTER TABLE `bid_plan` ADD COLUMN `operater_type` varchar(2) COMMENT '处理方式,0表示继续项目，1表示终止项目';
ALTER TABLE `contract_review` ADD COLUMN `operater_type` varchar(2) COMMENT '处理方式,0表示继续项目，1表示终止项目';
ALTER TABLE `contract_review` ADD COLUMN `contract_no` varchar(16) COMMENT '合同编号';
ALTER TABLE `calendar` MODIFY COLUMN `calDeptId` varchar(32) COMMENT '创建者所属部门' ;







