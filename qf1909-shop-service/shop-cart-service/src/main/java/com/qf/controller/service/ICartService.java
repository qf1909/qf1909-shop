package com.qf.controller.service;

import com.qf.bean.ResultBean;

public interface ICartService {

    ResultBean addProductToCart(String uuid,Long  productId,int count);
}
