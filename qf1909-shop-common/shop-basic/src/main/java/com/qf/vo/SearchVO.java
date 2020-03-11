package com.qf.vo;

import com.qf.dto.ProductSearchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVO implements Serializable {
    private List<ProductSearchDTO> list;
    private  long pageNum;

}
