package com.qf.controller;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.bean.ResultBean;
import com.qf.constant.RabbitConstant;
import com.qf.service.IOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("createOrder")
    @ResponseBody
    public ResultBean createOrder(@RequestBody Order order, @RequestBody List<Orderdetail> orderdetailList){
        //插入订单数据
        orderService.createOrder(order);
        //TODO  rabbitmq  发消息插入订单详情数据
        rabbitTemplate.convertAndSend(RabbitConstant.ORDER_ADD_TOPIC_EXCHANGE,"create-order-detail",orderdetailList);
        //TODO rabbitmq  发消息减库存
        rabbitTemplate.convertAndSend(RabbitConstant.ORDER_ADD_TOPIC_EXCHANGE,"subtract-store",orderdetailList);
        return ResultBean.success("创建订单成功!");
    }
}
