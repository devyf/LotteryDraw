package com.fengye.springcloud.service;

import com.fengye.springcloud.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/29 22:19
 */
public interface ProductService {
    Product getProductById(Integer id);

    List<Product> getProductList();

    Integer insertProduct(Product product);
}
