package com.qf.service;

import com.qf.bean.ResultBean;
import com.qf.entity.TProductType;

import java.util.List;

public interface IIndexService {

    ResultBean  checksLogin(String uuid);

    List<TProductType>  queryProductType();

}
