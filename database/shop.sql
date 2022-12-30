/*
 Navicat MySQL Data Transfer

 Source Server         : newone
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 30/12/2022 15:04:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号',
  `no` int NULL DEFAULT NULL COMMENT '商品编号',
  `goodsname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `no`(`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------
INSERT INTO `goodsinfo` VALUES (2, 10002, '康师傅老坛酸菜牛肉面', 0, 2.50, '食品');
INSERT INTO `goodsinfo` VALUES (5, 10005, '鼠标', 299, 23.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (8, 10008, 'iPhone 13 proMax', 20, 8999.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (9, 10009, '美的冰箱', 15, 3399.00, '家用电器');
INSERT INTO `goodsinfo` VALUES (11, 10011, '按摩椅', 15, 599.00, '享受用品');
INSERT INTO `goodsinfo` VALUES (12, 10012, '李宁防晒衣', 20, 99.00, '服饰');
INSERT INTO `goodsinfo` VALUES (15, 10015, '鸿星尔克羽绒服', 2, 200.00, '服饰');
INSERT INTO `goodsinfo` VALUES (16, 10016, '珍珠项链', 8, 999.00, '首饰');
INSERT INTO `goodsinfo` VALUES (17, 10017, '钻石戒指', 15, 5200.00, '首饰');
INSERT INTO `goodsinfo` VALUES (18, 10018, '蓝宝石手链', 12, 3699.00, '首饰');
INSERT INTO `goodsinfo` VALUES (19, 10019, '黄金耳环', 20, 2299.00, '首饰');
INSERT INTO `goodsinfo` VALUES (20, 10020, '手镯', 22, 1299.00, '首饰');
INSERT INTO `goodsinfo` VALUES (21, 10021, '农夫山泉', 1200, 2.00, '食品');
INSERT INTO `goodsinfo` VALUES (22, 10022, '怡宝', 1300, 2.00, '食品');
INSERT INTO `goodsinfo` VALUES (23, 10023, '达利园小面包', 212, 15.00, '食品');
INSERT INTO `goodsinfo` VALUES (24, 10024, '康师傅红烧牛肉面', 1100, 3.00, '食品');
INSERT INTO `goodsinfo` VALUES (25, 10025, '云南白药牙膏', 200, 25.00, '洗漱用品');
INSERT INTO `goodsinfo` VALUES (26, 10026, '牙刷', 420, 12.00, '洗漱用品');
INSERT INTO `goodsinfo` VALUES (27, 10027, '沐浴露', 350, 78.00, '洗漱用品');
INSERT INTO `goodsinfo` VALUES (28, 10028, '毛巾', 250, 15.00, '洗漱用品');
INSERT INTO `goodsinfo` VALUES (29, 10029, '联想电脑', 50, 6999.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (32, 100030, '雀巢咖啡', 37, 78.00, '食品');
INSERT INTO `goodsinfo` VALUES (34, 100031, '适配器', 10, 35.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (35, 100032, '电脑', 21, 1999.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (45, 10010, '美的空调', 0, 2999.00, '家用电器');
INSERT INTO `goodsinfo` VALUES (48, 10001, '百事可乐', 1000, 3.00, '食品');
INSERT INTO `goodsinfo` VALUES (50, 10006, '衬衫', 68, 88.00, '服饰');
INSERT INTO `goodsinfo` VALUES (51, 10003, '飘柔洗发水', 399, 36.00, '洗漱用品');
INSERT INTO `goodsinfo` VALUES (54, 10007, '耳机', 83, 56.00, '数码产品');
INSERT INTO `goodsinfo` VALUES (56, 10013, '安踏短袖', 100, 89.00, '服饰');
INSERT INTO `goodsinfo` VALUES (57, 10004, '保温杯', 300, 28.00, '日用品');
INSERT INTO `goodsinfo` VALUES (58, 10014, '皮克态极拖鞋', 80, 139.00, '日用品');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `ShopName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名字',
  `ShopId` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `State` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `times` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('雀巢咖啡', '100030', '增加', '17', '2022-07-04 22:40:31', '食品');
INSERT INTO `logs` VALUES ('罗', '100050', '进货新商品', '11', '2022-07-05 09:17:49', '食品');
INSERT INTO `logs` VALUES ('雀巢咖啡', '商品编号', '生产库存不足商品', '20', '2022-07-05 09:18:30', '食品');
INSERT INTO `logs` VALUES ('适配器', '100031', '进货新商品', '10', '2022-07-05 09:30:48', '数码产品');
INSERT INTO `logs` VALUES ('电脑', '100032', '进货新商品', '19', '2022-07-05 09:40:56', '数码产品');
INSERT INTO `logs` VALUES ('电脑', '商品编号', '生产库存不足商品', '1', '2022-07-05 09:41:18', '数码产品');
INSERT INTO `logs` VALUES ('第三方都是', '100033', '进货新商品', '11', '2022-07-05 09:55:03', '食品');
INSERT INTO `logs` VALUES ('电脑', '商品编号', '生产库存不足商品', '1', '2022-07-05 09:55:19', '食品');
INSERT INTO `logs` VALUES ('111', '10100', '进货新商品', '20', '2022-07-05 15:59:32', '首饰');
INSERT INTO `logs` VALUES ('10002', '', '生产库存不足商品', '11', '2022-07-07-15-43-11', '');

-- ----------------------------
-- Table structure for soldout
-- ----------------------------
DROP TABLE IF EXISTS `soldout`;
CREATE TABLE `soldout`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号',
  `no` int NULL DEFAULT NULL COMMENT '商品编号',
  `goodsname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of soldout
-- ----------------------------

-- ----------------------------
-- Table structure for talk
-- ----------------------------
DROP TABLE IF EXISTS `talk`;
CREATE TABLE `talk`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `talk` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of talk
-- ----------------------------
INSERT INTO `talk` VALUES (1, 'WQE', '2022-07-07-09-53-47', '1');
INSERT INTO `talk` VALUES (2, 'fewrf', '2022-07-07-14-26-04', '');
INSERT INTO `talk` VALUES (3, 'wdw', '2022-07-07-14-29-52', '');
INSERT INTO `talk` VALUES (4, 'xcv', '2022-07-07-14-48-20', '1');
INSERT INTO `talk` VALUES (5, 'dwaf', '2022-07-07-14-52-29', '1');
INSERT INTO `talk` VALUES (6, 'sdas', '2022-07-07-14-59-33', '1');
INSERT INTO `talk` VALUES (7, 'swd', '2022-07-07-15-07-42', '123');
INSERT INTO `talk` VALUES (8, '什么时候上线？', '2022-07-07-16-24-08', '123');
INSERT INTO `talk` VALUES (9, 'fsaf', '2022-07-07-16-26-41', '123');
INSERT INTO `talk` VALUES (10, '地方', '2022-07-07-23-18-23', '555');
INSERT INTO `talk` VALUES (11, '对对对', '2022-07-07-23-24-58', '777');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `no` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `userpassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (14, 888, '0a113ef6b61820daa5611c870ed8d5ee', 'yes', '广州', '13599999');
INSERT INTO `user` VALUES (15, 777, 'f1c1592588411002af340cbaedd6fc33', 'yes', '广州', '135444444');
INSERT INTO `user` VALUES (16, 0, 'c6f057b86584942e415435ffb1fa93d4', NULL, NULL, NULL);
INSERT INTO `user` VALUES (17, 555, '15de21c670ae7c3f6f3f1f37029303c9', 'yes', '广州', '13788888');
INSERT INTO `user` VALUES (18, 444, '550a141f12de6341fba65b0ad0433500', NULL, NULL, NULL);
INSERT INTO `user` VALUES (19, 1, 'c4ca4238a0b923820dcc509a6f75849b', 'yes', '广州', '123456');
INSERT INTO `user` VALUES (20, 9, '45c48cce2e2d7fbdea1afc51c7c6ad26', NULL, NULL, NULL);
INSERT INTO `user` VALUES (21, 101, '202cb962ac59075b964b07152d234b70', NULL, NULL, NULL);
INSERT INTO `user` VALUES (22, 11, '6512bd43d9caa6e02c990b0a82652dca', 'yes', '广东广州', '13523456789');

SET FOREIGN_KEY_CHECKS = 1;
