package com.qf.service;

import com.qf.bean.ResultBean;

public interface IUserService {

    ResultBean  checkLogin(String uname,String password);

    ResultBean  checksLogin(String uuid);
}
