package com.qf.mapper.order;


import com.qf.entity.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper {
//    long countByExample(TOrderExample example);
//
//    int deleteByExample(TOrderExample example);
//
//    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

//    List<TOrder> selectByExample(TOrderExample example);

//    TOrder selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);
//
//    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);
//
//    int updateByPrimaryKeySelective(TOrder record);
//
//    int updateByPrimaryKey(TOrder record);
}