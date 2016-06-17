/*
Navicat MySQL Data Transfer

Source Server         : linhp
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : security3

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2016-06-17 10:53:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sysresource
-- ----------------------------
DROP TABLE IF EXISTS `sysresource`;
CREATE TABLE `sysresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ICONCLS` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `TARGET` varchar(100) DEFAULT NULL,
  `UPDATEDATETIME` datetime DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `SYSRESOURCE_ID` int(11) DEFAULT NULL,
  `SYRESOURCETYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pxb5fx7gc6b0ft3gan3kx42ba` (`SYSRESOURCE_ID`),
  KEY `FK_9ljqisu2a2xn0k7etjy8eh6ux` (`SYRESOURCETYPE_ID`),
  CONSTRAINT `FK_9ljqisu2a2xn0k7etjy8eh6ux` FOREIGN KEY (`SYRESOURCETYPE_ID`) REFERENCES `sysresourcetype` (`id`),
  CONSTRAINT `FK_pxb5fx7gc6b0ft3gan3kx42ba` FOREIGN KEY (`SYSRESOURCE_ID`) REFERENCES `sysresource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysresource
-- ----------------------------
INSERT INTO `sysresource` VALUES ('1', '2016-01-25 16:20:18', '系统管理描述', 'icon-shield_silver', '系统管理', null, null, '2016-01-25 16:20:18', '', null, '1');
INSERT INTO `sysresource` VALUES ('2', '2016-01-25 16:20:18', '用户管理描述', 'icon-user_suit', '用户管理', '98', null, '2016-02-03 16:29:51', '/user/userAction!list.action', '1', '1');
INSERT INTO `sysresource` VALUES ('3', '2016-01-25 16:20:18', '资源管理描述', 'icon-wrench_orange', '资源管理', '99', null, '2016-02-04 14:37:59', '/resource/resourceAction!list.action', '1', '1');
INSERT INTO `sysresource` VALUES ('4', '2016-01-25 16:20:18', '角色管理描述', 'icon-tux', '角色管理', null, null, '2016-01-25 16:20:18', '/role/roleAction!list.action', '1', '1');
INSERT INTO `sysresource` VALUES ('5', '2016-01-25 16:20:18', '系统报表描述', 'icon-folder_table', '系统报表', null, null, '2016-01-25 16:20:18', '', null, '1');
INSERT INTO `sysresource` VALUES ('6', '2016-01-25 16:20:18', '用户角色分布描述', 'icon-user_comment', '用户角色分布', null, null, '2016-01-25 16:20:18', '/base/syresource!treeGrid', '5', '1');
INSERT INTO `sysresource` VALUES ('7', '2016-01-25 16:20:18', '注册时间分布描述', 'icon-clock_red', '注册时间分布', null, null, '2016-01-25 16:20:18', '/base/syresource!treeGrid', '5', '1');
INSERT INTO `sysresource` VALUES ('8', '2016-02-03 10:27:14', '添加用户描述', null, '添加用户', '101', null, '2016-02-04 14:36:48', '/user/userAction!save.action', '2', '2');
INSERT INTO `sysresource` VALUES ('9', '2016-02-03 12:04:07', '删除用户描述', null, '删除资源', '100', null, '2016-05-19 10:23:56', '/user/userAction!delete.action', '2', '2');
INSERT INTO `sysresource` VALUES ('10', '2016-02-03 12:05:45', '修改用户描述', null, '修改用户', '100', null, '2016-02-03 12:05:45', '/user/userAction!update.action', '2', '2');
INSERT INTO `sysresource` VALUES ('11', '2016-02-03 15:01:56', null, 'icon-hourglass_add', '其他', '100', null, '2016-02-03 15:01:56', '', null, '1');
INSERT INTO `sysresource` VALUES ('12', '2016-02-03 16:50:58', null, 'icon-medal_gold_1', 'javamelody项目监控', '100', null, '2016-02-03 16:50:58', '/monitoring', '11', '1');
INSERT INTO `sysresource` VALUES ('13', '2016-02-03 17:37:05', null, 'icon-medal_bronze_2', 'action监控', '102', null, '2016-02-03 17:37:20', '/config-browser/showConstants.action', '11', '1');
INSERT INTO `sysresource` VALUES ('14', '2016-02-03 17:38:40', null, 'icon-medal_bronze_3', '数据源监控', '100', null, '2016-02-03 17:38:40', '/druid', '11', '1');
INSERT INTO `sysresource` VALUES ('15', '2016-02-04 14:36:04', null, null, '查看用户', '94', null, '2016-02-04 14:36:04', '/user/userAction!getList.action', '2', '2');
INSERT INTO `sysresource` VALUES ('16', '2016-02-04 14:38:23', null, null, '查看资源', '100', null, '2016-02-04 14:38:23', '/resource/resourceAction!getResourceTree.action', '3', '2');
INSERT INTO `sysresource` VALUES ('17', '2016-02-04 14:55:17', '添加资源描述', null, '添加资源', '99', null, '2016-02-04 14:55:42', '/resource/resourceAction!save.action', '3', '2');
INSERT INTO `sysresource` VALUES ('18', '2016-02-04 15:02:01', '授权', null, '角色授权', '100', null, '2016-02-04 15:02:01', '/role/roleAction!doGrantPrivilege.action', '4', '2');
INSERT INTO `sysresource` VALUES ('19', '2016-02-04 15:02:30', null, null, '查看角色', '99', null, '2016-02-04 15:02:30', '/role/roleAction!getList.action', '4', '2');
INSERT INTO `sysresource` VALUES ('20', '2016-02-04 16:51:52', null, null, '查看单个用户', '100', null, '2016-02-04 16:51:52', '/user/userAction!getById.action', '2', '2');
INSERT INTO `sysresource` VALUES ('21', '2016-06-13 15:23:48', '首页权限', null, '首页权限', '99', null, '2016-06-15 09:23:43', '/resource/resourceAction!main.action', '11', '2');

-- ----------------------------
-- Table structure for sysresourcetype
-- ----------------------------
DROP TABLE IF EXISTS `sysresourcetype`;
CREATE TABLE `sysresourcetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `UPDATEDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysresourcetype
-- ----------------------------
INSERT INTO `sysresourcetype` VALUES ('1', '2016-01-25 16:20:18', null, '菜单', '2016-01-25 16:20:18');
INSERT INTO `sysresourcetype` VALUES ('2', '2016-01-25 16:20:18', null, '功能', '2016-01-25 16:20:18');

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ICONCLS` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `UPDATEDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES ('3', '2016-02-04 16:44:12', '超级管理', null, '超管', '100', '2016-02-04 16:44:47');
INSERT INTO `sysrole` VALUES ('8', '2016-05-20 10:25:08', null, null, '测试23111', '100', '2016-06-13 12:37:45');

-- ----------------------------
-- Table structure for sysrole_sysresource
-- ----------------------------
DROP TABLE IF EXISTS `sysrole_sysresource`;
CREATE TABLE `sysrole_sysresource` (
  `SYSROLE_ID` int(11) NOT NULL,
  `SYSRESOURCE_ID` int(11) NOT NULL,
  PRIMARY KEY (`SYSRESOURCE_ID`,`SYSROLE_ID`),
  KEY `FK_qwhn390g8cel3164un3hkxps5` (`SYSRESOURCE_ID`),
  KEY `FK_kryt0rucec9kg1ipbun7y79xg` (`SYSROLE_ID`),
  CONSTRAINT `FK_kryt0rucec9kg1ipbun7y79xg` FOREIGN KEY (`SYSROLE_ID`) REFERENCES `sysrole` (`id`),
  CONSTRAINT `FK_qwhn390g8cel3164un3hkxps5` FOREIGN KEY (`SYSRESOURCE_ID`) REFERENCES `sysresource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysrole_sysresource
-- ----------------------------
INSERT INTO `sysrole_sysresource` VALUES ('3', '1');
INSERT INTO `sysrole_sysresource` VALUES ('3', '2');
INSERT INTO `sysrole_sysresource` VALUES ('3', '3');
INSERT INTO `sysrole_sysresource` VALUES ('3', '4');
INSERT INTO `sysrole_sysresource` VALUES ('3', '5');
INSERT INTO `sysrole_sysresource` VALUES ('8', '5');
INSERT INTO `sysrole_sysresource` VALUES ('3', '6');
INSERT INTO `sysrole_sysresource` VALUES ('8', '6');
INSERT INTO `sysrole_sysresource` VALUES ('3', '7');
INSERT INTO `sysrole_sysresource` VALUES ('8', '7');
INSERT INTO `sysrole_sysresource` VALUES ('3', '8');
INSERT INTO `sysrole_sysresource` VALUES ('3', '9');
INSERT INTO `sysrole_sysresource` VALUES ('3', '10');
INSERT INTO `sysrole_sysresource` VALUES ('3', '11');
INSERT INTO `sysrole_sysresource` VALUES ('8', '11');
INSERT INTO `sysrole_sysresource` VALUES ('3', '12');
INSERT INTO `sysrole_sysresource` VALUES ('8', '12');
INSERT INTO `sysrole_sysresource` VALUES ('3', '13');
INSERT INTO `sysrole_sysresource` VALUES ('8', '13');
INSERT INTO `sysrole_sysresource` VALUES ('3', '14');
INSERT INTO `sysrole_sysresource` VALUES ('8', '14');
INSERT INTO `sysrole_sysresource` VALUES ('3', '15');
INSERT INTO `sysrole_sysresource` VALUES ('3', '16');
INSERT INTO `sysrole_sysresource` VALUES ('3', '17');
INSERT INTO `sysrole_sysresource` VALUES ('3', '18');
INSERT INTO `sysrole_sysresource` VALUES ('3', '19');
INSERT INTO `sysrole_sysresource` VALUES ('3', '20');
INSERT INTO `sysrole_sysresource` VALUES ('3', '21');
INSERT INTO `sysrole_sysresource` VALUES ('8', '21');

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AGE` int(11) DEFAULT NULL,
  `CREATEDATETIME` datetime DEFAULT NULL,
  `LOGINNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PHOTO` varchar(200) DEFAULT NULL,
  `PWD` varchar(100) DEFAULT NULL,
  `SEX` varchar(1) DEFAULT NULL,
  `UPDATEDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', null, '2016-01-25 16:23:19', 'linhp', '浩鹏', null, 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-06-16 17:27:54');
INSERT INTO `sysuser` VALUES ('2', '2', '2016-01-28 11:38:25', '啊啊士大夫1', 'sad发', null, 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-28 11:41:03');
INSERT INTO `sysuser` VALUES ('3', null, '2016-01-29 11:36:19', 'adf ', 'adf ', '/ssheUploadFile/userPhoto/2016/01/29/ab48560de0dd40829ede8ae4a3c964c3.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-29 11:36:19');
INSERT INTO `sysuser` VALUES ('4', null, '2016-01-29 11:45:16', 'adfadsf', 'asdfadsf', '/ssheUploadFile/userPhoto/2016/01/29/8f2326dd3f44497abacaf4a3d6d1b157.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-29 11:45:16');
INSERT INTO `sysuser` VALUES ('5', null, '2016-01-29 11:45:56', 'asdf', 'adsf', '/ssheUploadFile/userPhoto/2016/01/29/7a0f8fd1d6cd441196fa81cfb8902ba0.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-29 11:45:56');
INSERT INTO `sysuser` VALUES ('6', null, '2016-01-29 11:47:53', 'asdfs', 'adsfs', '/ssheUploadFile/userPhoto/2016/02/02/60a819c528754b9998d3f76e9a34c913.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-02-02 11:33:28');
INSERT INTO `sysuser` VALUES ('8', null, '2016-01-29 11:50:33', 'adswe', 'qea', '/ssheUploadFile/userPhoto/2016/01/29/2b5670fb8086431ab23f18ee3c556ba0.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-29 11:50:33');
INSERT INTO `sysuser` VALUES ('9', null, '2016-01-29 11:51:34', 'ewqer', 'erq', '/ssheUploadFile/userPhoto/2016/01/29/87153e56df524838ae766a561a810a9e.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-01-29 11:51:34');
INSERT INTO `sysuser` VALUES ('13', null, '2016-02-01 14:44:37', '测试', '112', '/ssheUploadFile/userPhoto/2016/02/01/72e4958ed37343f4b017c2f0d2808fa8.png', 'e10adc3949ba59abbe56e057f20f883e', '0', '2016-02-01 14:44:50');
INSERT INTO `sysuser` VALUES ('14', null, '2016-02-02 10:44:34', 'adfsa', 'fsa', '/ssheUploadFile/userPhoto/2016/02/02/386207b2e4f24efbb1b287d06977bf23.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-02-02 10:44:34');
INSERT INTO `sysuser` VALUES ('15', null, '2016-02-02 10:45:34', 'jhgj', 'f', '/ssheUploadFile/userPhoto/2016/02/02/cce5549200704ebeb1b0ef032e71fdd2.jpg', 'e10adc3949ba59abbe56e057f20f883e', '1', '2016-02-04 17:00:05');
INSERT INTO `sysuser` VALUES ('17', null, '2016-06-13 11:14:40', 'haha8910', null, null, '123456', '0', '2016-06-13 12:40:19');

-- ----------------------------
-- Table structure for sysuser_sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysuser_sysrole`;
CREATE TABLE `sysuser_sysrole` (
  `SYSUSER_ID` int(11) NOT NULL,
  `SYSROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`SYSROLE_ID`,`SYSUSER_ID`),
  KEY `FK_63i63dlo86bl3799mdmx2au5x` (`SYSROLE_ID`),
  KEY `FK_jpa5260ddxqonf5ajdjoja5u0` (`SYSUSER_ID`),
  CONSTRAINT `FK_63i63dlo86bl3799mdmx2au5x` FOREIGN KEY (`SYSROLE_ID`) REFERENCES `sysrole` (`id`),
  CONSTRAINT `FK_jpa5260ddxqonf5ajdjoja5u0` FOREIGN KEY (`SYSUSER_ID`) REFERENCES `sysuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser_sysrole
-- ----------------------------
INSERT INTO `sysuser_sysrole` VALUES ('1', '3');
