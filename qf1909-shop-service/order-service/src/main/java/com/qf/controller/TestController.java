package com.qf.controller;


import com.qf.bean.Address;
import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import com.qf.entity.TProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController {


    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "test")
    public String pay(){
        UUID uuid = UUID.randomUUID();
        List list = new ArrayList();
        redisTemplate.opsForValue().set("user_uuid",uuid);
        TProduct product = new TProduct(1,"new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待",
               1000.00,999.00,560,1,null,1, "2015-03-08 21:33:18","2015-04-11 20:38:38",1,1);
        list.add(product);
        CartInfo cartInfo = new CartInfo();
        cartInfo.setProductList(list);
        cartInfo.setAmount("1234");
        cartInfo.setAddress(new Address());
        String key = "cartinfo";
        redisTemplate.opsForValue().set(key,cartInfo);
        return null;
    }


}





