package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.service.ISearchService;
import com.qf.vo.TProductSearchVo;
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
@Controller
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("searchByKeyword")
    @ResponseBody
    public TProductSearchVo searchByKeyword(@RequestParam String keyword,@RequestParam Integer pageNo){
        TProductSearchVo tProductSearchVo = searchService.selectByKeyword(keyword,pageNo);
        return tProductSearchVo;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultBean add(){
        ResultBean resultBean = searchService.addProduct();
        return resultBean;
    }

    @RequestMapping("searchAll")
    @ResponseBody
    public TProductSearchVo searchAll(@RequestParam Integer pageNo){
        TProductSearchVo tProductSearchVo = searchService.searchAllFromSolr(pageNo);
        return tProductSearchVo;
    }

}
