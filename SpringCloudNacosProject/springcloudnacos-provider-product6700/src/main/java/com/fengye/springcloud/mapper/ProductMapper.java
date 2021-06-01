package com.fengye.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengye.springcloud.entities.Product;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/29 21:54
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
