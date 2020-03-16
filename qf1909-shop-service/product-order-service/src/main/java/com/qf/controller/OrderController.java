package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.entity.TProduct;
import com.qf.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/16 - 18:13
 */
@Controller
@RequestMapping("order-service")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("addProductListToRedis")
    @ResponseBody
    public ResultBean addProductListToRedis(List<TProduct> productList){

        ResultBean resultBean = orderService.saveProductListToRedis(productList);

        return resultBean;
    }
}
