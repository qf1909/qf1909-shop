package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.dto.ProductSearchDTO;
import com.qf.vo.SearchVO;



public interface ISearchService {
       ResultBean queryAllToSolr();

    SearchVO querySolrByKeyword(String keyword,Integer page);

    SearchVO queryAllSolr(Integer page);
}
