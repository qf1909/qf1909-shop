package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.entity.TProduct;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/16 - 17:43
 */
public interface IOrderService {

    /**
     * 将选中的商品列表存入Redis
     * @param productList
     * @return
     */
    public ResultBean saveProductListToRedis(List<TProduct> productList);

}
