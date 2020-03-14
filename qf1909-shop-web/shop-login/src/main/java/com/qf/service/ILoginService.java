package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-user-service")
public interface ILoginService {

    @RequestMapping("cLogin")
     ResultBean checkLogin(@RequestParam String uname, @RequestParam String password);

    @RequestMapping("csLogin")
     ResultBean  checksLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid);
}
