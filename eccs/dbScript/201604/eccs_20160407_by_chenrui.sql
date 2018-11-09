/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : eccs

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2016-04-07 10:05:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL default '0',
  `project_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  `ctime` datetime default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
--res表 共享
INSERT INTO `res` VALUES ('003016', '003', '共享', '/project/listShare.htm', '1');
INSERT INTO `res` VALUES ('003016001', '003016', '共享-保存', '/project/saveShare.htm', '1');
INSERT INTO `res` VALUES ('003016002', '003016', '共享-新增', '/project/newShare.htm', '1');
INSERT INTO `res` VALUES ('003016003', '003016', '共享-新增', '/project/deleteShare.htm', '1');
INSERT INTO `res` VALUES ('004001009', '004001', '共享-选择用户', '/user/selectUser3.htm', '1');
ALTER TABLE `share` ADD name  VARCHAR(128)
ALTER TABLE `share` ADD user_id1  int