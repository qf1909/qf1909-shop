package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smj
 * @date 2020/3/11 - 16:30
 */
@RestController
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("searchByKeyword")
    @ResponseBody
    public ResultBean searchByKeyword(@RequestParam String keyword){
        ResultBean resultBean = searchService.selectByKeyword(keyword);
        return resultBean;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultBean add(){
        ResultBean resultBean = searchService.addProduct();
        return resultBean;
    }

    @RequestMapping("searchAll")
    @ResponseBody
    public ResultBean searchAll(){
        ResultBean resultBean = searchService.searchAllFromSolr();
        return resultBean;
    }

}
