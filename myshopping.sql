/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : myshopping

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2019-07-18 14:32:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `vipid` varchar(30) DEFAULT NULL,
  `usermoney` int(20) DEFAULT NULL,
  `viplevel` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '07/21', '000001', '10500', '1');
INSERT INTO `user` VALUES ('2', '赵杰', '123456', '05/15', '726313', '2000', '0');
INSERT INTO `user` VALUES ('3', '李晓', '123456', '10/26', '949439', '1760', '1');
INSERT INTO `user` VALUES ('4', '何浩冉', '123459', '07/18', '326250', '3200', '0');
INSERT INTO `user` VALUES ('5', '万鹏', '123456', '06/25', '931504', '1500', '0');
