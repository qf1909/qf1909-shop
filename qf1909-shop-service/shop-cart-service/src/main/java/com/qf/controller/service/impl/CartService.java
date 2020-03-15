package com.qf.controller.service.impl;

import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.controller.service.ICartService;
import com.qf.util.StringUtil;
import com.qf.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService implements ICartService  {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean addProductToCart(String uuid,Long productId, int count) {
        /*
         *     * 	1）当前用户没有购物车
         *      * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
         *      * 	2）当前用户有购物车，但是购物车中没有该商品
         *      * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
         *      * 	3）当前用户有购物车，且购物车中有该商品
         *      * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中。
         */
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, uuid);
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o ==null){
            //无购物车
            List<CartItemVO> cartItemList = new ArrayList<>();
            CartItemVO cartItem = new CartItemVO();
            cartItem.setProductId(productId);
            cartItem.setCount(count);
            cartItem.setUpdateTime(new Date());
            cartItemList.add(cartItem);
            redisTemplate.opsForValue().set(redisKey,cartItemList);
            return ResultBean.success(cartItemList,"添加购物车成功!");
        }

        //有购物车
        List<CartItemVO> cartItemList = (List<CartItemVO>) o;
        //但购物车为空
        if (cartItemList.size()==0){
            CartItemVO cartItem = new CartItemVO();
            cartItem.setProductId(productId);
            cartItem.setCount(count);
            cartItem.setUpdateTime(new Date());
            cartItemList.add(cartItem);
            redisTemplate.opsForValue().set(redisKey,cartItemList);
            return ResultBean.success(cartItemList,"添加购物车成功!");
        }

        //购物车不为空  将重复的商品数量相加
        for (CartItemVO cartItemVO : cartItemList) {
            if (cartItemVO.getProductId().equals(productId)){
                cartItemVO.setCount(cartItemVO.getCount()+count);
                cartItemVO.setUpdateTime(new Date());
            }
        }
        redisTemplate.opsForValue().set(redisKey,cartItemList);
        return ResultBean.success(cartItemList,"添加购物车成功!");
    }
}
