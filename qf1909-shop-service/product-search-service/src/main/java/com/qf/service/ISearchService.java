package com.qf.service;

import com.qf.bean.ResultBean;

/**
 * @author smj
 * @date 2020/3/10 - 20:36
 */
public interface ISearchService {

    /**
     * 根据关键字查询
     * @param keyword
     * @return
     */
    public ResultBean selectByKeyword(String keyword);

    /**
     * 将商品添加至Solr库
     */
    public ResultBean addProduct();

}
