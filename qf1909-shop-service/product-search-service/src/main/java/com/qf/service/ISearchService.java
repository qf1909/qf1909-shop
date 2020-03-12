package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.dto.TProductSearchDTO;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/11 - 16:25
 */
public interface ISearchService {

    /**
     * 根据关键字从Solr库查询所有的数据
     * @param keyword
     * @return
     */
    ResultBean selectByKeyword(String keyword);

    /**
     * 将数据库的数据插入solr库中
     * @return
     */
    ResultBean addProduct();

    /**
     * 查询Solr库的所有数据
     * @return
     */
    ResultBean searchAllFromSolr();
}
