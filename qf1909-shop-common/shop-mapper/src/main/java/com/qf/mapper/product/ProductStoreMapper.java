package com.qf.mapper.product;

import com.qf.entity.TProductStore;

import java.util.List;

public interface ProductStoreMapper {

    List<TProductStore> insertStoreToRedis();
}
