package com.qf.handler;


import com.qf.bean.Orderdetail;
import com.qf.bean.ResultBean;
import com.qf.constant.RabbitConstant;
import com.qf.dto.OrderDetailDTO;
import com.qf.entity.TOrderdetail;
import com.qf.mapper.order.TOrderdetailMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreateOrderDetail {

    @Autowired
    private TOrderdetailMapper tOrderdetailMapper;

    @RabbitListener(queues = RabbitConstant.CREATE_ORDER_DETAIL_QUEUE)
    public void createOrderDetail(OrderDetailDTO orderDetailDTO){
        Integer orderId = orderDetailDTO.getOrderId();
        List<Orderdetail> orderdetailList = orderDetailDTO.getOrderdetailList();
        for (Orderdetail orderdetail : orderdetailList) {
            TOrderdetail tOrderdetail = new TOrderdetail();
            tOrderdetail.setOrderid(orderId);
            tOrderdetail.setProductid(orderdetail.getProductID());
            tOrderdetail.setPrice(new BigDecimal(orderdetail.getPrice()));
            tOrderdetail.setFee(new BigDecimal(orderdetail.getFee()));
            tOrderdetail.setNumber(orderdetail.getNumber());
            tOrderdetail.setProductname(orderdetail.getProductName());
            tOrderdetail.setTotal0(new BigDecimal(orderdetail.getTotal0()));
            tOrderdetailMapper.insertSelective(tOrderdetail);
        }
        System.out.println("订单详情数据插入成功!");
    }
}
