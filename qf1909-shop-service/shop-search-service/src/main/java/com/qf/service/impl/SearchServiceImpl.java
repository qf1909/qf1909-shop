package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.dto.ProductSearchDTO;
import com.qf.mapper.search.SearchMapper;
import com.qf.service.ISearchService;
import com.qf.vo.SearchVO;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements ISearchService {

   @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean queryAllToSolr() {
        List<ProductSearchDTO> productList = searchMapper.queryAllProductToSolr();
        List<SolrInputDocument> list = new ArrayList<>();
        for (ProductSearchDTO product : productList) {
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

    @Override
    public SearchVO querySolrByKeyword(String keyword,Integer page) {
        long pageNum =0;
        SolrQuery query =new SolrQuery();
        query.setQuery(keyword); //关键词
        query.set("df","t_item_keywords"); //复制域
        query.addHighlightField("t_product_name");//添加高亮字段
        query.setHighlight(true);//开启高亮
        query.setStart((page-1)*10);//分页首页
        query.setRows(10);//分页尾页
        query.setHighlightSimplePre("<span style='color:red'>"); //高亮前缀
        query.setHighlightSimplePost("</span>"); //高亮后缀
        List<ProductSearchDTO> list = new ArrayList<>();
        SearchVO searchVO = new SearchVO();
        QueryResponse response = null;//执行查询
        try {
            response = solrClient.query(query);
            SolrDocumentList results = response.getResults();//获取非高亮查询
            long num = results.getNumFound();
            if (num%10==0){
                pageNum = num/10;
            }else{
                pageNum =num/10+1;
            }
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();//获取高亮字段
            for (SolrDocument result : results) {
                //存放id
                ProductSearchDTO product = new ProductSearchDTO();
                String  idStr = (String) result.getFieldValue("id");
                product.setId(Long.parseLong(idStr));
                //存放高亮name
                Map<String, List<String>> stringListMap = highlighting.get(idStr);
                List<String> stringList = stringListMap.get("t_product_name");
                String productName = stringList.get(0);
                product.setTProductName(productName);
                //存放salePrice
                Double tProductSalePrice = (Double) result.getFieldValue("t_product_sale_price");
                product.setTProductSalePrice(new BigDecimal(tProductSalePrice));
                //存放image
                String tProductPimage = (String) result.getFieldValue("t_product_pimage");
                product.setTProductPimage(tProductPimage);
                //存放desc
                String tProductPdecs = (String) result.getFieldValue("t_product_pdecs");
                product.setTProductPdesc(tProductPdecs);
                list.add(product);
            }
            searchVO.setList(list);
            searchVO.setPageNum(pageNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchVO;
    }

    @Override
    public SearchVO  queryAllSolr(Integer page) {
        long pageNum =0;
        SolrQuery query =new SolrQuery();
        query.setQuery("*:*"); //关键词
        query.setStart((page-1)*10);//分页首页
        query.setRows(10);//分页尾页
        List<ProductSearchDTO> list = new ArrayList<>();
        QueryResponse response = null;//执行查询
        SearchVO searchVO = new SearchVO();
        try {
            response = solrClient.query(query);
            SolrDocumentList results = response.getResults();//获取非高亮查询
            long num = results.getNumFound();
            if (num%10==0){
                pageNum = num/10;
            }else{
                pageNum =num/10+1;
            }
            for (SolrDocument result : results) {
                //存放id
                ProductSearchDTO product = new ProductSearchDTO();
                String  idStr = (String) result.getFieldValue("id");
                product.setId(Long.parseLong(idStr));
                String tProductName = (String) result.getFieldValue("t_product_name");
                product.setTProductName(tProductName);
                //存放salePrice
                Double tProductSalePrice = (Double) result.getFieldValue("t_product_sale_price");
                product.setTProductSalePrice(new BigDecimal(tProductSalePrice));
                //存放image
                String tProductPimage = (String) result.getFieldValue("t_product_pimage");
                product.setTProductPimage(tProductPimage);
                //存放desc
                String tProductPdecs = (String) result.getFieldValue("t_product_pdecs");
                product.setTProductPdesc(tProductPdecs);
                list.add(product);
            }
            searchVO.setList(list);
            searchVO.setPageNum(pageNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchVO;
    }
}
