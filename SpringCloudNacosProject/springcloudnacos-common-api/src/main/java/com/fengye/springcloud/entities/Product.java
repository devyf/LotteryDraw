package com.fengye.springcloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("mk_product")  //用于指定表名，表名与实体名称不一致时必须使用
public class Product
{
    @ApiModelProperty("主键id")
    //注意：表主键使用@TableId
    //value表示数据库中实际列名，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "productId", type = IdType.AUTO)  //type指定自增策略
    private Integer id;   //主键id
    @ApiModelProperty("商品名称")
    //指定实体类中属性与表中列名的对应关系
    @TableField(value = "pname")
    private String productName;  //商品名称
    @ApiModelProperty("中奖率")
    //@TableField("winrate")
    // 如果开启了map-underscore-to-camel-case: true 自动驼峰命名，
    // 那么这里会将winRate修改为win_rate进行sql封装查询，有两种方式处理不对应问题：
    //1.设置@TableField("winrate")；2.修改map-underscore-to-camel-case: false（不使用驼峰命名方式）
    //@TableField(value = "winrate", exist = true) //exist表明数据库表中有没有对应列存在，默认true表示存在
    private float winRate;  //中奖率 -- 请用户输入小数点后两位
}
