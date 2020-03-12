package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.service.IPhoneRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PhoneRegisterController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IPhoneRegisterService registerService;

    @RequestMapping("redisCode")
    @ResponseBody
    public  void getRedis(){
        String s = (String) redisTemplate.opsForValue().get(RedisConstant.REGISTER_PHONE + "18571937940");
        System.out.println(s);
    }

    @RequestMapping("phone")
    @ResponseBody
    public ResultBean registerByPhone(String phone, String code, String password){
        ResultBean resultBean = registerService.registerByPhone(phone, code, password);
        return resultBean;
    }


}
