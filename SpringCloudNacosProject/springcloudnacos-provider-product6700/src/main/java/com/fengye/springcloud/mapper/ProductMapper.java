package com.fengye.springcloud.mapper;

import com.fengye.springcloud.entities.Product;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/29 21:54
 */
//@Mapper
@Repository
public interface ProductMapper {
    @Select("select id,pname,winrate from product where id = #{id}")
    @Results(
        @Result(property = "productName", column = "pname")
    )
    Product getProductById(Integer id);

    @Select("select id,pname,winrate from product")
    @Results(
            @Result(property = "productName", column = "pname")
    )
    List<Product> getAllProducts();

    @Insert("insert into product(pname, winrate) values(#{productName}, #{winRate})")
    //返回主键字段id值
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertProduct(Product product);
}
