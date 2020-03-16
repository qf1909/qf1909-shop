package com.qf.mapper.product;

import com.qf.entity.TProduct;
import org.apache.ibatis.annotations.Param;



public interface ProductCartMapper {

   TProduct queryProductDetailOfCart(@Param("pid") Long  pid);
}
