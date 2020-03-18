package com.qf.service;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IpayService {

    void doPay(@RequestParam String uuid, HttpServletRequest httpRequest,
               HttpServletResponse httpResponse);
}
