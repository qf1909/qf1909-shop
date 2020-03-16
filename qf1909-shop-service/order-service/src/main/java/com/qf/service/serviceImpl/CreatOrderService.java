package com.qf.service.serviceImpl;


import com.qf.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatOrderService {

    @RabbitListener(queues = RabbitConstant.ORDER_DESC_QUEUE)
    public void creatOrder(List list){
        System.out.println("收到消息"+list);
    }

}
