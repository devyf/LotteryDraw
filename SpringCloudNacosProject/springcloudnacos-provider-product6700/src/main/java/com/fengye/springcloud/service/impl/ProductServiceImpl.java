package com.fengye.springcloud.service.impl;

import com.fengye.springcloud.entities.Product;
import com.fengye.springcloud.mapper.ProductMapper;
import com.fengye.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/29 22:19
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getProductList() {
        return productMapper.selectList(null);
    }

    @Override
    public Integer insertProduct(Product product) {
        return productMapper.insert(product);
    }
}
