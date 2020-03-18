package com.qf.service;


import com.qf.bean.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order-server")
public interface IOrderService {

    @RequestMapping(value = "creatOrder")
    String creatOrder(@RequestParam String uuid);

}
