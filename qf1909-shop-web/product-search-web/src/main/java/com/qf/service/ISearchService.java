package com.qf.service;

import com.qf.bean.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author smj
 * @date 2020/3/10 - 23:07
 */
@FeignClient("product-search-service")
public interface ISearchService {

    public ResultBean selectByKeyword(String keyword);

    public ResultBean addProduct();
}
