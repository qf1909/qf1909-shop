package com.qf.controller;


import com.qf.bean.ResultBean;
import com.qf.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private MyService service;


    @GetMapping("/login")
    public String getIndex(){
        return "login";
    }


    @RequestMapping("/checkLogin")
    @ResponseBody
    public String login(String username, String password, HttpServletResponse response){

        ResultBean resultBean = service.loginCheck(username, password);
        System.out.println(resultBean);

        return null;

    }


}
