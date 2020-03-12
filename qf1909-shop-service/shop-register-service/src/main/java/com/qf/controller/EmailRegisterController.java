package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.IEmailRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailRegisterController {
    @Autowired
    private IEmailRegisterService emailRegisterService;

    @RequestMapping("email")
    @ResponseBody
    public ResultBean registerByEmail(String email, String password){
        ResultBean resultBean = emailRegisterService.registerByEmail(email, password);
        return resultBean;
    }
}
