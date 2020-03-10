package com.qf.service.impl;



import com.qf.bean.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserServiceImpl implements IuserService {

    @Autowired
    private TUserMapper userMapper;

    /**
     * 登录检验
     */
    @Override
    public ResultBean checkLogin(String username, String password) {
        TUser user = userMapper.selectByUsername(username);
        if (user != null){
            if (!password.equals("")  &&  !user.getPassword().equals("")  &&  user.getPassword().equals(password)){
                //这里使用spring security
                return ResultBean.success(user,"登录成功");
            }
        }
        return ResultBean.error("用户名或密码错误");
    }



}
