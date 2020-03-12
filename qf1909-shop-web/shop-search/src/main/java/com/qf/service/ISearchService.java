package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.dto.ProductSearchDTO;
import com.qf.vo.SearchVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient("shop-search-service")
public interface ISearchService {

    @RequestMapping("add")
    ResultBean addProductToSolr();

    @RequestMapping("query")
    SearchVO searchSolrByKeyword(@RequestParam String  keyword,@RequestParam Integer page);

    @RequestMapping("all")
    SearchVO queryAllSolr(@RequestParam Integer page);

}
