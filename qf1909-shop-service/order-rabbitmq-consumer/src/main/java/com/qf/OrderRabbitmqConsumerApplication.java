package com.qf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.mapper")
public class OrderRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderRabbitmqConsumerApplication.class, args);
    }

}
