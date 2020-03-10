package com.qf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getIndex(){
        return "login";
    }


    @RequestMapping("/checkLogin")
    @ResponseBody
    public String login(String username, String password, HttpServletResponse response){

        return null;

    }


}
