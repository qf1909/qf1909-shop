package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    /*
     * 1.查询数据库
     * 2.将数据库插入到索引库中
     * 3.搜索  查询索引库并显示高亮
     */
    @Autowired
    private ISearchService searchService;

    @RequestMapping("add")
    @ResponseBody
       public  ResultBean  addProductToSolr(){
        ResultBean resultBean = searchService.queryAllToSolr();
        return  resultBean;
    }

}
