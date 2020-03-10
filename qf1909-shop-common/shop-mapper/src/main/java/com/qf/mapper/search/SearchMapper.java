package com.qf.mapper.search;


import com.qf.dto.TProductSearchDTO;

import java.util.List;

public interface SearchMapper {

    List<TProductSearchDTO>  queryAllProductToSolr();
}
