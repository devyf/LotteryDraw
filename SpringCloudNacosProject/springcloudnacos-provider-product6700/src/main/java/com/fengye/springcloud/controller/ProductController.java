package com.fengye.springcloud.controller;

import com.fengye.springcloud.entities.CommonResult;
import com.fengye.springcloud.entities.Product;
import com.fengye.springcloud.entities.ResultCodeEnum;
import com.fengye.springcloud.service.ProductService;
import com.fengye.springcloud.utils.LotteryUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/27 14:23
 */
@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 测试Nacos数据接口
     *
     * @return
     */
    @GetMapping(value = "/provider/test")
    public CommonResult<Product> getProductTest() {
        CommonResult<Product> result = new CommonResult<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(new Product(1, "iphone12Max", (float) 0.05));
        result.setException(null);
        result.setMsg("测试数据接口");
        result.setUrl(null);
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据id查询商品返回商品接口
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/provider/product/{id}")
    public CommonResult<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new CommonResult<Product>(
                ResultCodeEnum.SUCCESS.getCode(),
                product,
                null,
                "根据id查询商品信息成功！商品名称为：" + product,
                true,
                null);
        }

        return new CommonResult<Product>(
            ResultCodeEnum.DATA_NOT_FOUND.getCode(),
            null,
            null,
            "根据id查询商品信息失败！",
            false,
            null);
    }

    /**
     * 获取所有商品
     *
     * @return
     */
    @GetMapping(value = "/provider/getAll")
    public CommonResult<List<Product>> getAllProducts() {
        List<Product> productList = productService.getProductList();
        if (CollectionUtils.isEmpty(productList)) {
            return new CommonResult<>(
                ResultCodeEnum.DATA_NOT_FOUND.getCode(),
                null,
                null,
                "查询商品列表信息失败！",
                false,
                null
            );
        }
        return new CommonResult<>(
            ResultCodeEnum.SUCCESS.getCode(),
            productList,
            null,
            "查询商品信息成功，所得到的的商品列表为：" + productList,
            true,
            null
        );
    }

    /**
     * 添加商品接口：使用Product参数进行JSON数据插入传递参数
     *
     * @param product
     * @return
     */
    @PostMapping(value = "/provider/insert")
    public Integer insertProduct(@RequestBody Product product) {
        //需要判断单个商品的概率是否大于100%
        int pVal = new BigDecimal(product.getWinRate() * 100).intValue();
        if(pVal > 100){
            return -2;
        }
        int sum = productService.getProductList()
                .stream()
                .map(p -> new BigDecimal(p.getWinRate() * 100).intValue()).mapToInt(p -> p).sum();

        int newRes = sum + pVal;
        //结果相加大于1，概率超过100%，返回-2，表示概率超过限制
        if(newRes > 100){
            return -2;
        }
        return productService.insertProduct(product);
    }

    /**
     * 抽奖接口：根据抽奖次数及抽奖商品的概率进行返回抽中的商品
     *
     * @return
     */
    @GetMapping(value = "/provider/luckyDraw")
    public CommonResult<Product> luckyDrawProduct() {
        List<Product> productList = productService.getProductList();
        Product product = LotteryUtil.discreteDraw(productList);
        if (product == null) {
            return new CommonResult<>(
                ResultCodeEnum.PARAMS_NULL.getCode(),
                null,
                null,
                "未抽取到商品，谢谢惠顾！",
                false,
                null
            );
        }

        return new CommonResult<>(
            ResultCodeEnum.SUCCESS.getCode(),
            product,
            null,
            "抽奖商品获取成功！抽到的商品名称为：" + product.getProductName(),
            true,
            null
        );
    }
}
