package com.qf.config;

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailRabbitConfig {

    @Bean
    public Queue getEmailQueue(){
        return new Queue(RabbitConstant.EMAIL_SEND_QUEUE);
    }

    @Bean
    public TopicExchange getEmailTopicExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_TOPIC_EXCHANGE,true,false);
    }

    @Bean
    public Binding getEmailBinding(Queue queue , TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("send-email");
    }
}
