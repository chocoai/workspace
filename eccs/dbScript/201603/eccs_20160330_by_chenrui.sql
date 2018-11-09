/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : eccs

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2016-03-30 13:17:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `performance_user`
-- ----------------------------
DROP TABLE IF EXISTS `performance_user`;
CREATE TABLE `performance_user` (
  `id` int(11) NOT NULL default '0',
  `performan_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  `fenpeiproportion` varchar(255) default NULL,
  `hedingproportion` varchar(255) default NULL,
  `sum` decimal(10,0) default NULL,
  `archive` varchar(255) default NULL,
  `ctime` decimal(10,0) default NULL,
  `daozhang` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of performance_user
-- ----------------------------
/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : eccs

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2016-03-30 13:17:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `performance`
-- ----------------------------
DROP TABLE IF EXISTS `performance`;
CREATE TABLE `performance` (
  `id` int(11) NOT NULL default '0',
  `wenhao` varchar(255) default NULL,
  `project_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `ctime` datetime default NULL,
  `signmen` varchar(255) default NULL,
  `job` varchar(11) default NULL,
  `contractno` varchar(255) default NULL,
  `charge` decimal(10,0) default NULL,
  `daozhang` varchar(255) default NULL,
  `prove` varchar(255) default NULL,
  `bbonus` decimal(10,0) default NULL,
  `coefficient` decimal(10,0) default NULL,
  `abonus` decimal(10,0) default NULL,
  `remark` varchar(512) default NULL,
  `compilemen` varchar(255) default NULL,
  `auditmen` varchar(255) default NULL,
  `approval` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of performance
-- ----------------------------
--project表加一个字段   
performance_id   int 