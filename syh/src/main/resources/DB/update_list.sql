
/**************************************
 * 版本日期 : 2017-08-22
 * 作者：陈伟
 * 
 **************************************/
ALTER TABLE `class_manager`
CHANGE COLUMN `AGE_START` `GROUP_MAN_AGE_START`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组内男子年龄上限' AFTER `UNIT_TEAMNUM`,
CHANGE COLUMN `AGE_END` `GROUP_MAN_AGE_END`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组内男子年龄下限' AFTER `GROUP_MAN_AGE_START`,
ADD COLUMN `IS_CROSS_GROUP`  int NULL COMMENT '是否可以跨组参赛(默认0：是 1：否)' AFTER `UNIT_TEAMNUM`,
ADD COLUMN `GROUP_WOMAN_AGE_START`  varchar(10) NULL COMMENT '组内女子年龄上限' AFTER `GROUP_MAN_AGE_END`,
ADD COLUMN `GROUP_WOMAN_AGE_END`  varchar(10) NULL COMMENT '组内女子年龄下限' AFTER `GROUP_WOMAN_AGE_START`;
/**************************************
 * 版本日期 : 2017-08-15
 * 作者：冯坤
 * 添加函数，根据最后一级的classId获取所有classId
 **************************************/
CREATE  FUNCTION `getParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId; 
WHILE rootId is not null  do   
    SET fid =(SELECT class_cid  FROM class_manager WHERE cid = rootId);   
    IF fid is not null THEN   
       SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return   str;   
END
/**************************************
 * 版本日期 : 2017-08-07
 * 作者：陈伟
 * 
 **************************************/

ALTER TABLE `class_manager`
ADD COLUMN `TEAM_SUM`  int NULL COMMENT '团体项目人数' AFTER `ORDER_NUM`,
ADD COLUMN `TEAM_MAN_SUM`  int NULL COMMENT '团体项目男运动员人数(至少)' AFTER `TEAM_SUM`,
ADD COLUMN `TEAM_WOMAN_SUM`  int NULL COMMENT '团体项目女运动员人数(至少)' AFTER `TEAM_MAN_SUM`,
ADD COLUMN `TEAM_MAN_AGE_START`  varchar(10) NULL COMMENT '团体项目男性年龄上限' AFTER `TEAM_WOMEN_SUM`,
ADD COLUMN `TEAM_MAN_AGE_END`  varchar(10) NULL COMMENT '团体项目男性年龄下限' AFTER `TEAM_MAN_AGE_START`,
ADD COLUMN `TEAM_WOMAN_AGE_START`  varchar(10) NULL COMMENT '团体项目女性年龄上限' AFTER `TEAM_MAN_AGE_END`,
ADD COLUMN `TEAM_WOMAN_AGE_END`  varchar(10) NULL COMMENT '团体项目女性年龄下限' AFTER `TEAM_WOMAN_AGE_START`;

ALTER TABLE `class_manager`
MODIFY COLUMN `BACKUP`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特殊要求' AFTER `TEAM_WOMAN_AGE_END`;

ALTER TABLE `sys_system_log`
DROP COLUMN `ERSION_NUM`,
ADD COLUMN `ERSION_NUM`  int NULL COMMENT '届数' AFTER `OPEARTION_TYPE`;

ALTER TABLE `sys_config`
MODIFY COLUMN `CID`  int(11) NOT NULL COMMENT '系统参数CID/届数' FIRST ;


ALTER TABLE `project_manager`
DROP COLUMN `VERSION_NUM`;

ALTER TABLE `class_manager`
ADD COLUMN `ERSION_NUM`  int NULL COMMENT '届数' AFTER `CREA_TIME`;


ALTER TABLE `unit_info`
ADD COLUMN `ERSION_NUM`  int NULL COMMENT '届数' AFTER `ORDER_NUM`;

ALTER TABLE `score_record`
ADD COLUMN `ERSION_NUM`  int NULL COMMENT '届数' AFTER `CREA_TIME`;

ALTER TABLE `athlete_base_info`
ADD COLUMN `ERSION_NUM`  int NULL COMMENT '届数' AFTER `ATHLETES_TYPE`;

/**************************************
 * 版本日期 : 2017-08-01
 * 作者：陈伟
 * 
 **************************************/
ALTER TABLE `athlete_base_info`
MODIFY COLUMN `ID_CARD`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号' AFTER `BIRTHDAY`;

ALTER TABLE `athlete_base_info`
MODIFY COLUMN `IS_EXAMINE`  int(11) NULL DEFAULT NULL COMMENT '是否参加反兴奋剂培训（0：是；1：否）' AFTER `ID_CARD`;

ALTER TABLE `athlete_base_info`
MODIFY COLUMN `ID_CARD`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号' AFTER `BIRTHDAY`;

/**************************************
 * 版本日期 : 2017-07-28
 * 作者：陈伟
 * class_manager、project_manager表中的添加字段
 **************************************/
ALTER TABLE `class_manager`
ADD COLUMN `CLASS_TYPE`  int NULL COMMENT '分类类型' AFTER `CLASS_CID`;

ALTER TABLE `project_manager`
ADD COLUMN `PROJECT_TYPE`  int NULL COMMENT '项目类型：关联项目分类的大项。如：足球、篮球' AFTER `CREA_REN`;

