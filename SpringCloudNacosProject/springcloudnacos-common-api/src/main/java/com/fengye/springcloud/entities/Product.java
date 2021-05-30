package com.fengye.springcloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 抽奖商品
 * @Author: huang
 * @Date: 2021/5/27 11:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
public class Product
{
    @ApiModelProperty("主键id")
    private Integer id;   //主键id
    @ApiModelProperty("商品名称")
    private String productName;  //商品名称
    @ApiModelProperty("中奖率")
    private float winRate;  //中奖率 -- 请用户输入小数点后两位
}
