package com.qf.service;

import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author smj
 * @date 2020/3/10 - 23:07
 */
@FeignClient(value = "product-search-service")
public interface ISearchService {

    @RequestMapping("searchByKeyword")
    ResultBean searchByKeyword(@RequestParam String keyword);

    @RequestMapping("add")
    ResultBean addProduct();

    @RequestMapping("searchAll")
    ResultBean searchAll();
}
