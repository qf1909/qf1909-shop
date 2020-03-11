package com.qf.mapper.search;


import com.qf.dto.ProductSearchDTO;

import java.util.List;

public interface SearchMapper {

    List<ProductSearchDTO>  queryAllProductToSolr();
}
