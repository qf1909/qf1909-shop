package com.qf.service;

import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements IPayService{
    @Override
    public String dopay(String uuid) {
        return "支付成功";
    }
}
