package com.qf.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "pay-server")
public interface IPayService {

    @RequestMapping(value = "doPay")
    void dopay(@RequestParam String uuid);


}
