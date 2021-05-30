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

 Date: 30/05/2021 22:40:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抽奖商品名称',
  `winrate` float(4, 2) NULL DEFAULT NULL COMMENT '中奖概率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '靴子', 0.20);
INSERT INTO `product` VALUES (2, '披风', 0.25);
INSERT INTO `product` VALUES (3, '饰品', 0.10);
INSERT INTO `product` VALUES (4, '双手剑', 0.05);
INSERT INTO `product` VALUES (5, '金币袋', 0.40);

SET FOREIGN_KEY_CHECKS = 1;
