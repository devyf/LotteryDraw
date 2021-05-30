package com.fengye.springcloud.utils;

import com.fengye.springcloud.entities.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/27 16:39
 */
public class ProductLotteryTest {
    public static void main(String[] args) {

    }

    /**
     * 1中奖了11次，概率: 0.05
     * 2中奖了22次，概率: 0.09
     * 3中奖了5次，概率: 0.02
     * 4中奖了111次，概率: 0.48
     * 5中奖了66次，概率: 0.28
     * 6中奖了18次，概率: 0.08
     */

    /**
     * 添加商品接口
     * @param productName  商品名称
     * @param lotteryPercent 中奖率
     */
    public void insertProduct(String productName, float lotteryPercent){
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, productName, lotteryPercent));
    }

    /**
     * 抽奖接口：根据抽奖次数及抽奖商品的概率进行返回抽中的商品
     * @return
     */
    public List<Product> lotteryProduct(Integer allAmount, List<Product> productList){
        return new Lottery().lottery(productList, allAmount);
    }
}
