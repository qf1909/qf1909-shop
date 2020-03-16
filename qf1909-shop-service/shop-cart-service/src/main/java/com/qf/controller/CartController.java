package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ICartService;
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
    //清空购物车
    @RequestMapping("delete")
    @ResponseBody
    public  ResultBean  cleanCart(String uuid){
        ResultBean resultBean = cartService.cleanCart(uuid);
        return resultBean;
    }

    @RequestMapping("update")
    @ResponseBody
    public  ResultBean updateCart(String uuid,Long productId ,int count){
        ResultBean resultBean = cartService.updateCart(uuid, productId, count);
        return  resultBean;
    }


    @RequestMapping("select")
    @ResponseBody
    public  ResultBean showCart(String uuid){
        ResultBean resultBean = cartService.showCart(uuid);
        return  resultBean;
    }

    @RequestMapping("merge")
    @ResponseBody
    public ResultBean mergeCart(String uuid ,String userId){
        return  cartService.mergeCart(uuid, userId);
        }
}
