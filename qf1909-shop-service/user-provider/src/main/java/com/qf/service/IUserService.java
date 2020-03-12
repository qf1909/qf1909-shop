package com.qf.service;

import com.qf.bean.ResultBean;

public interface IUserService {

    ResultBean loginCheck(String username, String password);
}
