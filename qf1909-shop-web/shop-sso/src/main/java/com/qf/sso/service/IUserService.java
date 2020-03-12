package com.qf.sso.service;

import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "user-server")
public interface IUserService {

    @RequestMapping(value = "loginCheck",method = RequestMethod.POST)
    ResultBean loginCheck(@RequestParam String username, @RequestParam String password);

    @RequestMapping(value = "user/regist",method = RequestMethod.POST)
    ResultBean regist(@RequestParam String uname, @RequestParam String password);
}
