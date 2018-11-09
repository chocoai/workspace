/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : eccs

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2016-03-23 15:39:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_info`
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `id` int(11) NOT NULL auto_increment,
  `proName` varchar(256) default NULL,
  `bidmen` varchar(64) default NULL,
  `bidfile` varchar(256) default NULL,
  `bidway` varchar(16) default NULL,
  `zixungusuan` decimal(10,0) default NULL,
  `bidmargin` decimal(10,0) default NULL,
  `bidreturn` varchar(128) default NULL,
  `applytime` varchar(32) default NULL,
  `opentime` datetime default NULL,
  `internaltime` datetime default NULL,
  `collectionmen` varchar(255) default NULL,
  `bidapplymen` varchar(255) default NULL,
  `compilemen` varchar(255) default NULL,
  `auditmen` varchar(255) default NULL,
  `agentmen` varchar(255) default NULL,
  `bidresult` varchar(255) default NULL,
  `winnotice` varchar(258) default NULL,
  `transfer` varchar(255) default NULL,
  `qsj` datetime default NULL,
  `zsj` datetime default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES ('1', '大冶市政府投资工程咨询服务协议供应商', '大冶市财政局', 'http://www.dyggzyjyzx.com/dyweb/InfoDetail/?InfoID=dd3854e5-e844-4b86-9425-4baadda95e2d&CategoryNum=002001002http://www.dyggzyjyzx.com/dyweb/InfoDetail/?InfoID=dd3854e5-e844-4b86-9425-4baadda95e2d&CategoryNum=002001002', '公开招标', '0', '0', '', '2016年03月23日起至2016年03月24日', '2016-03-23 13:43:43', null, '毛迎霞', null, null, null, '', '', '', '', '2016-03-23 00:00:00', '2016-03-24 00:00:00', '1');

--上面是表，下面是res的

INSERT INTO `res` VALUES ('002010', '002', '项目信息', '/information/list.htm', '1');
INSERT INTO `res` VALUES ('002010001', '002010', '项目信息-新增', '/information/new.htm', '1');
INSERT INTO `res` VALUES ('002010002', '002010', '项目信息-编辑', '/information/edit.htm', '1');
INSERT INTO `res` VALUES ('002010003', '002010', '项目信息-保存', '/information/save.htm', '1');
INSERT INTO `res` VALUES ('002010004', '002010', '项目信息-查看', '/information/show.htm', '1');
INSERT INTO `res` VALUES ('002010005', '002010', '项目信息-删除', '/information/delete.htm', '1');