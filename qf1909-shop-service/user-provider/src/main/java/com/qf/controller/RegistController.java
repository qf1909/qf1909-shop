package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class RegistController {


    @Autowired
    private IRegistService registService;

    @RequestMapping("regist")
    @ResponseBody
    public ResultBean regist(@RequestParam String uname, @RequestParam String password){
        return registService.regist(uname,password);
    }
}
