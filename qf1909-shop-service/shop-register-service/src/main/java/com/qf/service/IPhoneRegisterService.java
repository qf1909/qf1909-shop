package com.qf.service;


import com.qf.bean.ResultBean;

public interface IPhoneRegisterService {

    ResultBean  registerByPhone(String phone, String code, String password);

}
