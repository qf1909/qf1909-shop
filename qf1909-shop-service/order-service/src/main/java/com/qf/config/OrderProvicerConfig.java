package com.qf.config;


import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProvicerConfig {


    @Bean
    public FanoutExchange getExchange(){
        return new  FanoutExchange(RabbitConstant.ORDER_EXCHANGE,true,false,null);
    }

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConstant.ORDER_DESC_QUEUE);
    }
//
//    /**
//     * f发布订阅
//     * @return
//     */
//    @Bean
//    public DirectExchange getDirectExchange(){
//        return new  DirectExchange(RabbitConstant.ORDER_EXCHANGE);
//    }
//
//    @Bean
//    public Queue getQueue1(){
//        return new Queue("my_direct_queue");
//    }



}
