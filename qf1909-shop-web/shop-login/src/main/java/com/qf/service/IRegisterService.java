package com.qf.service;

import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-register-service")
public interface IRegisterService {

    @RequestMapping("phone")
     ResultBean registerByPhone(@RequestParam String phone, @RequestParam String code, @RequestParam String password);

    @RequestMapping("email")
     ResultBean registerByEmail(@RequestParam String email, @RequestParam String password);
}
