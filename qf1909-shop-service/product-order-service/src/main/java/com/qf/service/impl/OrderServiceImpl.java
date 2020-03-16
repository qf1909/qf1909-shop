package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.entity.TProduct;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/16 - 17:43
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将选中的商品列表存入Redis
     * @param productList
     * @return
     */
    @Override
    public ResultBean saveProductListToRedis(List<TProduct> productList) {

        // 清空
        while (redisTemplate.opsForList().size("product-order-list")>0){
            redisTemplate.opsForList().leftPop("product-order-list");
        }

       try {
           // 将productList存入Redis中
           redisTemplate.opsForList().rightPushAll("product-order-list",productList);
           return ResultBean.success("存入Redis成功！");
       } catch (Exception e){
           e.printStackTrace();
       }

        return ResultBean.error("存入Redis失败！");
    }





}
