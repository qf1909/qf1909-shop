package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.dto.ProductSearchDTO;
import com.qf.service.ISearchService;
import com.qf.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

    /*
     * 1.查询数据库.将数据库插入到索引库中
     *
     */
    @Autowired
    private ISearchService searchService;

    @RequestMapping("add")
    @ResponseBody
       public  ResultBean  addProductToSolr(){
        ResultBean resultBean = searchService.queryAllToSolr();
        return  resultBean;
    }

    /*
    搜索
     */
    @RequestMapping("query")
    @ResponseBody
    public   SearchVO searchSolrByKeyword(String  keyword,Integer page){
        SearchVO searchVO  = searchService.querySolrByKeyword(keyword,page);
        return searchVO;
    }

    /*
    初始化
     */
    @RequestMapping("all")
    @ResponseBody
    public SearchVO queryAllSolr(Integer page){
        SearchVO searchVO = searchService.queryAllSolr(page);
        return searchVO;
    }
}
