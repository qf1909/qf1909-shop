package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper.order")
public class ShopOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrderServiceApplication.class, args);
    }

}
