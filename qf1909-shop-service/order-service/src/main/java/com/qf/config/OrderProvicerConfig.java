package com.qf.config;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProvicerConfig {


    @Bean
    public FanoutExchange getExchange(){
        return new  FanoutExchange("shop_order_exchange",true,false,null);
    }

    @Bean
    public Queue getQueue(){
        return new Queue("order_queue");
    }

    /**
     * f发布订阅
     * @return
     */
    @Bean
    public DirectExchange getDirectExchange(){
        return new  DirectExchange("my_direct_exchange");
    }

    @Bean
    public Queue getQueue1(){
        return new Queue("my_direct_queue");
    }



}
