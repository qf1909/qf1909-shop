package com.qf.config;

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_TOPIC_EXCHANGE);
    }
}
