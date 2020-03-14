package com.qf.controller;


import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderConfirmController {

    @GetMapping("/orderConfirm")
    public String getIndex(){
        return "orderConfirm";
    }

    /**
     * 点击 创建订单 按钮
     * @param e
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String pay(Order e, ModelMap model, HttpServletRequest request) throws Exception{
        Object user = request.getAttribute("user");
        return insertAndPay(e, model,user);
    }

    private String insertAndPay(Order e, ModelMap model,Object user) throws Exception {

        if (user == null){
            return "用户未登录";
        }
        //TODO 从Redis中获取用户购买的商品列表
        
        CartInfo cartInfo =null;
        if(cartInfo ==null || cartInfo.getProductList().size() == 0){
            return "购物车中没有商品";
        }
        //TODO 检测商品是否都有库存,如果没有库存需要提醒用户
        //TODO 库存不足，则提示用户某些商品的库存不足，请重新选购
        //TODO 获取配送方式

    }

}
