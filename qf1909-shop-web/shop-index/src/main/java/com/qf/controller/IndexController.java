package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TProductType;
import com.qf.service.IIndexService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private IIndexService indexService;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping ("/index")
    public String getIndex(Model model){
        List<TProductType> typeList = indexService.queryProductType();
        model.addAttribute("types",typeList);
        return "index";
    }


    @RequestMapping("checksLogin")
    @ResponseBody
    public  ResultBean checksLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid){
        ResultBean resultBean = indexService.checksLogin(uuid);
        return resultBean;
    }

    @RequestMapping("logout")
    public  String logout(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid, HttpServletResponse response){
        if (uuid != null && !"".equals(uuid)){
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            redisTemplate.delete(redisKey);
        }
        Cookie cookie =new Cookie(CookieConstant.USER_LOGIN,"");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return  "index";
    }
}
