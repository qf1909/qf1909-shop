package com.qf.config;

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabitmqConfig {

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConstant.ORDER_DESC_QUEUE);
    }

    @Bean
    public FanoutExchange getExchange(){
        return new  FanoutExchange(RabbitConstant.ORDER_EXCHANGE,true,false,null);
    }

    @Bean
    public Binding getBinding(Queue queue, FanoutExchange exchange){
        return  BindingBuilder.bind(queue).to(exchange);
    }


}
