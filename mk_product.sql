/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 50525
 Source Host           : localhost:3306
 Source Schema         : nacosproduct

 Target Server Type    : MySQL
 Target Server Version : 50525
 File Encoding         : 65001

 Date: 02/06/2021 00:46:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mk_product
-- ----------------------------
DROP TABLE IF EXISTS `mk_product`;
CREATE TABLE `mk_product`  (
  `productId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抽奖商品名称',
  `winrate` float(4, 2) NULL DEFAULT NULL COMMENT '中奖概率',
  PRIMARY KEY (`productId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mk_product
-- ----------------------------
INSERT INTO `mk_product` VALUES (1, '靴子', 0.20);
INSERT INTO `mk_product` VALUES (2, '披风', 0.15);
INSERT INTO `mk_product` VALUES (3, '饰品', 0.10);
INSERT INTO `mk_product` VALUES (4, '双手剑', 0.05);
INSERT INTO `mk_product` VALUES (5, '金币袋', 0.28);
INSERT INTO `mk_product` VALUES (6, '麻痹戒指', 0.08);
INSERT INTO `mk_product` VALUES (7, '致命手镯', 0.07);

SET FOREIGN_KEY_CHECKS = 1;
