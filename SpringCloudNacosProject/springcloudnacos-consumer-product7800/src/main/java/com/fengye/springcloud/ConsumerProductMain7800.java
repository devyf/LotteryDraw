package com.fengye.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/27 15:07
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerProductMain7800 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerProductMain7800.class, args);
    }
}
