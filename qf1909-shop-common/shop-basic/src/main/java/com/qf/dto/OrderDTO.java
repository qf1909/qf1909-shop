package com.qf.dto;

import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PipedReader;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private Order order;
     private List<Orderdetail> orderdetailList;
}
