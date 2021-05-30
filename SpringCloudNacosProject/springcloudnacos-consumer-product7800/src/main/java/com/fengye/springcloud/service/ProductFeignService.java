package com.fengye.springcloud.service;

import com.fengye.springcloud.entities.CommonResult;
import com.fengye.springcloud.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 服务调用方接口
 * @Author: huang
 * @Date: 2021/5/27 15:09
 */
@Component
@FeignClient(value = "nacos-product-provider-6700")
public interface ProductFeignService {
    //测试集成Nacos服务接口
    @GetMapping(value = "/provider/test")
    public CommonResult<Product> getProductTest();

    //接口名与url地址与服务生产者接口名称相同
    @GetMapping(value = "/provider/product/{id}")
    public CommonResult<Product> getProductById(@PathVariable("id") Integer id);

    //获取所有的商品数据
    @GetMapping(value = "/provider/getAll")
    public CommonResult<List<Product>> getAllProducts();

    //编写一个添加商品接口:包含商品名称和中奖率
    @PostMapping(value = "/provider/insert")
    public Integer insertProduct(@RequestBody Product product);

    //编写一个抽奖接口,对添加的商口进行抽奖返回中奖商品
    @GetMapping(value = "/provider/luckyDraw")
    public CommonResult<Product> luckyDrawProduct();
}
