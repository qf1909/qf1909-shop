package com.qf.service;


import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server")
public interface MyService {

    @RequestMapping("loginCheck")
    ResultBean loginCheck(@RequestParam("username")String username,@RequestParam("password") String password);

}
