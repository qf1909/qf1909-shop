package com.qf.controller;


import com.qf.bean.ResultBean;
import com.qf.service.IUserService;
import com.qf.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private IUserService userService;

    @RequestMapping("loginCheck")
    @ResponseBody
    public ResultBean loginCheck(String username,String password) {

        ResultBean resultBean = userService.checkLogin(username, password);

        return resultBean;
    }


    }
