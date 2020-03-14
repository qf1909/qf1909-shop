package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private IIndexService indexService;


    @GetMapping ("/index")
    public String getIndex(){
        return "index";
    }


    @RequestMapping("checksLogin")
    @ResponseBody
    public  ResultBean checksLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid){
        ResultBean resultBean = indexService.checksLogin(uuid);
        return resultBean;
    }

}
