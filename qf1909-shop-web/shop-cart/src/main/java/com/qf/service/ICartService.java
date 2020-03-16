package com.qf.service;


import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-cart-service")
public interface ICartService {

    @RequestMapping("insert")
    ResultBean addProductToCart(@RequestParam String uuid,@RequestParam Long productId, @RequestParam int count);

    @RequestMapping("delete")
    ResultBean  cleanCart(@RequestParam String uuid);

    @RequestMapping("update")
    ResultBean updateCart(@RequestParam String uuid,@RequestParam Long productId, @RequestParam int count);

    @RequestMapping("select")
    ResultBean showCart(@RequestParam String uuid);

    @RequestMapping("merge")
     ResultBean mergeCart(@RequestParam String uuid ,@RequestParam String userId);
}
