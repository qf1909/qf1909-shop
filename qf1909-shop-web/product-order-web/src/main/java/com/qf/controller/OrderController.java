package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author smj
 * @date 2020/3/16 - 14:36
 */
@Controller
@RequestMapping("order")
public class OrderController {

    /**
     * 返回到orderConfirm页面，订单确认
     * @return
     */
    @RequestMapping("orderConfirm")
    public String orderConfirm(){

        return "orderConfirm";
    }

    @RequestMapping("insertAndPay")
    public String insertAndPay(){

        return "";
    }



}
