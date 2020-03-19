package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("shop-order-service")
public interface OrderService {

    @RequestMapping("createOrder")
    ResultBean createOrder(@RequestBody OrderDTO orderDTO);
}
