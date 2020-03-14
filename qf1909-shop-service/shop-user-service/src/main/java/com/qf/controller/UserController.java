package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService  userService;

    @RequestMapping("cLogin")
    @ResponseBody
    public ResultBean  checkLogin(String uname, String password){
        return userService.checkLogin(uname,password);
    }

    @RequestMapping("csLogin")
    @ResponseBody
    public ResultBean  checksLogin(String uuid){
        ResultBean resultBean = userService.checksLogin(uuid);
        return resultBean;
    }

}
