package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.mapper.register.RegisterMapper;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("email")
public class EmailController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RegisterMapper registerMapper;

    @RequestMapping("active")
    @ResponseBody
    public ResultBean actionAccount(String uuid){
        String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_EMAIL,uuid);
        String email = (String) redisTemplate.opsForValue().get(redisKey);
        registerMapper.updateRegisterStatus(email);
        return ResultBean.success(uuid);
    }
}
