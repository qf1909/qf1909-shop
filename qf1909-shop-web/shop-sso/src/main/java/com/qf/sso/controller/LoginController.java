package com.qf.sso.controller;


import com.qf.bean.ResultBean;
import com.qf.constant.CookiesConstant;
import com.qf.constant.RedisConstant;
import com.qf.sso.service.IUserService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {



    @Autowired
    private IUserService service;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/login")
    public String getIndex(){
        return "login";
    }


    @RequestMapping("/loginCheck")
    @ResponseBody
    public String checkLogin(String username, String password, HttpServletResponse response){

        ResultBean resultBean = service.loginCheck(username, password);
        if(resultBean !=null && resultBean.getErrno() == 0){

            //生成cookies
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(CookiesConstant.USER_LOGIN,uuid);

            //将用户信息存入redis
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            redisTemplate.opsForValue().set(redisKey,resultBean.getData(),30, TimeUnit.DAYS);

            //将cookies发送到客户端
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            //重定向到index页面
            return "http://localhost:9083/index";
        }

        return "redirect:loginCheck";


    }

//    @RequestMapping("/checkIsLogin")
//    @ResponseBody
//    public String checkIsLogin(String username, String password){
//
//        ResultBean resultBean = service.loginCheck(username, password);
//        System.out.println(resultBean);
//
//        return null;

//    }

}
