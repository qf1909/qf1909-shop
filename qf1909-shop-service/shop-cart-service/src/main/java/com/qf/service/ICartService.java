package com.qf.service;

import com.qf.bean.ResultBean;

public interface ICartService {

    ResultBean addProductToCart(String uuid,Long  productId,int count);

    ResultBean cleanCart(String uuid);

    ResultBean updateCart(String uuid,Long productId,int count);

    ResultBean showCart(String uuid);

    ResultBean mergeCart(String uuid ,String userId);
}
