package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    @ResponseBody
    public ResultBean  addProductToSolr(){
        return searchService.addProductToSolr();
    }

}
