package com.qf.service;

import com.qf.bean.ResultBean;

public interface IuserService {


     ResultBean checkLogin(String username, String password);
}
