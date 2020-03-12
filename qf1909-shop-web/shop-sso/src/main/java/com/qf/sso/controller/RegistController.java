package com.qf.sso.controller;


import com.qf.bean.ResultBean;
import com.qf.sso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistController {

    @Autowired
    private IUserService service;


    @GetMapping("/register")
    public String getIndex(){
        return "register";
    }


    @RequestMapping("regist")
    public ResultBean regist(@RequestParam String uname, @RequestParam String password){
        ResultBean regist = service.regist(uname, password);
        return  service.regist(uname,password);
    }

}
