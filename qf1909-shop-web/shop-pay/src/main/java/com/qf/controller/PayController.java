package com.qf.controller;

import com.qf.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PayController {

    @Autowired
    private IPayService payService;

    @RequestMapping("success")
    public String getSuccess(){
        return "success";
    }

    @RequestMapping("pay")
    public String showPay(){
        return "pay";
    }


    @RequestMapping("doPay")
    public String doPay(@RequestParam String uuid) {

        payService.dopay(uuid);
        return null;

    }
}
