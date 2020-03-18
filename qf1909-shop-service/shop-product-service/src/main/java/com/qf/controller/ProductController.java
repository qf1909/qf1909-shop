package com.qf.controller;

import com.qf.entity.TProductType;
import com.qf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("type")
    @ResponseBody
    public List<TProductType> queryProductType(){
        List<TProductType> typeList = productService.queryProductType();
        productService.insertStoreToRedis();
        return typeList;
    }
}
