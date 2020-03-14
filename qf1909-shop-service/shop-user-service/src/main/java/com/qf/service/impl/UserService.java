package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.mapper.user.TUserMapper;
import com.qf.service.IUserService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean checkLogin(String username, String password) {
        TUser user = tUserMapper.selectByUsername(username);
        if (password !=null && !"".equals(password) && encoder.matches(password,user.getPassword())){
                return  ResultBean.success(user,"登录成功");
        }
        return ResultBean.error("登录失败");
    }

    @Override
    public ResultBean checksLogin(String uuid) {
        if (uuid !=null  && !"".equals(uuid)){
            String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null){
                return  ResultBean.success(o,"该用户已登录");
            }
        }
        return ResultBean.error("该用户未登录");
    }
}
