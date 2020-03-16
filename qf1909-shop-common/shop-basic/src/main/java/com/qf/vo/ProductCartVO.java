package com.qf.vo;

import com.qf.entity.TProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartVO implements Serializable {
    private  TProduct product;
    private  int count;
    private double totalPrice;
    private Date updateTime;
}
