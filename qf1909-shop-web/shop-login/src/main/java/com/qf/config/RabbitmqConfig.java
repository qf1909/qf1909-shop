package com.qf.config;


import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {


    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(RabbitConstant.SMS_TOPIC_EXCHANGE);
    }

}
