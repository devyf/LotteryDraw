package com.fengye.springcloud.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.fengye.springcloud.entities.CommonResult;
import com.fengye.springcloud.entities.Product;
import com.fengye.springcloud.entities.ResultCodeEnum;
import com.fengye.springcloud.service.ProductFeignService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/27 15:03
 */
@RestController
@Api(value = "抽奖接口演示",description = "SpringCloud Nacos测试API接口")
public class ProductConsumerController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ProductFeignService productFeignService;

    @ApiOperation(value = "获取所有抽奖商品信息", notes = "获取所有抽奖商品getAllProducts接口")
    @GetMapping(value = "/consumer/getAll")
    public CommonResult<List<Product>> getAllProducts(){
        CommonResult<List<Product>> allProducts = productFeignService.getAllProducts();
        String requestUrl = String.format("http://localhost:%s/consumer/getAll", serverPort);
        allProducts.setUrl(requestUrl);
        return allProducts;
    }

    @ApiOperation(value = "获取商品测试test接口", notes = "test接口")
    @GetMapping(value = "/test")
    public CommonResult<Product> getProductTest(){
        CommonResult<Product> productTest = productFeignService.getProductTest();
        String requestUrl = String.format("http://localhost:%s/test", serverPort);
        productTest.setUrl(requestUrl);
        return productTest;
    }

    @RequestMapping(value = "/consumer/getProductById/{id}")
    @ApiOperation(value = "获取对应id商品接口", notes = "根据商品id获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "商品id", required = true, dataType = "Integer"),
    })
    public CommonResult<Product> getProductById(@PathVariable("id") Integer id){
        CommonResult<Product> productRes = productFeignService.getProductById(id);
        String requestUrl = String.format("http://localhost:%s/consumer/getProductById/%s", serverPort, id);
        productRes.setUrl(requestUrl);
        return productRes;
    }

    @PostMapping(value = "/consumer/insert")
    @ApiOperation(value = "插入抽奖商品insert接口", notes = "插入商品接口，包含商品信息、商品抽奖概率")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="insert", name = "id", value = "商品", required = true, dataType = "Product"),
    })
    public CommonResult<Product> insertProduct(@RequestBody Product product){
        Integer integer = productFeignService.insertProduct(product);
        String requestUrl = String.format("http://localhost:%s//consumer/insert", serverPort);
        if(integer == -2){
            return new CommonResult<>(ResultCodeEnum.ERROR.getCode(),
                    null,
                    null,
                    "插入的中奖商品概率已超过100%，当前操作无效！",
                    false, requestUrl);
        }
        return new CommonResult<>(
                ResultCodeEnum.SUCCESS.getCode(),
                product,
                null,
                "插入商品成功，抽奖商品为：" + product,
                true, requestUrl);
    }

    //编写一个抽奖接口,对添加的商口进行抽奖返回中奖商品
    @GetMapping(value = "/consumer/luckyDraw")
    @ApiOperation(value = "抽奖接口", notes = "抽奖接口，根据概率返回中奖商品")
    public CommonResult<Product> luckyDrawProduct(){
        CommonResult<Product> commonRes = productFeignService.luckyDrawProduct();
        String requestUrl = String.format("http://localhost:%s//consumer/luckyDraw", serverPort);
        commonRes.setUrl(requestUrl);
        return commonRes;
    }
}
