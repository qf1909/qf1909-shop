package com.qf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TProductSearchDTO implements Serializable {

    private  Long  id;
    private  String  tProductName;
    private BigDecimal tProductSalePrice;
    private  String tProductPimage;
    private  String tProductPdesc;

}
