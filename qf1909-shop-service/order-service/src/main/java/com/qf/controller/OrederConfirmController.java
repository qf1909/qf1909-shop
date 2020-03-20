package com.qf.controller;

import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import com.qf.constant.CookieConstant;
import com.qf.entity.TUser;
import com.qf.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OrederConfirmController {


    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private OrderServiceImpl orderService;

    /**
     *
//     * @param e  用户信息
//     * @param model
//     * @param request
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "pay", method = RequestMethod.POST)
    @RequestMapping("creatOrder")
    public String creatOrder(String uuid) throws Exception{
//
//        Object user = request.getAttribute("user");
        return orderService.insertAndPay(uuid);
    }





    }