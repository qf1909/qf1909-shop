package com.qf.controller;

import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import com.qf.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class OrederConfirmController {


    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public String pay(Order e, ModelMap model, HttpServletRequest request) throws Exception{
        Object user = request.getAttribute("user");
        return orderService.insertAndPay(e, model,user);
    }



    }
