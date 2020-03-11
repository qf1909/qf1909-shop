package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductSearchWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSearchWebApplication.class, args);
    }

}
