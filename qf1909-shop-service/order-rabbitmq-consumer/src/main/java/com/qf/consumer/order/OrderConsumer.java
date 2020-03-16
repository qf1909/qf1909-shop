package com.qf.consumer.order;

import com.qf.constant.RabbitConstant;
import com.qf.entity.TOrder;
import com.qf.entity.TOrderdetail;
import com.qf.mapper.TOrderMapper;
import com.qf.mapper.TOrderdetailMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConsumer {

    @Autowired
    private TOrderMapper orderMapper;

    @Autowired
    private TOrderdetailMapper orderdetailMapper;

    @RabbitListener(queues = RabbitConstant.ORDER_DESC_QUEUE)
    public String process(List list){
        if (list == null || list.size()==0){
            return "没有订单";
        }
        TOrder order = (TOrder) list.get(1);
        TOrderdetail orderdetail = (TOrderdetail) list.get(2);
        orderMapper.createOrder(order);
        orderdetailMapper.creatOrderDetail(orderdetail);

        return "订单创建完成";
    }

}
