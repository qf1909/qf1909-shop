package com.qf.mapper.product;

import com.qf.entity.TProductStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStoreMapper {

    List<TProductStore> insertStoreToRedis();


    void subtractStore(@Param("pid") long pid,@Param("pcount") Integer pcount);


    TProductStore  selectStoreBySingle(@Param("pid") long pid);
}
