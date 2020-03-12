package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.RabbitConstant;
import com.qf.service.IRegisterService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IRegisterService registerService;

    @GetMapping("/register")
    public String getIndex(){
        return "register";
    }

    @RequestMapping("getCode")
    @ResponseBody
    public ResultBean getCode(@RequestParam String phone){
        rabbitTemplate.convertAndSend(RabbitConstant.SMS_TOPIC_EXCHANGE,"send-sms",phone);
        return ResultBean.success();
    }

    @RequestMapping("phoneRegister")
    @ResponseBody
    public  ResultBean  registerByPhone(@RequestParam String phone,@RequestParam String code, @RequestParam String password){
        ResultBean resultBean = registerService.registerByPhone(phone, code, password);
        return resultBean;
    }

    @RequestMapping("emailRegister")
    @ResponseBody
    public  ResultBean  registerByEmail(@RequestParam String email, @RequestParam String password){
        ResultBean resultBean = registerService.registerByEmail(email, password);
//        return "redirect:/user/showLogin";
        return resultBean;
    }
}
