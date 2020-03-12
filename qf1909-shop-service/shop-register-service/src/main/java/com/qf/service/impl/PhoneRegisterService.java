package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.mapper.register.RegisterMapper;
import com.qf.service.IPhoneRegisterService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PhoneRegisterService implements IPhoneRegisterService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public ResultBean registerByPhone(String phone, String code, String password) {
        //获取redis存储手机验证码的键
        String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_PHONE, phone);
        //获取redis存储的验证码
        String redisCode = (String) redisTemplate.opsForValue().get(redisKey);
        //判断
        if (redisCode.equals(code)){
            //注册信息插入数据库
            TUser user = new TUser();
            user.setPassword(password);
            user.setPhone(phone);
            registerMapper.insertRegisterUser(user);
            return  ResultBean.success("注册成功!");
        }
        return  ResultBean.error("验证码错误!");
    }
}
