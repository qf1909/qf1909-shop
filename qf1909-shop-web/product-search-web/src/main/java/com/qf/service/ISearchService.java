package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.vo.TProductSearchVo;
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
    TProductSearchVo searchByKeyword(@RequestParam String keyword,@RequestParam Integer pageNo);

    @RequestMapping("add")
    ResultBean addProduct();

    @RequestMapping("searchAll")
    TProductSearchVo searchAll(@RequestParam Integer pageNo);
}
