package com.qf.service;


import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-cart-service")
public interface ICartService {

    @RequestMapping("insert")
    ResultBean addProductToCart(@RequestParam String uuid,@RequestParam Long productId, @RequestParam int count);
}
