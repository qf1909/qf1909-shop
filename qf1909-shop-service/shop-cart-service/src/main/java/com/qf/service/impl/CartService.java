package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.entity.TProduct;
import com.qf.mapper.product.ProductCartMapper;
import com.qf.service.ICartService;
import com.qf.util.StringUtil;
import com.qf.vo.CartItemVO;
import com.qf.vo.ProductCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService implements ICartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductCartMapper productCartMapper;

    //添加购物车
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
        //购物车不为空  将重复的商品数量相加
        for (CartItemVO cartItemVO : cartItemList) {
            if (cartItemVO.getProductId().equals(productId)){
                cartItemVO.setCount(cartItemVO.getCount()+count);
                cartItemVO.setUpdateTime(new Date());
                redisTemplate.opsForValue().set(redisKey,cartItemList);
                return ResultBean.success(cartItemList,"添加购物车成功!");
            }
        }
        //购物车为空或者购物车不为空但购物车中无 准备i添加的商品
        CartItemVO cartItem = new CartItemVO();
        cartItem.setProductId(productId);
        cartItem.setCount(count);
        cartItem.setUpdateTime(new Date());
        cartItemList.add(cartItem);
        redisTemplate.opsForValue().set(redisKey,cartItemList);
        return ResultBean.success(cartItemList,"添加购物车成功!");
    }


    //清空购物车
    @Override
    public ResultBean cleanCart(String uuid) {
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, uuid);
        redisTemplate.delete(redisKey);
        return ResultBean.success("清空购物车成功!");
    }


    //更新购物车
    @Override
    public ResultBean updateCart(String uuid, Long productId, int count) {
        if (uuid !=null && !"".equals(uuid)) {
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, uuid);
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                List<CartItemVO> cartItemList = (List<CartItemVO>) o;
                for (CartItemVO cartItemVO : cartItemList) {
                    if (cartItemVO.getProductId().equals(productId)) {
                        cartItemVO.setCount(count);
                        cartItemVO.setUpdateTime(new Date());
                        redisTemplate.opsForValue().set(redisKey,cartItemList);
                        return ResultBean.success("更新购物车成功!");
                    }
                }
            }
        }
        return ResultBean.error("无购物车!");
    }


    //查看购物车
    @Override
    public ResultBean showCart(String uuid) {
     if (uuid !=null && !"".equals(uuid)){
         //获取购物车
         String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, uuid);
         Object o = redisTemplate.opsForValue().get(redisKey);
         if (o !=null){
             List<CartItemVO> cartItemList = (List<CartItemVO>) o;
             List<ProductCartVO> productCartList = new ArrayList<>();
             //遍历购物车的商品项
             for (CartItemVO cartItemVO : cartItemList) {
                 //获取redis中的购物车商品详情
                 String pRedisKey = StringUtil.getRedisKey(RedisConstant.PRODUCT_PRE, cartItemVO.getProductId().toString());
                 Object o1 = redisTemplate.opsForValue().get(pRedisKey);
                 TProduct product = JSON.parseObject(JSON.toJSONString(o1), TProduct.class);
                 if (product==null){
                     //redis中没有就去数据库查询
                     Long productId = cartItemVO.getProductId();
                     product = productCartMapper.queryProductDetailOfCart(productId);
                     redisTemplate.opsForValue().set(pRedisKey,product);
                 }
                 ProductCartVO productCartVO = new ProductCartVO();
                 productCartVO.setCount(cartItemVO.getCount());
                 productCartVO.setTotalPrice(product.getSalePrice()*cartItemVO.getCount());
                 productCartVO.setProduct(product);
                 productCartVO.setUpdateTime(new Date());
                productCartList.add(productCartVO);
             }
             //排序
             Collections.sort(productCartList, new Comparator<ProductCartVO>() {
                 @Override
                 public int compare(ProductCartVO o1, ProductCartVO o2) {
                     return (int) (o2.getUpdateTime().getTime()-o1.getUpdateTime().getTime());
                 }
             });
             return  ResultBean.success(productCartList);
         }
     }
        return ResultBean.error("无购物车");
    }

//合并购物车
    @Override
    public ResultBean mergeCart(String uuid, String userId) {
        /*
                合并
        1.未登录状态下没有购物车==》合并成功
        2.未登录状态下有购物车，但已登录状态下没有购物车==》把未登录的变成已登录的
        3.未登录状态下有购物车，但已登录状态下也有购物车，而且购物车中的商品有重复==》难点！
         */
        //获取两种状态下的redis中的购物车
        String noLoginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, uuid);
        String loginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, userId);
        Object noLogin = redisTemplate.opsForValue().get(noLoginRedisKey);
        Object login = redisTemplate.opsForValue().get(loginRedisKey);
        //未登录没有购物车
        if (noLogin ==null){
            redisTemplate.delete(noLoginRedisKey);
            return  ResultBean.success("无需合并");
        }
        //未登录有购物车,登录状态没有购物车
        if (login ==null){
            redisTemplate.opsForValue().set(loginRedisKey,noLogin);
            redisTemplate.delete(noLoginRedisKey);
            System.out.println( "合并成功!");
            return  ResultBean.success(noLogin,"合并成功!");
        }
        //未登录有购物车,登录有购物车
        List<CartItemVO> loginList = (List<CartItemVO>)login;
        List<CartItemVO> noLoginList = (List<CartItemVO>)noLogin;
        //将未登录状态的购物车 放进map key为购物车商品id  value  商品详情
        Map<Long,CartItemVO> map = new HashMap<>();
        for (CartItemVO cartItemVO : noLoginList) {
            map.put(cartItemVO.getProductId(),cartItemVO);
        }
        for (CartItemVO cartItemVO : loginList) {
            //获取未登录状态购物车map
            CartItemVO currentCartItem = map.get(cartItemVO.getProductId());
            //两种状态的购物车有重复商品
            if (currentCartItem != null){
                currentCartItem.setCount(currentCartItem.getCount()+cartItemVO.getCount());
            }else{
                map.put(cartItemVO.getProductId(),cartItemVO);
            }
        }
        //删除未登录状态下的redis
        redisTemplate.delete(noLoginRedisKey);
        //获取map中的value
        Collection<CartItemVO> values = map.values();
        List<CartItemVO> newCartList = new ArrayList<>(values);
        redisTemplate.opsForValue().set(loginRedisKey,newCartList);
        return ResultBean.success(newCartList,"合并成功!");
    }


}
