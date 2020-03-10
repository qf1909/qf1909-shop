package com.qf.mapper;

import com.qf.bean.TProductSearchDTO;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/10 - 19:40
 */
public interface TProductSearchDTOMapper {

    List<TProductSearchDTO> selectAll();

}
