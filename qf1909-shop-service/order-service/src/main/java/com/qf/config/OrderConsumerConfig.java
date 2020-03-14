package com.qf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConsumerConfig {

    @Bean
    public Queue getQueue(){
        return new Queue("my_direct_queue");
    }

    @Bean
    public FanoutExchange getExchange(){
        return new  FanoutExchange("my_fanout_exchange",true,false,null);
    }

    @Bean
    public Binding getBinding(Queue queue, FanoutExchange exchange){
        return  BindingBuilder.bind(queue).to(exchange);
    }

}
