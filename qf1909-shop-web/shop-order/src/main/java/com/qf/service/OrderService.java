package com.qf.service;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("shop-order-service")
public interface OrderService {

    @RequestMapping("createOrder")
    ResultBean createOrder(@RequestBody Order order,@RequestBody List<Orderdetail> orderdetailList);
}
