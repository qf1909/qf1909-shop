package com.qf.service.impl;

import com.qf.bean.Order;
import com.qf.entity.TOrder;
import com.qf.mapper.order.TOrderMapper;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private TOrderMapper tOrderMapper;

    @Override
    public Integer createOrder(Order order) {
        TOrder tOrder = new TOrder();
        tOrder.setStatus(order.getStatus());
        tOrder.setQuantity(order.getQuantity());
        tOrder.setAccount(order.getAccount());
        tOrder.setPaystatus(order.getPaystatus());
        tOrder.setOtherrequirement(order.getOtherRequirement());
        tOrderMapper.insertSelective(tOrder);
        return tOrder.getId();
    }
}
