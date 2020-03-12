package com.qf.controller;

import com.qf.bean.ResultBean;
import com.qf.dto.TProductSearchDTO;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public ResultBean searchByKeyword(String keyword){
        ResultBean resultBean = searchService.searchByKeyword(keyword);

        return resultBean;
    }

    @RequestMapping("addProToSolr")
    @ResponseBody
    public String addProToSolr(){
        ResultBean resultBean = searchService.addProduct();

        return resultBean.getMessage();
    }


    @RequestMapping("searchAll")
    public String searchAll(Model model){
        ResultBean resultBean = searchService.searchAll();
        List<TProductSearchDTO> tProductSearchDTOS = (List<TProductSearchDTO>) resultBean.getData();
        model.addAttribute("tProductSearchDTOS",tProductSearchDTOS);
        return "search";
    }



}
