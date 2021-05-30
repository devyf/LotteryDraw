package com.fengye.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/27 14:31
 */
@SpringBootApplication
@EnableDiscoveryClient
//主启动类上标注，在XxxMapper中可以省略@Mapper注解
@MapperScan("com.fengye.springcloud.mapper")
public class ProviderProductMain6700 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderProductMain6700.class, args);
    }
}
