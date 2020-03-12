package com.qf.handler;

import com.qf.constant.RabbitConstant;
import com.qf.constant.RedisConstant;
import com.qf.util.SMSUtil;
import com.qf.util.StringUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SMSHandler {

    @Value("${sms.account}")
    private  String  account;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.veryCode}")
    private String veryCode;

    @Value("${sms.http_url}")
    private String http_url;

    @Value("${sms.tplId}")
    private String tplId;

    @Autowired
    private RedisTemplate  redisTemplate;

    @RabbitListener(queues = RabbitConstant.SMS_SEND_QUEUE)
    public  void  sendMessage(String phone){
        //1.发送短信
        String code = String.valueOf((int) (((Math.random() * 9 + 1) * 1000)));
        String content = "@1@="+code;
        SMSUtil.account = account;
        SMSUtil.password = password;
        SMSUtil.veryCode = veryCode;
        SMSUtil.http_url  =http_url;
        SMSUtil.sendTplSms(phone,tplId,content);

        //2.组织键值对，存入到redis中
        String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_PHONE, phone);
        //设置键值对和有效期
       redisTemplate.opsForValue().set(redisKey,code,1,TimeUnit.MINUTES);
    }
}
