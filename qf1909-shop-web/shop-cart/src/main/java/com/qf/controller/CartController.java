package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.service.ICartService;
import com.qf.util.StringUtil;
import com.qf.vo.ProductCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private RedisTemplate redisTemplate;
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
//            TUser user = (TUser) o;
            TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
            Long id = user.getId();
            //调用服务 --添加商品到购物车
            ResultBean resultBean = cartService.addProductToCart(id.toString(),productId, count);
            return  resultBean;
        }

        //未登录状态
        if (uuid ==null || "".equals(uuid)){
           uuid = UUID.randomUUID().toString();
            Cookie cookie =new Cookie(CookieConstant.USER_CART,uuid);
//            cookie.setMaxAge(30*60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        ResultBean resultBean = cartService.addProductToCart(uuid, productId, count);
        return resultBean;
    }

    //清空购物车
        @RequestMapping("cleanCart")
        @ResponseBody
        public  ResultBean  cleanCart(@CookieValue(name = CookieConstant.USER_CART,required = false)String  uuid,HttpServletRequest request,
                                      HttpServletResponse response){
            Object o = request.getAttribute("user");
            //登录状态
            if (o!=null){
                TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
                Long id = user.getId();
                ResultBean resultBean = cartService.cleanCart(id.toString());
                return  resultBean;
            }
            //未登录状态
            if (uuid !=null &&! "".equals(uuid)){
                Cookie cookie =new Cookie(CookieConstant.USER_CART,"");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                ResultBean resultBean = cartService.cleanCart(uuid);
                return resultBean;
            }
            return  ResultBean.error("清空购物车失败!");
        }
    //更新购物车
    @RequestMapping("updateCart/{productId}/{count}")
    @ResponseBody
    public  ResultBean updateCart(@CookieValue(name = CookieConstant.USER_CART,required = false) String uuid,
                                  @PathVariable Long productId,@PathVariable int count,
                                   HttpServletRequest request){
        Object o = request.getAttribute("user");
        if (o != null){
            TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
            Long id = user.getId();
            return cartService.updateCart(id.toString(),productId,count);
        }
        return cartService.updateCart(uuid,productId,count);
    }


    //查看购物车
    @RequestMapping("showCart")
    public  String  showCart(@CookieValue(name = CookieConstant.USER_CART,required = false) String uuid,
                             HttpServletRequest request, Model model){
        Object o = request.getAttribute("user");
        if (o != null){
            TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
            Long id = user.getId();
            ResultBean resultBean = cartService.showCart(id.toString());
            List<ProductCartVO> data = (List<ProductCartVO>) resultBean.getData();
            model.addAttribute("cart",data);
            model.addAttribute("user",user);
            return  "shopcart";
        }
        ResultBean resultBean = cartService.showCart(uuid);
        model.addAttribute("cart",resultBean.getData());
        return "shopcart";
    }

    //合并购物车
    @RequestMapping("mergeCart")
    @ResponseBody
    public  ResultBean mergeCart(@CookieValue(name = CookieConstant.USER_CART ,required = false) String uuid,
                                 HttpServletRequest request,HttpServletResponse response){
        String userId = null;
        Object o = request.getAttribute("user");
        if (o != null){
            TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
            userId = Long.valueOf(user.getId()).toString();
        }
        //清空cookie
        Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        ResultBean resultBean = cartService.mergeCart(uuid,userId);
        return  resultBean;
    }


//    @RequestMapping("redirectOrder")
//    public ModelAndView goOrderConfirm(String user, String cart){
//        System.out.println(user);
//        System.out.println(cart);
//        ModelAndView modelAndView = new ModelAndView("redirect:http://localhost:9087/orderConfirm");
//        modelAndView.addObject("user",user);
//        modelAndView.addObject("cart",cart);
//        return modelAndView;
//    }

    @RequestMapping("redirectOrder")
    public String goOrderConfirm(String user, String cart){
        System.out.println(user);
        System.out.println(cart);
        TUser tUser = JSON.parseObject(user, TUser.class);
        Long id = tUser.getId();
        List<ProductCartVO> orderList = JSON.parseArray(cart, ProductCartVO.class);
        String redisKey = StringUtil.getRedisKey(RedisConstant.PRODUCT_ORDER_PRE, id.toString());
        redisTemplate.opsForValue().set(redisKey,orderList);
        return "redirect:http://localhost:9087/orderConfirm";
    }

}
