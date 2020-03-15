package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.entity.TUser;
import com.qf.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

//    @Autowired
//    private RedisTemplate redisTemplate;
    //添加商品到购物车
    /*
     * 	1）当前用户没有购物车
     * 		新建购物车，把商品添加到购物车中，再把购物车存到redis中
     * 	2）当前用户有购物车，但是购物车中没有该商品
     * 		先从redis中获取该购物车，再把商品添加都购物车中，再存入到redis中。
     * 	3）当前用户有购物车，且购物车中有该商品
     * 		先从redis中获取该购物车，再获取该商品的数量，再让新的数量和老的数量相加，更新回购物车中，再更新回redis中
     */
    @RequestMapping("addCart/{productId}/{count}")
    @ResponseBody
    public ResultBean addProductToCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                                       @PathVariable Long productId, @PathVariable int count,
                                       HttpServletResponse response, HttpServletRequest request){

        Object o = request.getAttribute("user");
        if (o != null){   //已登录状态   (会经过拦截器  将user对象存放到request域中)
            TUser user = (TUser) o;
            Long id = user.getId();
            //调用服务 --添加商品到购物车
            ResultBean resultBean = cartService.addProductToCart(id.toString(),productId, count);
            return  resultBean;
        }

        //未登录状态
        if (uuid ==null || "".equals(uuid)){
           uuid = UUID.randomUUID().toString();
            Cookie cookie =new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setMaxAge(30*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        ResultBean resultBean = cartService.addProductToCart(uuid, productId, count);
        return resultBean;
    }
    //清空购物车
    //更新购物车
    //查看购物车
    //合并购物车
}
