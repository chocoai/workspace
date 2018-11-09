/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : eccs

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2016-03-25 11:31:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person_fenpei`
-- ----------------------------
DROP TABLE IF EXISTS `person_fenpei`;
CREATE TABLE `person_fenpei` (
  `id` int(11) NOT NULL auto_increment,
  `project_id` int(11) default NULL,
  `pro_id` int(11) default NULL,
  `user_id` varchar(255) default NULL,
  `pstart` int(11) default NULL,
  `pcontinue` int(11) default NULL,
  `realstart` int(11) default NULL,
  `realcontinue` int(11) default NULL,
  `complet` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_fenpei
-- ----------------------------
INSERT INTO `res` VALUES ('002011', '002', '投标人员分配', '/person/list.htm', '1');
INSERT INTO `res` VALUES ('002011001', '002011', '投标人员分配-编辑', '/person/edit.htm', '1');
INSERT INTO `res` VALUES ('002011002', '002011', '投标人员分配-保存', '/person/save.htm', '1');
INSERT INTO `res` VALUES ('002011003', '002011', '投标人员分配-查看', '/person/show.htm', '1');
INSERT INTO `res` VALUES ('004001008', '004001', '选择用户', '/user/selectUser2.htm', '2');
--加的选择用户是可以选择所有的
