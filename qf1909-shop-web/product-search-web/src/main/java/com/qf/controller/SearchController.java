package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.dto.TProductSearchDTO;
import com.qf.service.ISearchService;
import com.qf.vo.TProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public String searchByKeyword(String keyword,Integer pageNo,Model model){
        if (pageNo == null){
            pageNo = 1;
        }
        TProductSearchVo tProductSearchVo = searchService.searchByKeyword(keyword,pageNo);
        model.addAttribute("products",tProductSearchVo.getList());
        model.addAttribute("pageNo",tProductSearchVo.getPageNo());
        model.addAttribute("search","search");
        model.addAttribute("keyword",keyword);
        model.addAttribute("url","/search/searchByKeyword");
        return "search";
    }

    @RequestMapping("addProToSolr")
    @ResponseBody
    public String addProToSolr(){
        ResultBean resultBean = searchService.addProduct();

        return resultBean.getMessage();
    }


    @RequestMapping("searchAll")
    public String searchAll(Model model,Integer pageNo){
        TProductSearchVo tProductSearchVo = searchService.searchAll(pageNo);
        model.addAttribute("products",tProductSearchVo.getList());
        model.addAttribute("pageNo",pageNo);
        return "search";
    }



}
