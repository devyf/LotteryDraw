package com.fengye.springcloud.utils;

import com.fengye.springcloud.entities.CommonResult;
import com.fengye.springcloud.entities.Product;
import com.fengye.springcloud.entities.ResultCodeEnum;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/28 10:22
 */
public class LotteryUtil {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "靴子", (float) 0.2));
        products.add(new Product(2, "披风", (float) 0.25));
        products.add(new Product(3, "饰品", (float) 0.1));
        products.add(new Product(4, "双手剑", (float) 0.05));
        products.add(new Product(5, "金币袋", (float) 0.4));

//        Product product = luckyDraw(products);
//        System.out.println(product);
        Product discreteDraw = discreteDraw(products);
        System.out.println(discreteDraw);
    }

    /**
     * 抽奖设计接口一：方式一抽奖接口（中奖概率已设定为40%，不具有正态分布随机性）
     * 产生一个随机数，0-5为一等奖商品，6-15为二等奖商品，16-40为三等奖商品，41-100为谢谢惠顾
     * 在比较的时候，比较随机数（百分比）与获取商品的概率（百分比）的绝对值，40%以下的才中奖
     * 之后计算随机数与中奖概率的绝对值，选择绝对值相差最小的那个为中奖商品
     * @param products
     * @return
     */
    public static Product luckyDraw(List<Product> products) {
        //1.产生一个随机数
        int probabilityCount = 100;
        int randomNum = (int) (Math.random()* probabilityCount);
        //2.41-100表示不中奖
        if(randomNum > 40){
            return null;
        }

        Map<String, Product> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        products.forEach(p -> {
            int intValue = new BigDecimal(p.getWinRate() * 100).intValue();
            int absVal = Math.abs(randomNum - intValue);
            list.add(absVal);
        });

        Integer min = Collections.min(list);
        for (Product product : products) {
            int value = new BigDecimal(product.getWinRate() * 100).intValue();
            if(Math.abs(randomNum - value) == min){
                return product;
            }
        }
        return null;
    }

    /**
     * 抽奖接口二：离散算法，具有较好的正态分布随机性
     * 竟然1-20都是靴子，21-45都是披风，那抽象成小于等于20的是靴子，大于20且小于等于45是披风，
     * 就变成几个点[20,45,55,60,100]，然后也是从1到99随机取一个数R，按顺序在这些点进行比较，
     * 知道找到第一个比R大的数的下标，比一般算法减少占用空间，
     * 还可以采用二分法找出R，这样，预处理O(N)，随机数生成O(logN)，空间复杂度O(N)
     * @param products
     * @return
     */
    public static Product discreteDraw(List<Product> products){
        List<Integer> integers = products.stream()
                .map(product -> new BigDecimal(product.getWinRate() * 100).intValue())
                .collect(Collectors.toList());
        //1.划分区间,将概率划分为与概率值对应的几个点的概率区间
        Integer[] arr = new Integer[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            int sum = 0;
            for (int j = 0; j < i+1; j++) {
                sum += integers.get(j);
            }
            arr[i] = sum;
        }
        //2.最后arr就变成了0-100对应的商品的概率区间，如：[20,45,55,60,100]
        System.out.println("原抽奖商品的概率(%)为：" + integers);
        integers.forEach(System.out::println);
        //3.从1到99随机取一个数R，按照顺序在对这些点进行比较，找出第一个比R大的数的下标，这个下标对应就是数组中
        //的抽到的商品的下标值
        int probabilityCount = 100;  //产生1-100的下标值
        int randomNum = (int) (Math.random()* probabilityCount);  //生成0-100的随机数
        int maxIndex = getMaxIndex(arr, randomNum);
        //4.根据索引值去查询出中奖商品
        Product target = maxIndex == -1 ? null : products.get(maxIndex);
        return target;
    }

    /**
     *
     * @param arr  传入的分区后的0-100区间的概率数组arr
     * @param randomNum  随机数
     * @return  成功返回索引值，不成功返回-1
     */
    private static int getMaxIndex(Integer[] arr, int randomNum) {
        for (int index = 0; index < arr.length; index++) {
            if(arr[index] >= randomNum){
                return index;
            }
        }

        return -1;
    }
}
