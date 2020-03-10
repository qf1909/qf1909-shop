package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.dto.TProductSearchDTO;
import com.qf.mapper.search.SearchMapper;
import com.qf.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService {

   @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean queryAllToSolr() {
        List<TProductSearchDTO> productList = searchMapper.queryAllProductToSolr();
        List<SolrInputDocument> list = new ArrayList<>();
        for (TProductSearchDTO product : productList) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",product.getId());
            doc.setField("t_product_name",product.getTProductName());
            doc.setField("t_product_sale_price",product.getTProductSalePrice().floatValue());
            doc.setField("t_product_pimage",product.getTProductPimage());
            doc.setField("t_product_pdesc",product.getTProductPdesc());
            list.add(doc);
        }
        try {
            solrClient.add(list);
            solrClient.commit();
          return   ResultBean.success("插入solr库成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBean.error("插入solr库失败");
    }
}
