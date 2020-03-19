package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.constant.RabbitConstant;
import com.qf.constant.RedisConstant;
import com.qf.dto.EmailMessageDTO;
import com.qf.entity.TUser;
import com.qf.mapper.register.RegisterMapper;
import com.qf.service.IEmailRegisterService;
import com.qf.util.SpringSecurityUtil;
import com.qf.util.StringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EmailRegisterService implements IEmailRegisterService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${activeAccountServer}")
    private  String activeAccountServer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public ResultBean registerByEmail(String email, String password) {
        try {
            //1.发邮件
            EmailMessageDTO message = new EmailMessageDTO();//里面有两样东西： username   url
            message.setEmail(email);
            //生成uuid
            String uuid = UUID.randomUUID().toString();
            //创建url
            String url = activeAccountServer+uuid;
            message.setUrl(url);
            rabbitTemplate.convertAndSend(RabbitConstant.EMAIL_TOPIC_EXCHANGE,"send-email",message);
            //2.将数据插入到数据库中
            TUser user = new TUser();
            user.setUname(uuid);
            user.setEmail(email);
            user.setPassword(SpringSecurityUtil.getEncodePassword(password));
            registerMapper.insertRegisterUser(user);
            //3.存入到redis中
            //组织键
            String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_EMAIL,uuid);
            redisTemplate.opsForValue().set(redisKey,email,10, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("注册失败");
        }
        return ResultBean.success("注册成功");
    }

}
