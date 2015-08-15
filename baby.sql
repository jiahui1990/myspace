/*
Navicat MySQL Data Transfer

Source Server         : localhost_baby
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : baby

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-08-15 19:50:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for globalvariable
-- ----------------------------
DROP TABLE IF EXISTS `globalvariable`;
CREATE TABLE `globalvariable` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `NUMBERS` varchar(20) NOT NULL COMMENT '编号，全局变量命名规则：MDGV[序号]。',
  `CONTENT` varchar(20) DEFAULT NULL COMMENT '内容',
  `COMMENTS` varchar(100) DEFAULT NULL COMMENT '备注',
  `CREATETIME` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='全局变量表';

-- ----------------------------
-- Records of globalvariable
-- ----------------------------
INSERT INTO `globalvariable` VALUES ('1', 'MDGV1', '方法执行成功', '方法执行成功', '2015-06-15 13:34:06');
INSERT INTO `globalvariable` VALUES ('2', 'MDGV2', '方法执行失败', '方法执行失败', '2015-06-15 13:34:06');
INSERT INTO `globalvariable` VALUES ('3', 'MDGV3', '写日志', '写日志', '2015-06-15 13:34:06');
INSERT INTO `globalvariable` VALUES ('4', 'MDGV4', '不写日志', '不写日志', '2015-06-15 13:34:06');
INSERT INTO `globalvariable` VALUES ('5', 'MDGV5', 'login_info', '网页登陆用户session', '2015-06-15 13:34:06');
INSERT INTO `globalvariable` VALUES ('6', 'MDGV6', '未保存', '未保存', '2015-07-28 14:48:46');
INSERT INTO `globalvariable` VALUES ('7', 'MDGV7', '已保存', '已保存', '2015-07-28 14:48:46');

-- ----------------------------
-- Table structure for kindergarten
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten`;
CREATE TABLE `kindergarten` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '幼儿园id',
  `name` varchar(100) DEFAULT NULL COMMENT '幼儿园名字',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='幼儿园对象';

-- ----------------------------
-- Records of kindergarten
-- ----------------------------
INSERT INTO `kindergarten` VALUES ('1', '第一幼儿园', '2015-07-06 18:16:00');
INSERT INTO `kindergarten` VALUES ('2', '第二幼儿园', '2015-07-08 10:14:07');
INSERT INTO `kindergarten` VALUES ('3', '第三幼儿园', '2015-08-01 15:14:39');

-- ----------------------------
-- Table structure for kindergartenarea
-- ----------------------------
DROP TABLE IF EXISTS `kindergartenarea`;
CREATE TABLE `kindergartenarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '区域id',
  `kindergartenId` bigint(20) DEFAULT NULL COMMENT '幼儿园id',
  `name` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id 一级区域父id为0',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK17081E53FB62D231` (`kindergartenId`),
  CONSTRAINT `FK17081E53FB62D231` FOREIGN KEY (`kindergartenId`) REFERENCES `kindergarten` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='幼儿园区域';

-- ----------------------------
-- Records of kindergartenarea
-- ----------------------------
INSERT INTO `kindergartenarea` VALUES ('1', '1', '教室', '0', '2015-07-06 18:16:00');
INSERT INTO `kindergartenarea` VALUES ('2', '1', '一班', '1', '2015-07-06 18:16:00');
INSERT INTO `kindergartenarea` VALUES ('3', '2', '教室2', '0', '2015-07-08 10:14:07');
INSERT INTO `kindergartenarea` VALUES ('4', '2', '', '3', '2015-07-08 10:14:07');
INSERT INTO `kindergartenarea` VALUES ('5', '1', '二班', '1', '2015-08-01 11:33:06');
INSERT INTO `kindergartenarea` VALUES ('6', '3', '校门', '0', '2015-08-01 15:14:39');
INSERT INTO `kindergartenarea` VALUES ('7', '3', '前门', '6', '2015-08-01 15:14:39');

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '监控id',
  `areaId` bigint(20) DEFAULT NULL COMMENT '监控所属区域id',
  `name` varchar(50) DEFAULT NULL COMMENT '监控名称',
  `sn` varchar(50) DEFAULT NULL COMMENT '监控sn号',
  `beginWatchTime` varchar(30) DEFAULT NULL COMMENT '开始观看时间',
  `endWatchTime` varchar(30) DEFAULT NULL COMMENT '结束观看时间',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `isSaved` bigint(20) DEFAULT '7' COMMENT '是否被保存了',
  PRIMARY KEY (`id`),
  KEY `FK49B0BD5A364B88E5` (`areaId`),
  CONSTRAINT `FK49B0BD5A364B88E5` FOREIGN KEY (`areaId`) REFERENCES `kindergartenarea` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='监控对象';

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('1', '2', '前门', ' B4B6115100950', '00:00', '23:59', '2015-07-06 18:16:00', '7');
INSERT INTO `monitor` VALUES ('2', '2', '后门', '4D41215105062', '04:00', '23:00', '2015-07-06 18:16:39', '7');
INSERT INTO `monitor` VALUES ('21', '7', '四川', '123', '09:00', '17:00', '2015-08-01 15:14:39', '7');
INSERT INTO `monitor` VALUES ('22', '5', '1', ' B4B6115100950', '09:00', '23:00', '2015-08-01 15:16:08', '7');

-- ----------------------------
-- Table structure for msgtip
-- ----------------------------
DROP TABLE IF EXISTS `msgtip`;
CREATE TABLE `msgtip` (
  `ID` int(19) unsigned NOT NULL AUTO_INCREMENT,
  `ISLOG` int(19) DEFAULT NULL COMMENT '是否保存系统日志 1是 0否',
  `ISSUCCESS` int(19) DEFAULT NULL COMMENT '方法正确或者错误错误 true正确 false错误',
  `NAME` varchar(20) NOT NULL COMMENT '提示信息编号，如MSG0000',
  `RETURNMSG` varchar(100) DEFAULT NULL COMMENT '返回的具体信息',
  `RETURNTCO` varchar(100) DEFAULT NULL COMMENT '返回信息的图标',
  `RETURNURL` varchar(100) DEFAULT NULL COMMENT '返回信息页面的地址',
  `CREATETIME` varchar(30) DEFAULT NULL COMMENT '错误信息增加的日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msgtip
-- ----------------------------
INSERT INTO `msgtip` VALUES ('1', '3', '2', 'MSG4001', '系统错误', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('2', '3', '2', 'MSG4002', '用户名不存在', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('3', '3', '2', 'MSG4003', '密码错误', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('4', '3', '2', 'MSG4004', '用户未生效', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('5', '3', '2', 'MSG4005', '用户已过期', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('6', '3', '1', 'MSG6001', '管理员登陆成功页面', null, '/userMonitorAction_actualTimeMonitor.action', '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('7', '3', '1', 'MSG6002', '幼儿园管理员登陆成功页面', null, '/userMonitorAction_actualTimeMonitorK.action', '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('8', '3', '1', 'MSG6003', '普通用户登陆成功页面', null, '/userMonitorAction_userMonitor.action', '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('9', '3', '1', 'MSG6004', '进入管理员实时页面', null, '/baby/admin/actualTime.jsp', '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('10', '3', '2', 'MSG4006', '该账号有其它用户使用，是否继续登录', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('11', '3', '2', 'MSG4007', '该账号登陆设备过多，请输入手机验证码', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('12', '3', '2', 'MSG4009', '账号登陆设备过多，且没绑定手机，请联系管理员', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('13', '3', '2', 'MSG4010', '手机验证码输入错误', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('14', '3', '1', 'MSG0001', '在线', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('15', '3', '2', 'MSG4011', '下线', null, null, '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('16', '3', '1', 'MSG6005', '监控管理页面', null, '/baby/monitorManage/monitorManage.jsp', '2015-06-23 18:02:11');
INSERT INTO `msgtip` VALUES ('17', '3', '1', 'MSG6006', '进入添加监控页面', null, '/baby/monitorManage/addMonitor.jsp', '2015-06-24 11:18:06');
INSERT INTO `msgtip` VALUES ('18', '3', '1', 'MSG0002', '添加监控成功', null, null, '2015-06-24 11:18:06');
INSERT INTO `msgtip` VALUES ('19', '3', '1', 'MSG6007', '进入用户管理页面', null, '/baby/userManage/userManage.jsp', '2015-06-24 11:18:06');
INSERT INTO `msgtip` VALUES ('20', '3', '1', 'MSG6008', '进入生成用户页面', null, '/baby/userManage/generateUsers.jsp', '2015-06-24 11:18:06');
INSERT INTO `msgtip` VALUES ('21', '3', '1', 'MSG0003', '生成用户成功', null, null, '2015-06-24 11:18:06');
INSERT INTO `msgtip` VALUES ('22', '3', '1', 'MSG6009', '幼儿园管理员实时页面', null, '/baby/admin/kactualTime.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('23', '3', '1', 'MSG6010', '用户信息页面—幼儿园管理', null, '/baby/userManage/kuserManage.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('24', '3', '1', 'MSG6011', '监控信息页面', null, '/baby/monitorManage/kmonitorManage.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('25', '3', '1', 'MSG6012', '管理员个人中心页面', null, '/baby/personalCenter/mCenter.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('26', '3', '1', 'MSG6013', '幼儿园管理员个人中心页面', null, '/baby/personalCenter/kCenter.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('27', '3', '1', 'MSG6014', '普通用户个人中心页面', null, '/baby/personalCenter/nCenter.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('28', '3', '1', 'MSG0004', '更新个人信息成功', null, null, '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('29', '3', '1', 'MSG6015', '退出登陆', null, '/', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('30', '3', '1', 'MSG6016', '个人监控页面', null, '/baby/admin/userMonitor.jsp', '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('31', '3', '1', 'MSG0005', '删除用户成功', null, null, '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('32', '3', '1', 'MSG0006', '更新用户成功', null, null, '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('33', '3', '1', 'MSG0007', '删除监控成功', null, null, '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('34', '3', '1', 'MSG0008', '更新监控成功', null, null, '2015-06-25 15:17:17');
INSERT INTO `msgtip` VALUES ('35', '3', '1', 'MSG0000', '方法执行成功', null, null, '2015-07-05 16:40:14');
INSERT INTO `msgtip` VALUES ('36', '3', '1', 'MSG0009', '修改密码成功', null, null, '2015-07-05 16:40:14');
INSERT INTO `msgtip` VALUES ('37', '3', '2', 'MSG4012', '发送手机验证码失败，请稍后再试。', '', '', '2015-07-05 16:40:14');
INSERT INTO `msgtip` VALUES ('38', '3', '2', 'MSG4013', '手机验证码过期', '', '', '2015-07-05 16:40:14');
INSERT INTO `msgtip` VALUES ('39', '3', '2', 'MSG4014', '手机app只支持普通用户登陆', '', '', '2015-07-13 15:08:51');
INSERT INTO `msgtip` VALUES ('40', '3', '1', 'MSG0010', '绑定手机号码成功', '', '', '2015-07-13 15:08:51');
INSERT INTO `msgtip` VALUES ('41', '3', '1', 'MSG0011', '保存用户成功', '', '', '2015-07-28 16:43:22');
INSERT INTO `msgtip` VALUES ('42', '3', '1', 'MSG0012', '保存设备成功', '', '', '2015-07-28 16:43:22');

-- ----------------------------
-- Table structure for phonecode
-- ----------------------------
DROP TABLE IF EXISTS `phonecode`;
CREATE TABLE `phonecode` (
  `id` bigint(20) NOT NULL COMMENT '验证码id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `code` varchar(15) DEFAULT NULL COMMENT '验证码',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `isValidate` bigint(20) DEFAULT NULL COMMENT '是否验证过 与全局变量关联',
  PRIMARY KEY (`id`),
  KEY `FKC2B1845B434396E` (`userId`),
  CONSTRAINT `FKC2B1845B434396E` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机验证码';

-- ----------------------------
-- Records of phonecode
-- ----------------------------
INSERT INTO `phonecode` VALUES ('1', '1', '814408', '2015-07-06 15:43:34', null);
INSERT INTO `phonecode` VALUES ('2', '1', '262863', '2015-07-06 15:45:01', null);
INSERT INTO `phonecode` VALUES ('3', '1', '890061', '2015-07-06 18:00:12', null);
INSERT INTO `phonecode` VALUES ('4', '1', '749674', '2015-07-06 18:02:30', null);
INSERT INTO `phonecode` VALUES ('5', '1', '638362', '2015-07-06 18:02:36', null);
INSERT INTO `phonecode` VALUES ('6', '1', '200574', '2015-07-06 18:02:40', null);
INSERT INTO `phonecode` VALUES ('7', '1', '565525', '2015-07-06 18:05:11', null);
INSERT INTO `phonecode` VALUES ('8', '1', '740178', '2015-07-06 18:05:44', null);
INSERT INTO `phonecode` VALUES ('9', '1', '514543', '2015-07-06 18:57:30', null);
INSERT INTO `phonecode` VALUES ('10', '1', '774671', '2015-07-06 21:42:00', null);
INSERT INTO `phonecode` VALUES ('11', '1', '838686', '2015-07-06 22:20:51', null);
INSERT INTO `phonecode` VALUES ('12', '1', '306477', '2015-07-06 22:25:11', null);
INSERT INTO `phonecode` VALUES ('13', '1', '940102', '2015-07-06 22:27:30', null);
INSERT INTO `phonecode` VALUES ('14', '1', '411137', '2015-07-06 22:27:49', null);
INSERT INTO `phonecode` VALUES ('15', '1', '275980', '2015-07-06 22:30:27', null);
INSERT INTO `phonecode` VALUES ('16', '1', '290112', '2015-07-06 22:30:34', null);
INSERT INTO `phonecode` VALUES ('17', '1', '305553', '2015-07-06 22:30:46', null);
INSERT INTO `phonecode` VALUES ('18', '1', '956192', '2015-07-06 22:33:28', null);
INSERT INTO `phonecode` VALUES ('19', '1', '338626', '2015-07-06 22:33:32', null);
INSERT INTO `phonecode` VALUES ('20', '1', '678993', '2015-07-06 22:33:53', null);
INSERT INTO `phonecode` VALUES ('21', '1', '799944', '2015-07-06 22:33:56', null);
INSERT INTO `phonecode` VALUES ('22', '1', '509921', '2015-07-06 22:36:50', null);
INSERT INTO `phonecode` VALUES ('23', '1', '199046', '2015-07-07 08:40:19', null);
INSERT INTO `phonecode` VALUES ('24', '1', '985956', '2015-07-07 08:52:43', null);
INSERT INTO `phonecode` VALUES ('25', '1', '805060', '2015-07-07 09:28:17', null);
INSERT INTO `phonecode` VALUES ('26', '1', '754762', '2015-07-07 22:58:00', null);
INSERT INTO `phonecode` VALUES ('27', '1', '860136', '2015-07-08 08:57:40', null);
INSERT INTO `phonecode` VALUES ('28', '1', '431101', '2015-07-08 09:01:38', null);
INSERT INTO `phonecode` VALUES ('29', '1', '377663', '2015-07-08 09:06:34', null);
INSERT INTO `phonecode` VALUES ('30', '1', '726617', '2015-07-08 09:14:42', null);
INSERT INTO `phonecode` VALUES ('31', '1', '216251', '2015-07-08 09:18:11', null);
INSERT INTO `phonecode` VALUES ('32', '1', '219411', '2015-07-08 09:18:26', null);
INSERT INTO `phonecode` VALUES ('33', '1', '439713', '2015-07-08 18:33:35', null);
INSERT INTO `phonecode` VALUES ('34', '1', '437910', '2015-07-08 21:02:52', null);
INSERT INTO `phonecode` VALUES ('35', '1', '157327', '2015-07-09 08:42:09', null);
INSERT INTO `phonecode` VALUES ('36', '1', '672080', '2015-07-09 09:20:55', null);
INSERT INTO `phonecode` VALUES ('37', '1', '527251', '2015-07-09 11:58:11', null);
INSERT INTO `phonecode` VALUES ('38', '1', '633854', '2015-07-09 14:10:10', null);
INSERT INTO `phonecode` VALUES ('39', '1', '721116', '2015-07-10 10:27:31', null);
INSERT INTO `phonecode` VALUES ('40', '1', '847437', '2015-07-10 10:28:16', null);
INSERT INTO `phonecode` VALUES ('41', '1', '230293', '2015-07-13 21:41:56', null);
INSERT INTO `phonecode` VALUES ('42', '1', '621937', '2015-07-14 19:50:58', null);
INSERT INTO `phonecode` VALUES ('43', '1', '477915', '2015-07-14 19:51:42', null);
INSERT INTO `phonecode` VALUES ('44', '1', '361988', '2015-07-14 21:30:53', null);
INSERT INTO `phonecode` VALUES ('45', '1', '991685', '2015-07-14 21:49:17', null);
INSERT INTO `phonecode` VALUES ('46', '1', '510485', '2015-07-14 21:50:23', null);
INSERT INTO `phonecode` VALUES ('47', '1', '358207', '2015-07-15 08:44:40', null);
INSERT INTO `phonecode` VALUES ('48', '1', '399573', '2015-07-15 08:44:47', null);
INSERT INTO `phonecode` VALUES ('49', '1', '757338', '2015-07-15 08:44:52', null);
INSERT INTO `phonecode` VALUES ('50', '1', '973627', '2015-07-15 19:10:10', null);
INSERT INTO `phonecode` VALUES ('51', '24', '378779', '2015-07-15 19:10:40', null);
INSERT INTO `phonecode` VALUES ('52', '1', '214490', '2015-07-15 19:12:28', null);
INSERT INTO `phonecode` VALUES ('53', '23', '976937', '2015-07-15 19:18:42', null);
INSERT INTO `phonecode` VALUES ('54', '23', '600540', '2015-07-15 19:20:51', null);
INSERT INTO `phonecode` VALUES ('55', '24', '850693', '2015-07-21 20:23:43', null);
INSERT INTO `phonecode` VALUES ('56', '24', '484965', '2015-07-22 13:28:09', null);
INSERT INTO `phonecode` VALUES ('57', '24', '102266', '2015-07-24 08:49:48', null);
INSERT INTO `phonecode` VALUES ('58', '24', '453145', '2015-07-24 15:24:48', null);
INSERT INTO `phonecode` VALUES ('59', '23', '851370', '2015-07-24 16:07:28', null);
INSERT INTO `phonecode` VALUES ('60', '23', '812988', '2015-07-24 16:07:34', null);
INSERT INTO `phonecode` VALUES ('61', '23', '926651', '2015-07-24 16:07:56', null);
INSERT INTO `phonecode` VALUES ('62', '23', '125565', '2015-07-26 13:13:43', null);
INSERT INTO `phonecode` VALUES ('63', '24', '142026', '2015-07-26 13:27:45', null);
INSERT INTO `phonecode` VALUES ('64', '24', '638238', '2015-07-26 13:42:24', null);
INSERT INTO `phonecode` VALUES ('65', '24', '628606', '2015-07-26 13:42:38', null);
INSERT INTO `phonecode` VALUES ('66', '24', '588512', '2015-07-26 13:42:44', null);
INSERT INTO `phonecode` VALUES ('67', '1', '319673', '2015-07-26 16:01:51', null);
INSERT INTO `phonecode` VALUES ('68', '1', '743935', '2015-07-26 16:03:39', null);
INSERT INTO `phonecode` VALUES ('69', '1', '679238', '2015-07-26 16:04:46', null);
INSERT INTO `phonecode` VALUES ('70', '1', '495367', '2015-07-26 16:17:40', null);
INSERT INTO `phonecode` VALUES ('71', '5', '962294', '2015-07-26 16:18:28', null);
INSERT INTO `phonecode` VALUES ('72', '5', '251252', '2015-07-26 16:21:27', null);
INSERT INTO `phonecode` VALUES ('73', '5', '672127', '2015-07-26 16:26:21', null);
INSERT INTO `phonecode` VALUES ('74', '5', '474019', '2015-07-26 16:36:41', null);
INSERT INTO `phonecode` VALUES ('75', '5', '975878', '2015-07-26 16:37:45', null);
INSERT INTO `phonecode` VALUES ('76', '1', '997037', '2015-07-26 16:47:19', null);
INSERT INTO `phonecode` VALUES ('77', '1', '797783', '2015-07-27 16:57:46', null);
INSERT INTO `phonecode` VALUES ('78', '40', '858949', '2015-07-27 17:59:20', null);
INSERT INTO `phonecode` VALUES ('79', '40', '324943', '2015-07-27 17:59:36', null);
INSERT INTO `phonecode` VALUES ('80', '40', '523013', '2015-07-27 17:59:47', null);
INSERT INTO `phonecode` VALUES ('81', '40', '719325', '2015-07-27 18:00:12', null);
INSERT INTO `phonecode` VALUES ('82', '40', '493668', '2015-07-27 18:00:15', null);
INSERT INTO `phonecode` VALUES ('83', '1', '110833', '2015-07-27 19:33:37', null);
INSERT INTO `phonecode` VALUES ('84', '19', '389558', '2015-07-31 14:43:09', null);
INSERT INTO `phonecode` VALUES ('85', '19', '105234', '2015-07-31 23:10:34', null);
INSERT INTO `phonecode` VALUES ('86', '19', '367656', '2015-07-31 23:38:19', null);
INSERT INTO `phonecode` VALUES ('87', '40', '993291', '2015-08-01 00:45:04', null);
INSERT INTO `phonecode` VALUES ('88', '24', '149697', '2015-08-01 00:45:19', null);
INSERT INTO `phonecode` VALUES ('89', '1', '647865', '2015-08-01 11:11:46', null);
INSERT INTO `phonecode` VALUES ('90', '1', '575475', '2015-08-01 11:12:07', null);
INSERT INTO `phonecode` VALUES ('91', '1', '943398', '2015-08-01 11:18:46', null);
INSERT INTO `phonecode` VALUES ('92', '45', '178548', '2015-08-01 11:26:51', null);
INSERT INTO `phonecode` VALUES ('93', '45', '678436', '2015-08-01 11:27:19', null);
INSERT INTO `phonecode` VALUES ('94', '45', '170513', '2015-08-01 11:28:06', null);
INSERT INTO `phonecode` VALUES ('95', '43', '423989', '2015-08-01 11:32:41', null);
INSERT INTO `phonecode` VALUES ('96', '43', '796071', '2015-08-01 11:33:40', null);
INSERT INTO `phonecode` VALUES ('97', '1', '480319', '2015-08-01 14:38:08', null);
INSERT INTO `phonecode` VALUES ('98', '1', '673286', '2015-08-01 14:44:24', null);
INSERT INTO `phonecode` VALUES ('99', '43', '896436', '2015-08-01 14:51:18', null);
INSERT INTO `phonecode` VALUES ('100', '40', '953716', '2015-08-02 11:47:29', null);
INSERT INTO `phonecode` VALUES ('101', '40', '202542', '2015-08-02 11:47:45', null);
INSERT INTO `phonecode` VALUES ('102', '1', '357202', '2015-08-03 15:32:00', null);
INSERT INTO `phonecode` VALUES ('103', '19', '902235', '2015-08-03 17:45:23', null);
INSERT INTO `phonecode` VALUES ('104', '19', '904968', '2015-08-03 17:48:24', null);
INSERT INTO `phonecode` VALUES ('105', '19', '389644', '2015-08-03 17:48:42', null);
INSERT INTO `phonecode` VALUES ('106', '63', '977929', '2015-08-03 20:35:26', null);
INSERT INTO `phonecode` VALUES ('107', '63', '526882', '2015-08-03 20:38:33', null);
INSERT INTO `phonecode` VALUES ('108', '19', '511947', '2015-08-03 20:41:26', null);
INSERT INTO `phonecode` VALUES ('109', '19', '263734', '2015-08-03 20:48:43', null);
INSERT INTO `phonecode` VALUES ('110', '61', '853516', '2015-08-03 20:53:32', null);
INSERT INTO `phonecode` VALUES ('111', '63', '644803', '2015-08-04 21:47:17', null);
INSERT INTO `phonecode` VALUES ('112', '61', '812440', '2015-08-04 21:47:38', null);
INSERT INTO `phonecode` VALUES ('113', '61', '662358', '2015-08-04 21:48:17', null);
INSERT INTO `phonecode` VALUES ('114', '61', '903743', '2015-08-04 21:49:23', null);
INSERT INTO `phonecode` VALUES ('115', '19', '616521', '2015-08-04 23:02:21', null);
INSERT INTO `phonecode` VALUES ('116', '24', '906938', '2015-08-05 10:05:25', null);
INSERT INTO `phonecode` VALUES ('117', '1', '792759', '2015-08-05 10:16:48', null);
INSERT INTO `phonecode` VALUES ('118', '1', '650978', '2015-08-05 10:26:25', null);
INSERT INTO `phonecode` VALUES ('119', '1', '909549', '2015-08-05 10:26:30', null);
INSERT INTO `phonecode` VALUES ('120', '1', '435865', '2015-08-05 10:27:08', null);
INSERT INTO `phonecode` VALUES ('121', '1', '567458', '2015-08-05 10:27:28', null);
INSERT INTO `phonecode` VALUES ('122', '43', '364088', '2015-08-05 10:37:13', null);
INSERT INTO `phonecode` VALUES ('123', '45', '937145', '2015-08-05 10:38:35', null);
INSERT INTO `phonecode` VALUES ('124', '43', '442435', '2015-08-05 10:44:01', null);
INSERT INTO `phonecode` VALUES ('125', '24', '690694', '2015-08-05 10:44:32', null);
INSERT INTO `phonecode` VALUES ('126', '24', '363148', '2015-08-05 10:45:49', null);
INSERT INTO `phonecode` VALUES ('127', '24', '445839', '2015-08-05 10:46:08', null);
INSERT INTO `phonecode` VALUES ('128', '24', '604865', '2015-08-05 10:49:50', null);
INSERT INTO `phonecode` VALUES ('129', '44', '830739', '2015-08-05 11:52:23', null);
INSERT INTO `phonecode` VALUES ('130', '63', '103323', '2015-08-06 15:46:38', null);
INSERT INTO `phonecode` VALUES ('131', '63', '290989', '2015-08-06 16:01:27', null);
INSERT INTO `phonecode` VALUES ('132', '63', '253033', '2015-08-06 16:01:59', null);
INSERT INTO `phonecode` VALUES ('133', '63', '425163', '2015-08-06 16:02:23', null);
INSERT INTO `phonecode` VALUES ('134', '61', '823612', '2015-08-06 16:02:44', null);
INSERT INTO `phonecode` VALUES ('135', '19', '280624', '2015-08-06 16:09:50', null);
INSERT INTO `phonecode` VALUES ('136', '19', '946494', '2015-08-06 20:05:56', null);
INSERT INTO `phonecode` VALUES ('137', '19', '812382', '2015-08-06 20:06:59', null);
INSERT INTO `phonecode` VALUES ('138', '19', '382240', '2015-08-06 20:07:32', null);
INSERT INTO `phonecode` VALUES ('139', '19', '933633', '2015-08-06 20:07:44', null);
INSERT INTO `phonecode` VALUES ('140', '57', '341371', '2015-08-06 22:18:07', null);
INSERT INTO `phonecode` VALUES ('141', '57', '449090', '2015-08-06 22:19:12', null);
INSERT INTO `phonecode` VALUES ('142', '57', '369592', '2015-08-06 22:19:20', null);
INSERT INTO `phonecode` VALUES ('143', '57', '989532', '2015-08-06 22:20:12', null);
INSERT INTO `phonecode` VALUES ('144', '1', '361913', '2015-08-06 22:28:45', null);
INSERT INTO `phonecode` VALUES ('145', '1', '507042', '2015-08-06 22:29:15', null);
INSERT INTO `phonecode` VALUES ('146', '63', '282170', '2015-08-07 07:36:25', null);
INSERT INTO `phonecode` VALUES ('147', '63', '686068', '2015-08-07 08:57:03', null);
INSERT INTO `phonecode` VALUES ('148', '63', '325004', '2015-08-07 09:05:22', null);
INSERT INTO `phonecode` VALUES ('149', '63', '531455', '2015-08-07 09:09:03', null);
INSERT INTO `phonecode` VALUES ('150', '63', '225074', '2015-08-07 09:11:34', null);
INSERT INTO `phonecode` VALUES ('151', '19', '315349', '2015-08-07 09:13:11', null);
INSERT INTO `phonecode` VALUES ('152', '19', '768394', '2015-08-07 09:15:17', null);
INSERT INTO `phonecode` VALUES ('153', '19', '685965', '2015-08-07 09:26:46', null);
INSERT INTO `phonecode` VALUES ('154', '19', '991501', '2015-08-07 11:11:27', null);
INSERT INTO `phonecode` VALUES ('155', '19', '671578', '2015-08-07 16:03:30', null);
INSERT INTO `phonecode` VALUES ('156', '57', '834019', '2015-08-07 16:05:09', null);
INSERT INTO `phonecode` VALUES ('157', '57', '346643', '2015-08-07 16:07:27', null);
INSERT INTO `phonecode` VALUES ('158', '61', '533547', '2015-08-08 09:10:47', null);
INSERT INTO `phonecode` VALUES ('159', '63', '816684', '2015-08-08 09:11:52', null);
INSERT INTO `phonecode` VALUES ('160', '19', '652720', '2015-08-08 22:03:45', null);
INSERT INTO `phonecode` VALUES ('161', '19', '774491', '2015-08-08 22:04:20', null);
INSERT INTO `phonecode` VALUES ('162', '19', '742153', '2015-08-08 22:04:31', null);
INSERT INTO `phonecode` VALUES ('163', '19', '573825', '2015-08-08 22:10:00', null);
INSERT INTO `phonecode` VALUES ('164', '19', '247576', '2015-08-08 22:12:33', null);
INSERT INTO `phonecode` VALUES ('165', '44', '863079', '2015-08-08 22:13:58', null);
INSERT INTO `phonecode` VALUES ('166', '44', '453407', '2015-08-08 22:19:21', null);
INSERT INTO `phonecode` VALUES ('167', '44', '394734', '2015-08-08 22:19:47', null);
INSERT INTO `phonecode` VALUES ('168', '44', '488699', '2015-08-08 22:19:49', null);
INSERT INTO `phonecode` VALUES ('169', '41', '864366', '2015-08-09 16:00:49', null);
INSERT INTO `phonecode` VALUES ('170', '41', '458416', '2015-08-09 16:18:39', null);
INSERT INTO `phonecode` VALUES ('171', '41', '514152', '2015-08-09 16:19:28', null);
INSERT INTO `phonecode` VALUES ('172', '41', '741204', '2015-08-09 16:43:04', null);
INSERT INTO `phonecode` VALUES ('173', '41', '202185', '2015-08-09 16:47:53', null);
INSERT INTO `phonecode` VALUES ('174', '307', '421729', '2015-08-09 17:02:14', null);
INSERT INTO `phonecode` VALUES ('175', '307', '907581', '2015-08-09 17:04:08', null);
INSERT INTO `phonecode` VALUES ('176', '307', '858735', '2015-08-09 17:17:21', null);
INSERT INTO `phonecode` VALUES ('177', '307', '994785', '2015-08-10 22:05:04', null);
INSERT INTO `phonecode` VALUES ('178', '19', '807845', '2015-08-10 22:09:22', null);
INSERT INTO `phonecode` VALUES ('179', '43', '867586', '2015-08-11 09:42:44', null);
INSERT INTO `phonecode` VALUES ('180', '63', '959033', '2015-08-11 16:39:45', null);
INSERT INTO `phonecode` VALUES ('181', '61', '244914', '2015-08-11 18:35:03', null);
INSERT INTO `phonecode` VALUES ('182', '61', '209487', '2015-08-11 18:36:03', null);
INSERT INTO `phonecode` VALUES ('183', '61', '209487', '2015-08-11 18:36:03', null);
INSERT INTO `phonecode` VALUES ('184', '61', '209487', '2015-08-11 18:36:03', null);
INSERT INTO `phonecode` VALUES ('185', '61', '209487', '2015-08-11 18:36:03', null);
INSERT INTO `phonecode` VALUES ('186', '61', '918916', '2015-08-13 09:47:27', null);
INSERT INTO `phonecode` VALUES ('187', '61', '842685', '2015-08-13 22:31:27', null);
INSERT INTO `phonecode` VALUES ('188', '61', '299720', '2015-08-13 22:31:30', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `roleNumber` varchar(10) DEFAULT NULL COMMENT '角色标号 如PROLE1',
  `comments` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'PROLE1', '管理员');
INSERT INTO `role` VALUES ('2', 'PROLE2', '幼儿园管理员');
INSERT INTO `role` VALUES ('3', 'PROLE3', '普通用户');

-- ----------------------------
-- Table structure for userarea
-- ----------------------------
DROP TABLE IF EXISTS `userarea`;
CREATE TABLE `userarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户区域id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `areaId` bigint(20) DEFAULT NULL COMMENT '区域id',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建事件',
  PRIMARY KEY (`id`),
  KEY `FKF023DED8364B88E5` (`areaId`),
  KEY `FKF023DED8434396E` (`userId`),
  CONSTRAINT `FKF023DED8364B88E5` FOREIGN KEY (`areaId`) REFERENCES `kindergartenarea` (`id`),
  CONSTRAINT `FKF023DED8434396E` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='用户区域中间表';

-- ----------------------------
-- Records of userarea
-- ----------------------------
INSERT INTO `userarea` VALUES ('1', '5', '2', '2015-07-06 22:16:51');
INSERT INTO `userarea` VALUES ('4', '8', '2', '2015-07-08 09:41:13');
INSERT INTO `userarea` VALUES ('6', '10', '2', '2015-07-08 09:41:23');
INSERT INTO `userarea` VALUES ('12', '16', '2', '2015-07-08 09:45:38');
INSERT INTO `userarea` VALUES ('21', '36', '2', '2015-07-26 13:32:49');
INSERT INTO `userarea` VALUES ('25', '49', '5', '2015-08-01 14:49:30');
INSERT INTO `userarea` VALUES ('27', '51', '5', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('29', '53', '5', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('30', '54', '5', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('31', '55', '2', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('32', '56', '5', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('33', '57', '2', '2015-08-01 15:11:47');
INSERT INTO `userarea` VALUES ('34', '58', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('35', '59', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('36', '60', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('37', '61', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('38', '62', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('39', '63', '2', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('40', '64', '5', '2015-08-01 15:12:55');
INSERT INTO `userarea` VALUES ('72', '102', '5', '2015-08-05 14:49:33');
INSERT INTO `userarea` VALUES ('73', '103', '2', '2015-08-05 14:50:34');
INSERT INTO `userarea` VALUES ('74', '105', '2', '2015-08-07 16:59:08');
INSERT INTO `userarea` VALUES ('75', '106', '2', '2015-08-07 16:59:38');
INSERT INTO `userarea` VALUES ('76', '307', '5', '2015-08-09 15:16:00');
INSERT INTO `userarea` VALUES ('77', '308', '5', '2015-08-09 15:18:41');
INSERT INTO `userarea` VALUES ('78', '309', '5', '2015-08-11 09:56:42');
INSERT INTO `userarea` VALUES ('79', '310', '5', '2015-08-11 09:56:42');

-- ----------------------------
-- Table structure for userkinder
-- ----------------------------
DROP TABLE IF EXISTS `userkinder`;
CREATE TABLE `userkinder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户幼儿园中间表id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `kindergartenId` bigint(20) DEFAULT NULL COMMENT '幼儿园id',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK873D5ACCFB62D231` (`kindergartenId`),
  KEY `FK873D5ACC434396E` (`userId`),
  CONSTRAINT `FK873D5ACC434396E` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `FK873D5ACCFB62D231` FOREIGN KEY (`kindergartenId`) REFERENCES `kindergarten` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和幼儿园中间表 幼儿园管理员';

-- ----------------------------
-- Records of userkinder
-- ----------------------------

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `id` bigint(20) NOT NULL COMMENT '用户角色id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `roleid` bigint(20) DEFAULT NULL COMMENT '角色id',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FKF02B8EC1D2D62747` (`roleid`),
  KEY `FKF02B8EC1434396E` (`userid`),
  CONSTRAINT `FKF02B8EC1434396E` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `FKF02B8EC1D2D62747` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('1', '1', '1', '2015-06-23 14:49:10');
INSERT INTO `userrole` VALUES ('5', '5', '3', '2015-07-06 22:16:51');
INSERT INTO `userrole` VALUES ('8', '8', '3', '2015-07-08 09:41:13');
INSERT INTO `userrole` VALUES ('10', '10', '3', '2015-07-08 09:41:23');
INSERT INTO `userrole` VALUES ('16', '16', '3', '2015-07-08 09:45:38');
INSERT INTO `userrole` VALUES ('19', '19', '1', '2015-07-14 19:52:15');
INSERT INTO `userrole` VALUES ('23', '23', '1', '2015-07-14 22:00:00');
INSERT INTO `userrole` VALUES ('24', '24', '1', '2015-07-15 09:08:24');
INSERT INTO `userrole` VALUES ('36', '36', '3', '2015-07-26 13:32:49');
INSERT INTO `userrole` VALUES ('40', '40', '1', '2015-07-26 14:13:45');
INSERT INTO `userrole` VALUES ('41', '41', '1', '2015-07-26 14:13:45');
INSERT INTO `userrole` VALUES ('43', '43', '1', '2015-08-01 00:46:09');
INSERT INTO `userrole` VALUES ('44', '44', '1', '2015-08-01 00:46:09');
INSERT INTO `userrole` VALUES ('45', '45', '1', '2015-08-01 00:47:51');
INSERT INTO `userrole` VALUES ('46', '46', '1', '2015-08-01 00:47:51');
INSERT INTO `userrole` VALUES ('49', '49', '3', '2015-08-01 14:49:30');
INSERT INTO `userrole` VALUES ('51', '51', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('53', '53', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('54', '54', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('55', '55', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('56', '56', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('57', '57', '3', '2015-08-01 15:11:47');
INSERT INTO `userrole` VALUES ('58', '58', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('59', '59', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('60', '60', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('61', '61', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('62', '62', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('63', '63', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('64', '64', '3', '2015-08-01 15:12:55');
INSERT INTO `userrole` VALUES ('101', '101', '1', '2015-08-05 14:46:59');
INSERT INTO `userrole` VALUES ('102', '102', '3', '2015-08-05 14:49:33');
INSERT INTO `userrole` VALUES ('103', '103', '3', '2015-08-05 14:50:34');
INSERT INTO `userrole` VALUES ('104', '104', '1', '2015-08-07 16:23:32');
INSERT INTO `userrole` VALUES ('105', '105', '3', '2015-08-07 16:59:08');
INSERT INTO `userrole` VALUES ('106', '106', '3', '2015-08-07 16:59:38');
INSERT INTO `userrole` VALUES ('107', '107', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('108', '108', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('109', '109', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('110', '110', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('111', '111', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('112', '112', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('113', '113', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('114', '114', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('115', '115', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('116', '116', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('117', '117', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('118', '118', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('119', '119', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('120', '120', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('121', '121', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('122', '122', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('123', '123', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('124', '124', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('125', '125', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('126', '126', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('127', '127', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('128', '128', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('129', '129', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('130', '130', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('131', '131', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('132', '132', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('133', '133', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('134', '134', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('135', '135', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('136', '136', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('137', '137', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('138', '138', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('139', '139', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('140', '140', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('141', '141', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('142', '142', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('143', '143', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('144', '144', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('145', '145', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('146', '146', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('147', '147', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('148', '148', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('149', '149', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('150', '150', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('151', '151', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('152', '152', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('153', '153', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('154', '154', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('155', '155', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('156', '156', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('157', '157', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('158', '158', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('159', '159', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('160', '160', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('161', '161', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('162', '162', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('163', '163', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('164', '164', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('165', '165', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('166', '166', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('167', '167', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('168', '168', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('169', '169', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('170', '170', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('171', '171', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('172', '172', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('173', '173', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('174', '174', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('175', '175', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('176', '176', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('177', '177', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('178', '178', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('179', '179', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('180', '180', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('181', '181', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('182', '182', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('183', '183', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('184', '184', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('185', '185', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('186', '186', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('187', '187', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('188', '188', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('189', '189', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('190', '190', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('191', '191', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('192', '192', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('193', '193', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('194', '194', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('195', '195', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('196', '196', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('197', '197', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('198', '198', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('199', '199', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('200', '200', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('201', '201', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('202', '202', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('203', '203', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('204', '204', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('205', '205', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('206', '206', '1', '2015-08-08 22:16:18');
INSERT INTO `userrole` VALUES ('207', '207', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('208', '208', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('209', '209', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('210', '210', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('211', '211', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('212', '212', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('213', '213', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('214', '214', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('215', '215', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('216', '216', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('217', '217', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('218', '218', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('219', '219', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('220', '220', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('221', '221', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('222', '222', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('223', '223', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('224', '224', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('225', '225', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('226', '226', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('227', '227', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('228', '228', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('229', '229', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('230', '230', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('231', '231', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('232', '232', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('233', '233', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('234', '234', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('235', '235', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('236', '236', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('237', '237', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('238', '238', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('239', '239', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('240', '240', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('241', '241', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('242', '242', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('243', '243', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('244', '244', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('245', '245', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('246', '246', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('247', '247', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('248', '248', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('249', '249', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('250', '250', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('251', '251', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('252', '252', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('253', '253', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('254', '254', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('255', '255', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('256', '256', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('257', '257', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('258', '258', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('259', '259', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('260', '260', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('261', '261', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('262', '262', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('263', '263', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('264', '264', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('265', '265', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('266', '266', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('267', '267', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('268', '268', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('269', '269', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('270', '270', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('271', '271', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('272', '272', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('273', '273', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('274', '274', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('275', '275', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('276', '276', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('277', '277', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('278', '278', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('279', '279', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('280', '280', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('281', '281', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('282', '282', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('283', '283', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('284', '284', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('285', '285', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('286', '286', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('287', '287', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('288', '288', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('289', '289', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('290', '290', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('291', '291', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('292', '292', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('293', '293', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('294', '294', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('295', '295', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('296', '296', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('297', '297', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('298', '298', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('299', '299', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('300', '300', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('301', '301', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('302', '302', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('303', '303', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('304', '304', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('305', '305', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('306', '306', '1', '2015-08-08 22:19:55');
INSERT INTO `userrole` VALUES ('307', '307', '3', '2015-08-09 15:16:00');
INSERT INTO `userrole` VALUES ('308', '308', '3', '2015-08-09 15:18:41');
INSERT INTO `userrole` VALUES ('309', '309', '3', '2015-08-11 09:56:42');
INSERT INTO `userrole` VALUES ('310', '310', '3', '2015-08-11 09:56:42');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `telphone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `operationTime` varchar(30) DEFAULT NULL COMMENT '生效时间',
  `expireTime` varchar(30) DEFAULT NULL COMMENT '过期时间',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `isSaved` bigint(20) DEFAULT '7' COMMENT '是否保存了',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18280338034', '123456', '2015-05-23 19:12:48', '2015-09-23 19:13:01', null, '7');
INSERT INTO `users` VALUES ('5', 'user1', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13699454591', null, '2015-06-06 22:16:12', '2016-06-06 22:16:12', '2015-07-06 22:16:51', '7');
INSERT INTO `users` VALUES ('8', 'administrator', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-06-08 09:40:46', '2016-06-08 09:40:46', '2015-07-08 09:41:13', '7');
INSERT INTO `users` VALUES ('10', '12', 'C20AD4D76FE97759AA27A0C99BFF6710', null, null, null, '2015-06-08 09:40:46', '2016-06-08 09:40:46', '2015-07-08 09:41:23', '7');
INSERT INTO `users` VALUES ('16', 'nihao', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-06-08 09:45:27', '2016-06-08 09:45:27', '2015-07-08 09:45:38', '7');
INSERT INTO `users` VALUES ('19', 'wj', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18081032712', null, '2015-06-14 19:52:45', '2026-06-14 00:00:00', '2015-07-14 19:52:15', '7');
INSERT INTO `users` VALUES ('23', 'zjr1', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13880807506', null, '2015-06-14 22:00:06', '2016-06-14 22:00:06', '2015-07-14 22:00:00', '7');
INSERT INTO `users` VALUES ('24', 'zjr2', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13880807506', null, '2015-06-15 09:08:16', '2016-06-15 09:08:16', '2015-07-15 09:08:24', '7');
INSERT INTO `users` VALUES ('36', 'ae6409874', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-06-26 13:37:54', '2016-06-26 13:37:54', '2015-07-26 13:32:49', '7');
INSERT INTO `users` VALUES ('40', '1234', '81DC9BDB52D04DC20036DBD8313ED055', null, '13880807506', null, '2015-06-26 14:18:50', '2016-06-26 14:18:50', '2015-07-26 14:13:45', '7');
INSERT INTO `users` VALUES ('41', 'xcz', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13688195630', null, '2015-06-26 14:18:50', '2016-06-26 14:18:50', '2015-07-26 14:13:45', '7');
INSERT INTO `users` VALUES ('43', 'pyy', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18215642085', null, '2015-07-01 00:46:21', '2016-07-01 00:46:21', '2015-08-01 00:46:09', '7');
INSERT INTO `users` VALUES ('44', 'lx', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13618027395', null, '2015-07-01 00:46:21', '2016-07-01 00:46:21', '2015-08-01 00:46:09', '7');
INSERT INTO `users` VALUES ('45', 'lr', 'E10ADC3949BA59ABBE56E057F20F883E', null, '15308085182', null, '2015-07-01 00:47:59', '2016-07-01 00:47:59', '2015-08-01 00:47:51', '7');
INSERT INTO `users` VALUES ('46', '0a647e6d5', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13908207814', null, '2015-07-01 00:47:59', '2016-07-01 00:47:59', '2015-08-01 00:47:51', '7');
INSERT INTO `users` VALUES ('49', 'f1a650887', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 14:47:46', '2016-07-01 14:47:46', '2015-08-01 14:49:30', '7');
INSERT INTO `users` VALUES ('51', 'ad475058d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('53', '89aab3c63', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('54', '47ebff20c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('55', 'ljh2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('56', '914413440', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('57', 'wj1', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18280338033', null, '2015-07-01 15:10:20', '2016-07-01 15:10:20', '2015-08-01 15:11:47', '7');
INSERT INTO `users` VALUES ('58', '313ee7508', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('59', 'wj1', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18280338033', null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('60', '43b14ce9f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('61', 'ljh1', 'E10ADC3949BA59ABBE56E057F20F883E', null, '18428389866', null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('62', 'ef9863446', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('63', 'ljh', 'E10ADC3949BA59ABBE56E057F20F883E', '李加辉', '18428389866', null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('64', 'ea0d2166f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-01 15:11:31', '2016-07-01 15:11:31', '2015-08-01 15:12:55', '7');
INSERT INTO `users` VALUES ('101', 'zjr', 'E10ADC3949BA59ABBE56E057F20F883E', null, '13880807506', null, '2015-07-05 14:45:43', '2016-07-05 14:45:43', '2015-08-05 14:46:59', '7');
INSERT INTO `users` VALUES ('102', '23a4d0b4e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-05 14:49:26', '2016-07-05 14:49:26', '2015-08-05 14:49:33', '7');
INSERT INTO `users` VALUES ('103', 'test123', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-05 14:50:12', '2016-07-05 14:50:12', '2015-08-05 14:50:34', '7');
INSERT INTO `users` VALUES ('104', 'f3b9a13b2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-07 16:25:19', '2016-07-07 16:25:19', '2015-08-07 16:23:32', '7');
INSERT INTO `users` VALUES ('105', 'icework', '656B093A2612E8D3C0A51EFB83A138EB', null, null, null, '2015-07-07 17:00:55', '2016-07-07 17:00:55', '2015-08-07 16:59:08', '7');
INSERT INTO `users` VALUES ('106', 'c83d2f75b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-07 17:01:05', '2016-07-07 17:01:05', '2015-08-07 16:59:38', '7');
INSERT INTO `users` VALUES ('107', '1e5f8d858', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('108', '9af1db0c2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('109', '07f4775a8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('110', 'a9fa4b341', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('111', 'ffa263954', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('112', '742219ef4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('113', '8a762f398', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('114', '44f157c63', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('115', '97d4797ce', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('116', '2fbe755c9', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('117', '027d1fefc', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('118', 'de48f4448', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('119', '58b716a19', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('120', '10016983a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('121', '442db36e4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('122', '14dafedec', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('123', 'e597e812b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('124', 'b6b17cd20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('125', 'c1c664320', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('126', 'e47a478c4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('127', '004e639dc', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('128', 'b29a4069b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('129', '76ce3dc79', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('130', 'e17d041b1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('131', 'c71716208', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('132', 'dfd039819', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('133', 'e5185541c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('134', 'a4d9d3692', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('135', 'd48a8a8a0', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('136', 'a6b474cea', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('137', 'e02eee00e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('138', '0791d1d84', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('139', '7c436e052', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('140', '9b3de843c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('141', 'a5ef824e8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('142', '3035d5d44', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('143', '13e3b046c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('144', '4adb292a6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('145', 'ac33a12ba', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('146', 'd8c528eec', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('147', '84b615422', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('148', 'd40a325ad', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('149', '07c799c90', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('150', '6129420cc', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('151', '3bd60f7aa', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('152', 'b370edf62', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('153', 'fa292b56c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('154', 'bae5ca085', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('155', 'ab08eb3b4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('156', 'ef57de6f8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('157', 'cc3293c12', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('158', '17311bff9', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('159', 'c8296a854', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('160', 'f909a3197', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('161', '08d353290', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('162', '5f0c57be5', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('163', '13b3a394c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('164', 'df984cf5e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('165', '28c9cf6aa', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('166', 'b802f9990', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('167', '5d56d1711', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('168', 'adb6522d6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('169', '2d2531ddf', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('170', '79e1f6c82', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('171', '1446e8cb6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('172', '0100bc710', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('173', 'e038c621e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('174', '3bc84af18', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('175', 'd3ea790e1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('176', 'e215de8a7', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('177', 'f1d1f5210', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('178', '4fb1fb1cd', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('179', '7573bcf4e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('180', 'daf8011ff', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('181', 'daffdbd93', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('182', '68f304dbb', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('183', '7d073e3a1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('184', 'a729a38c8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('185', 'b44e20c21', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('186', '3b42a7684', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('187', 'd612edb1e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('188', '009704bdd', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('189', 'b4a6d6b8a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('190', '47fed4003', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('191', 'c3e89219b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('192', '4e4d25b21', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('193', 'a0b75701a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('194', '32bf20527', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('195', '47d88fed9', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('196', 'aa5b1d973', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('197', '000518062', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('198', 'fb54be2a0', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('199', 'c2a42a15c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('200', '2d830c4f6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('201', 'e08a0d272', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('202', 'c02aed6ed', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('203', '30e582cbd', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('204', '902c545b6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('205', '5947461e3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('206', '57904ca3d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:15:07', '2016-07-08 22:15:07', '2015-08-08 22:16:18', '7');
INSERT INTO `users` VALUES ('207', 'ccf771796', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('208', '39bbcf02a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('209', 'db4798356', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('210', '286a9b639', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('211', '4fa0e2aa4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('212', '080fdcc7f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('213', '9f6c4ecf1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('214', '447792293', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('215', '2255b6c1d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('216', 'adb778130', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('217', 'fae637b4d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('218', '34bef5514', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('219', '9f83a6855', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('220', '2140985b8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('221', '320727a49', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('222', '2bd5802b6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('223', 'a65a2a978', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('224', 'bf7504c43', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('225', 'c9e670fcc', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('226', 'cf72d829a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('227', 'dcef88c86', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('228', 'f4d250ea2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('229', 'a6a089d47', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('230', '7b269c876', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('231', 'a1d9bf650', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('232', '1bd02720f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('233', 'aafc6f448', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('234', '4dad3960b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('235', '34e6cf442', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('236', '7b90d2a56', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('237', '3a3768f90', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('238', '315d22d70', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('239', '1ff362574', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('240', '3f9fc8665', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('241', '455f2a8be', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('242', 'b1bc7186a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('243', '298d1d2b5', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('244', 'c52ddb350', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('245', 'd6cf7c28a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('246', 'fca9145f1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('247', '05768f63a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('248', '7dc7cfd57', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('249', '381f39091', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('250', 'f5114b043', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('251', '5855dbe1e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('252', 'af855a91d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('253', '4b09a19b3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('254', '1b3db3ad4', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('255', '591aa84f8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('256', '0bfd90f08', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('257', '5b1748b0c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('258', 'efe896ecf', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('259', '5ae54208a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('260', 'ecfc8da66', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('261', '78274ace0', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('262', '9b2d844dc', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('263', 'fb5f1d90f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('264', 'ceec6c27e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('265', '411abe68e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('266', 'd3a6cf77f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('267', 'c20770a2b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('268', 'f28937a84', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('269', 'eef6c808e', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('270', '918b6394b', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('271', '73f1989e8', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('272', 'e04140c8c', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('273', 'e311de7c5', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('274', 'b1382634d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('275', '9750264ea', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('276', '513da8c69', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('277', '5435e1f58', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('278', 'bece138a9', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('279', '02b293d76', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('280', '0b02c01d2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('281', '713e4e509', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('282', 'bf76e28e2', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('283', '92fd29196', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('284', '5bdb0ee1a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('285', '6d3613f5f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('286', 'a2f9abfa5', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('287', 'ba07d9b93', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('288', '5f607d65f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('289', '49926ead6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('290', '110d98bc3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('291', '7bee28a58', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('292', '3a942467d', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('293', '5988f6643', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('294', '348adbbc1', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('295', 'a5917a820', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('296', '4b29197bf', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('297', 'b73c5587f', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('298', '0e515ead3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('299', 'c5f7eabaa', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('300', 'cd5d2acf9', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('301', '0e2ec71b7', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('302', '5707849fa', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('303', '079c553a3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('304', '4e9f08861', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('305', 'fb073c80a', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('306', 'fcd6badb3', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-08 22:20:05', '2016-07-08 22:20:05', '2015-08-08 22:19:55', '7');
INSERT INTO `users` VALUES ('307', 'change', 'C33367701511B4F6020EC61DED352059', 'change', '13688195630', null, '2015-07-09 15:15:34', '2016-07-09 15:15:34', '2015-08-09 15:16:00', '7');
INSERT INTO `users` VALUES ('308', '5e50babf0', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-09 15:18:33', '2016-07-09 15:18:33', '2015-08-09 15:18:41', '7');
INSERT INTO `users` VALUES ('309', '4b4791193', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-11 09:55:11', '2016-07-11 09:55:11', '2015-08-11 09:56:42', '7');
INSERT INTO `users` VALUES ('310', '3297512a6', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, '2015-07-11 09:55:11', '2016-07-11 09:55:11', '2015-08-11 09:56:42', '7');

-- ----------------------------
-- Table structure for userterminal
-- ----------------------------
DROP TABLE IF EXISTS `userterminal`;
CREATE TABLE `userterminal` (
  `id` bigint(20) NOT NULL COMMENT '登陆终端id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `macAddress` varchar(50) DEFAULT NULL COMMENT 'mac地址',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK87F78347434396E` (`userid`),
  CONSTRAINT `FK87F78347434396E` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆终端对象';

-- ----------------------------
-- Records of userterminal
-- ----------------------------
INSERT INTO `userterminal` VALUES ('24', '5', '49F9531B-414A-4163-8106-6B15D3AE0855', '2015-07-08 09:11:11');
INSERT INTO `userterminal` VALUES ('37', '8', '08:00:27:c0:c8:dd', '2015-07-13 17:19:49');
INSERT INTO `userterminal` VALUES ('38', '8', 'F04EDD32-A84D-42A4-9140-29DD7377A93D', '2015-07-13 17:35:42');
INSERT INTO `userterminal` VALUES ('51', '23', null, '2015-07-15 19:21:07');
INSERT INTO `userterminal` VALUES ('55', '10', '62331964-3CCB-427E-9FAD-8E68C42856E6', '2015-07-24 08:51:23');
INSERT INTO `userterminal` VALUES ('56', '23', '62331964-3CCB-427E-9FAD-8E68C42856E6', '2015-07-24 15:26:50');
INSERT INTO `userterminal` VALUES ('58', '23', '727431C6-4B67-4466-B941-7D8FBA3BB59E', '2015-07-26 13:14:03');
INSERT INTO `userterminal` VALUES ('62', '24', 'AEC08080-123B-49DE-B885-1221026B8767', '2015-07-26 13:28:00');
INSERT INTO `userterminal` VALUES ('63', '24', '727431C6-4B67-4466-B941-7D8FBA3BB59E', '2015-07-26 13:42:56');
INSERT INTO `userterminal` VALUES ('71', '40', '6AB4F533-03CE-46A7-999F-B7931620F201', '2015-07-26 14:15:26');
INSERT INTO `userterminal` VALUES ('72', '40', '1BA344C6-7380-4021-A1FF-BD7FA7FD0DD5', '2015-07-26 14:15:47');
INSERT INTO `userterminal` VALUES ('73', '5', '45A19C34-9E7B-4BC1-AA77-F3A46937F46A', '2015-07-26 16:19:03');
INSERT INTO `userterminal` VALUES ('74', '5', '45A19C34-9E7B-4BC1-AA77-F3A46937F46A', '2015-07-26 16:19:16');
INSERT INTO `userterminal` VALUES ('77', '1', 'B24436CA-313D-43B0-B513-7DBACFFE9ECF', '2015-07-27 16:58:06');
INSERT INTO `userterminal` VALUES ('78', '40', '63043EB9-B69D-4329-A146-93ACC208F468', '2015-07-27 18:01:55');
INSERT INTO `userterminal` VALUES ('79', '1', '30725BA6-B94A-4D6D-97E8-273716F4CD57', '2015-07-27 19:33:54');
INSERT INTO `userterminal` VALUES ('85', '19', '8D6DA686-5AFB-456E-89DA-4AC410568D9C', '2015-07-31 23:38:51');
INSERT INTO `userterminal` VALUES ('86', '24', 'F6C04696-631F-4607-B4D0-934E8D48552F', '2015-08-01 00:45:33');
INSERT INTO `userterminal` VALUES ('91', '45', 'F9633D2E-CC22-4A22-A59B-73AB37B21C85', '2015-08-01 10:46:46');
INSERT INTO `userterminal` VALUES ('92', '45', 'A995CB6D-FBBF-4AC8-A268-E2387819FD7E', '2015-08-01 10:48:36');
INSERT INTO `userterminal` VALUES ('93', '46', 'F9633D2E-CC22-4A22-A59B-73AB37B21C85', '2015-08-01 11:24:31');
INSERT INTO `userterminal` VALUES ('94', '46', 'BDF3A123-C77F-423E-B512-7DE968CC2F92', '2015-08-01 11:25:36');
INSERT INTO `userterminal` VALUES ('95', '46', '37946B24-E8D0-4BB0-A50F-CD229ADDE553', '2015-08-01 11:28:12');
INSERT INTO `userterminal` VALUES ('96', '45', 'D75B9FAB-51BE-4D68-9DD0-8BD220BB9A48', '2015-08-01 11:28:38');
INSERT INTO `userterminal` VALUES ('97', '43', null, '2015-08-01 11:32:55');
INSERT INTO `userterminal` VALUES ('98', '43', 'BDF3A123-C77F-423E-B512-7DE968CC2F92', '2015-08-01 11:33:55');
INSERT INTO `userterminal` VALUES ('99', '49', 'BDF3A123-C77F-423E-B512-7DE968CC2F92', '2015-08-01 14:49:49');
INSERT INTO `userterminal` VALUES ('100', '49', 'F9633D2E-CC22-4A22-A59B-73AB37B21C85', '2015-08-01 14:50:12');
INSERT INTO `userterminal` VALUES ('101', '43', 'F9633D2E-CC22-4A22-A59B-73AB37B21C85', '2015-08-01 14:51:41');
INSERT INTO `userterminal` VALUES ('119', '53', '12345678', '2015-08-05 10:54:48');
INSERT INTO `userterminal` VALUES ('120', '44', '12345678', '2015-08-05 11:37:34');
INSERT INTO `userterminal` VALUES ('121', '44', 'BC7E24A4-EB55-411E-A383-432B90B37D5D', '2015-08-05 11:48:51');
INSERT INTO `userterminal` VALUES ('122', '101', 'C39D1DDE-A78A-420A-8F0B-A39EEF07B121', '2015-08-05 14:48:35');
INSERT INTO `userterminal` VALUES ('123', '103', '12345678', '2015-08-05 14:52:46');
INSERT INTO `userterminal` VALUES ('124', '103', '30725BA6-B94A-4D6D-97E8-273716F4CD57', '2015-08-06 15:22:47');
INSERT INTO `userterminal` VALUES ('125', '101', '30725BA6-B94A-4D6D-97E8-273716F4CD57', '2015-08-06 15:35:31');
INSERT INTO `userterminal` VALUES ('126', '59', '30725BA6-B94A-4D6D-97E8-273716F4CD57', '2015-08-06 15:36:29');
INSERT INTO `userterminal` VALUES ('127', '57', '30725BA6-B94A-4D6D-97E8-273716F4CD57', '2015-08-06 15:41:27');
INSERT INTO `userterminal` VALUES ('128', '63', '12345678', '2015-08-06 15:47:54');
INSERT INTO `userterminal` VALUES ('129', '103', 'B24436CA-313D-43B0-B513-7DBACFFE9ECF', '2015-08-06 15:51:12');
INSERT INTO `userterminal` VALUES ('130', '101', '7c:1d:d9:4d:aa:87', '2015-08-06 22:01:34');
INSERT INTO `userterminal` VALUES ('131', '57', '7c:1d:d9:4d:aa:87', '2015-08-06 22:03:05');
INSERT INTO `userterminal` VALUES ('132', '57', '9c:c1:72:01:fd:44', '2015-08-06 22:04:30');
INSERT INTO `userterminal` VALUES ('133', '1', 'F11D5FFF-3B99-45F4-A576-FFD7320E731D', '2015-08-06 22:29:23');
INSERT INTO `userterminal` VALUES ('134', '19', '9c:c1:72:01:fd:44', '2015-08-07 16:03:49');
INSERT INTO `userterminal` VALUES ('135', '55', '9c:c1:72:01:fd:44', '2015-08-07 17:01:18');
INSERT INTO `userterminal` VALUES ('136', '55', '12345678', '2015-08-07 22:08:59');
INSERT INTO `userterminal` VALUES ('138', '63', '9c:c1:72:01:fd:44', '2015-08-08 09:12:08');
INSERT INTO `userterminal` VALUES ('139', '44', '7DEF8E8A-54B1-44E3-91CE-1979B7E3C157', '2015-08-08 22:14:24');
INSERT INTO `userterminal` VALUES ('143', '16', '80E650182AF2', '2015-08-08 22:28:59');
INSERT INTO `userterminal` VALUES ('150', '41', null, '2015-08-09 16:19:46');
INSERT INTO `userterminal` VALUES ('151', '41', null, '2015-08-09 16:43:27');
INSERT INTO `userterminal` VALUES ('152', '41', null, '2015-08-09 16:48:11');
INSERT INTO `userterminal` VALUES ('155', '307', null, '2015-08-09 17:17:45');
INSERT INTO `userterminal` VALUES ('156', '307', '80E650182AF2', '2015-08-09 17:18:21');
INSERT INTO `userterminal` VALUES ('157', '16', 'F9633D2E-CC22-4A22-A59B-73AB37B21C85', '2015-08-10 16:11:14');
INSERT INTO `userterminal` VALUES ('158', '16', '12345678', '2015-08-10 16:11:23');
INSERT INTO `userterminal` VALUES ('159', '307', 'BFF3EE8B-4F06-40BE-BA85-75B4EB312DEB', '2015-08-10 22:05:30');
INSERT INTO `userterminal` VALUES ('160', '19', 'BC7E24A4-EB55-411E-A383-432B90B37D5D', '2015-08-10 22:09:46');
INSERT INTO `userterminal` VALUES ('161', '105', 'BC7E24A4-EB55-411E-A383-432B90B37D5D', '2015-08-10 22:11:27');
INSERT INTO `userterminal` VALUES ('162', '105', 'BFF3EE8B-4F06-40BE-BA85-75B4EB312DEB', '2015-08-10 22:29:32');
INSERT INTO `userterminal` VALUES ('163', '63', '70F8AE81-3D79-4A16-8702-6E73B304D147', '2015-08-11 16:40:04');
INSERT INTO `userterminal` VALUES ('164', '55', '70F8AE81-3D79-4A16-8702-6E73B304D147', '2015-08-11 16:48:18');
INSERT INTO `userterminal` VALUES ('165', '61', null, '2015-08-11 18:35:19');
INSERT INTO `userterminal` VALUES ('166', '61', '9c:c1:72:01:fd:44', '2015-08-11 18:36:32');
INSERT INTO `userterminal` VALUES ('167', '61', null, '2015-08-13 22:31:46');

-- ----------------------------
-- View structure for viewmonitor
-- ----------------------------
DROP VIEW IF EXISTS `viewmonitor`;
CREATE ALGORITHM=UNDEFINED DEFINER=`baby`@`%` SQL SECURITY DEFINER  VIEW `viewmonitor` AS select `m`.`id` AS `monitorId`,`m`.`name` AS `monitorName`,`m`.`sn` AS `sn`,concat(`m`.`beginWatchTime`,'-',`m`.`endWatchTime`) AS `timeQuantum`,`ka`.`kindergartenId` AS `kindergartenId`,`k`.`name` AS `kindergartenName`,`ka`.`id` AS `areaId`,`ka`.`name` AS `areaName`,`pka`.`id` AS `pkaId`,(select `glo`.`NUMBERS` from `globalvariable` `glo` where (`glo`.`ID` = `m`.`isSaved`)) AS `isSaved` from (((`monitor` `m` left join `kindergartenarea` `ka` on((`ka`.`id` = `m`.`areaId`))) left join `kindergartenarea` `pka` on((`pka`.`id` = `ka`.`pid`))) left join `kindergarten` `k` on((`k`.`id` = `ka`.`kindergartenId`))) ; ;

-- ----------------------------
-- View structure for viewuserarea
-- ----------------------------
DROP VIEW IF EXISTS `viewuserarea`;
CREATE ALGORITHM=UNDEFINED DEFINER=`baby`@`%` SQL SECURITY DEFINER  VIEW `viewuserarea` AS select `ua`.`id` AS `userAreaId`,`u`.`password` AS `password`,`u`.`id` AS `userId`,`u`.`username` AS `userName`,`k`.`id` AS `kindergartenId`,`k`.`name` AS `kindergartenName`,`ka`.`id` AS `areaId`,`ka`.`name` AS `areaName`,`u`.`telphone` AS `telphone`,`u`.`operationTime` AS `operationTime`,`u`.`expireTime` AS `expireTime`,`r`.`id` AS `roleId`,`r`.`comments` AS `roleName`,`u`.`createTime` AS `userCreateTime`,`pka`.`id` AS `pkaId`,(select `glo`.`NUMBERS` from `globalvariable` `glo` where (`glo`.`ID` = `u`.`isSaved`)) AS `isSaved` from (((((((`users` `u` left join `userarea` `ua` on((`u`.`id` = `ua`.`userId`))) left join `kindergartenarea` `ka` on((`ka`.`id` = `ua`.`areaId`))) left join `kindergartenarea` `pka` on((`pka`.`id` = `ka`.`pid`))) left join `userkinder` `uk` on((`uk`.`userId` = `u`.`id`))) left join `kindergarten` `k` on(((`k`.`id` = `ka`.`kindergartenId`) or (`k`.`id` = `uk`.`kindergartenId`)))) left join `userrole` `ur` on((`ur`.`userid` = `u`.`id`))) left join `role` `r` on((`r`.`id` = `ur`.`roleid`))) ;
