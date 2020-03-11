package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smj
 * @date 2020/3/10 - 23:07
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("searchByKeyword")
    @ResponseBody
    public ResultBean searchByKeyword(String keyword){
        ResultBean resultBean = searchService.selectByKeyword(keyword);

        return resultBean;
    }

    @RequestMapping("addProToSolr")
    @ResponseBody
    public String addProToSolr(){
        ResultBean resultBean = searchService.addProduct();

        return resultBean.getMessage();
    }


}
