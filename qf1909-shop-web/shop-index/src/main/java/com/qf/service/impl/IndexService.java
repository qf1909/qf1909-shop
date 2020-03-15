package com.qf.service.impl;

import com.qf.bean.ResultBean;
import com.qf.entity.TProductType;
import com.qf.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IndexService implements IIndexService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResultBean checksLogin(String uuid) {
        String url = "http://shop-user-service/csLogin?uuid="+uuid;
        ResultBean resultBean = restTemplate.getForObject(url,ResultBean.class);
        return resultBean;
    }

    @Override
    public List<TProductType> queryProductType() {
        String url = "http://shop-product-service/type";
        List typeList = restTemplate.getForObject(url, List.class);
        return typeList;
    }

}
