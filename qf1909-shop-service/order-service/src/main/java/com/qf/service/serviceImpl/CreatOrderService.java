package com.qf.service.serviceImpl;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatOrderService {

    @RabbitListener(queues = "shop_order_exchange")
    public void creatOrder(List list){

        System.out.println("收到消息"+list);
    }

}
