package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.controller.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("insert")
    @ResponseBody
    public ResultBean addProductToCart(String uuid, Long  productId,int count){
        ResultBean resultBean = cartService.addProductToCart(uuid, productId, count);
        return resultBean;
    }
}
