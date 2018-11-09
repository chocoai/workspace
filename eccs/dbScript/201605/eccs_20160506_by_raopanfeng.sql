ALTER TABLE `person_fenpei` MODIFY COLUMN `pstart`  varchar(8) NULL DEFAULT NULL COMMENT '开始时间' AFTER `user_id`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `pcontinue`  varchar(8) NULL DEFAULT NULL COMMENT '开始-持续时间' AFTER `pstart`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `realstart`  varchar(8) NULL DEFAULT NULL COMMENT '实际开始' AFTER `pcontinue`;
ALTER TABLE `person_fenpei` MODIFY COLUMN `realcontinue`  varchar(8) NULL DEFAULT NULL COMMENT '实际持续' AFTER `realstart`;







