package com.qf.service;

import com.qf.bean.ResultBean;

public interface IEmailRegisterService {

    ResultBean registerByEmail(String email, String password);
}
