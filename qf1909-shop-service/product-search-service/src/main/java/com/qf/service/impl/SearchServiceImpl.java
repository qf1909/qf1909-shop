package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.dto.TProductSearchDTO;
import com.qf.mapper.search.SearchMapper;
import com.qf.service.ISearchService;
import com.qf.vo.TProductSearchVo;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author smj
 * @date 2020/3/10 - 20:42
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public TProductSearchVo selectByKeyword(String keyword,Integer pageNo) {
        if (pageNo == null){
            pageNo = 1;
        }
        // 1->0  2->11 3->23
        Integer pageNum = (pageNo-1)*12;
        // 创建查询对象
        SolrQuery query = new SolrQuery();
        query.set("df","t_item_keywords");
        query.setQuery(keyword);
        // 分页
        query.setStart(pageNum);
        query.setRows(12);
        // 高亮
        query.setHighlight(true);
        query.addHighlightField("t_product_name");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");

        TProductSearchVo tProductSearchVo = new TProductSearchVo();

        try {
            QueryResponse response = solrClient.query(query);

            // 用来存放查询出来的商品结果
            List<TProductSearchDTO> tProductSearchDTOS = new ArrayList<>();

            // 获得数据结果集
            SolrDocumentList results = response.getResults();
            // 获得高亮结果集
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : results){
                TProductSearchDTO product = new TProductSearchDTO();
                String StringId = (String) document.getFieldValue("id");
                long id = Long.parseLong(StringId);
                product.setId(id);

                // 获得高亮的t_product_name
                Map<String, List<String>> stringListMap = highlighting.get(StringId);
                List<String> t_product_name1 = stringListMap.get("t_product_name");
                String t_product_name = t_product_name1.get(0);
                product.setTProductName(t_product_name);

                Double t_product_sale_price = (Double) document.getFieldValue("t_product_sale_price");
                product.setTProductSalePrice(new BigDecimal(t_product_sale_price));

                String p_product_pimage = (String) document.getFieldValue("p_product_pimage");
                product.setTProductPimage(p_product_pimage);

                String p_product_pdesc = (String) document.getFieldValue("p_product_pdesc");
                product.setTProductPdesc(p_product_pdesc);

                tProductSearchDTOS.add(product);

            }

            tProductSearchVo.setList(tProductSearchDTOS);
            tProductSearchVo.setPageNo(pageNo);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tProductSearchVo;
    }

    /**
     * 将商品添加至solr库中
     * @return
     */
    @Override
    public ResultBean addProduct() {
        // 从数据库查出要插入Solr库的数据
        List<TProductSearchDTO> tProductSearchDTOS = searchMapper.queryAllProductToSolr();

        // 存放所有doc的集合
        List<SolrInputDocument> docs = new ArrayList<>();

        // 遍历tProductSearchDTOs集合，将每一个product对象封装成一个SolrInputDocument对象
        for (TProductSearchDTO tProductSearchDTO : tProductSearchDTOS){
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",tProductSearchDTO.getId());
            doc.setField("t_product_name",tProductSearchDTO.getTProductName());
            doc.setField("t_product_sale_price",tProductSearchDTO.getTProductSalePrice().floatValue());
            doc.setField("t_product_pimage",tProductSearchDTO.getTProductPimage());
            doc.setField("t_product_pdesc",tProductSearchDTO.getTProductPdesc());
            // 将SolrInputDocument对象存入集合中
            docs.add(doc);
        }

        // 将集合添加到Solr库中
        try {
            solrClient.add(docs);
            solrClient.commit();
            return ResultBean.success("插入搜索库成功！");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ResultBean.error("插入搜索库失败！");
    }

    @Override
    public TProductSearchVo searchAllFromSolr(Integer pageNo) {
        if (pageNo == null){
            pageNo = 1;
        }
        // 1->0  2->11 3->23
        Integer pageNum = (pageNo-1)*12;
        // 创建查询对象
        SolrQuery query = new SolrQuery();
        query.set("df","t_item_keywords");
        query.setQuery("*:*");
        // 分页
        query.setStart(pageNum);
        query.setRows(12);

        TProductSearchVo tProductSearchVo = new TProductSearchVo();
        try {
            QueryResponse response = solrClient.query(query);

            // 用来存放查询出来的商品结果
            List<TProductSearchDTO> tProductSearchDTOS = new ArrayList<>();

            // 获得数据结果集
            SolrDocumentList results = response.getResults();

            for (SolrDocument document : results){
                TProductSearchDTO product = new TProductSearchDTO();

                String StringId = (String) document.getFieldValue("id");
                long id = Long.parseLong(StringId);
                product.setId(id);

                String t_product_name = (String) document.getFieldValue("t_product_name");
                product.setTProductName(t_product_name);

                Double t_product_sale_price = (Double) document.getFieldValue("t_product_sale_price");
                product.setTProductSalePrice(new BigDecimal(t_product_sale_price));

                String p_product_pimage = (String) document.getFieldValue("p_product_pimage");
                product.setTProductPimage(p_product_pimage);

                String p_product_pdesc = (String) document.getFieldValue("p_product_pdesc");
                product.setTProductPdesc(p_product_pdesc);

                tProductSearchDTOS.add(product);

            }

            tProductSearchVo.setList(tProductSearchDTOS);
            tProductSearchVo.setPageNo(pageNo);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tProductSearchVo;
    }



}
