package com.qf.controller;

import com.qf.bean.ResultBean;

import com.qf.dto.ProductSearchDTO;

import com.qf.service.ISearchService;
import com.qf.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
    public class SearchController {

        @Autowired
        private ISearchService searchService;

        //调用服务---添加数据到solr库中
        @RequestMapping("search/add")
        @ResponseBody
        public ResultBean addProductToSolr() {
            return searchService.addProductToSolr();
        }

        //调用服务---向solr库做站内搜索
        @RequestMapping("search/query")
        public String querySolrByKeyword(@RequestParam String keyword, @RequestParam(value = "page",required = false) Integer page, Model model) {
            if (page ==null){
                page = 1;
            }
            SearchVO searchVO = searchService.searchSolrByKeyword(keyword,page);
            List<ProductSearchDTO> list = searchVO.getList();
            long pageNum = searchVO.getPageNum();
            String url = "search/query";
            model.addAttribute("productList",list);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("pageNo",page);
            model.addAttribute("url",url);
            model.addAttribute("keyword",keyword);
            return "search";
        }

        //调用服务---初始化查询数据
        @GetMapping("search/all")
        public  String  getSearch(Model model,@RequestParam(value = "page",required = false) Integer page){
            if (page ==null){
                page = 1;
            }
            SearchVO searchVO = searchService.queryAllSolr(page);
            List<ProductSearchDTO> list = searchVO.getList();
            long pageNum = searchVO.getPageNum();
            String url = "search/all";
            model.addAttribute("productList",list);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("pageNo",page);
            model.addAttribute("url",url);
            return "search";
        }
    }
