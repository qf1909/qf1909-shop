package com.qf.controller;





import com.qf.bean.ResultBean;
import com.qf.constant.CookiesConstant;
import com.qf.constant.RedisConstant;
import com.qf.service.IUserService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private IUserService userService;


    @RequestMapping("loginCheck")
    @ResponseBody
    public ResultBean loginCheck(@RequestParam String username,@RequestParam String password ) {

        ResultBean resultBean = userService.loginCheck(username, password);
       return resultBean;
    }

    @RequestMapping("checkIsLogin")
    @ResponseBody
    public String checkIsLogin(){


        return null;
    }



    }
