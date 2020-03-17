package com.qf.controller;


import com.alibaba.fastjson.JSON;
import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.service.ILoginService;
import com.qf.util.HttpClientUtils;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
//@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("login")
    public String getIndex(){
        return "login";
    }


    @RequestMapping("checkLogin")
    public String login(@RequestParam String uname, @RequestParam String password, HttpServletResponse response,
                        @CookieValue(name = CookieConstant.USER_CART,required = false) String cartId){
        ResultBean resultBean = loginService.checkLogin(uname, password);
        if (resultBean.getErrno()==0){
            String uuid = UUID.randomUUID().toString();
            //创建cookie
            Cookie cookie =new Cookie(CookieConstant.USER_LOGIN,uuid);
            //将cookie中的uuid  组值redis键
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //redis  根据组织的键 存放用户信息
            redisTemplate.opsForValue().set(redisKey,resultBean.getData(),30,TimeUnit.DAYS);
            cookie.setMaxAge(30*24*60*60);  //cookie 有效期
            cookie.setPath("/"); //路径
            cookie.setHttpOnly(true); //仅客户端可见
            response.addCookie(cookie);

            //合并购物车的请求
            //由HttpClient 发送请求 不会自动携带cookie 所以手写cookie
            StringBuilder sb = new StringBuilder();
            //拼接cookie
            //未登录状态    只有  是否有购物车的uuid
            sb.append(CookieConstant.USER_CART);
            sb.append("=");
            sb.append(cartId);
            sb.append(";");
            //如果已经登录了，那么cookie里就会有这个user_login的uuid
            sb.append(CookieConstant.USER_LOGIN);
            sb.append("=");
            sb.append(uuid);
            //发送请求
            String url = "http://localhost:9085/mergeCart";
            HttpClientUtils.doGet(url,sb.toString());
            return "redirect:http://localhost:9082/index";
        }
        return "redirect:login";
    }

    @RequestMapping("checksLogin")
    @ResponseBody
    public  ResultBean checksLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid){
        ResultBean resultBean = loginService.checksLogin(uuid);
        return resultBean;
    }

    @RequestMapping("logout")
    @ResponseBody
    public  ResultBean logout(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid,HttpServletResponse response){
        if (uuid != null && !"".equals(uuid)){
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            redisTemplate.delete(redisKey);
        }
        Cookie cookie =new Cookie(CookieConstant.USER_LOGIN,"");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return  ResultBean.success("已注销");
    }
}
