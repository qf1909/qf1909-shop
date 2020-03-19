package com.qf.dto;

import com.qf.bean.Orderdetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO  implements Serializable {

    private  Integer orderId;

    private List<Orderdetail> orderdetailList;
}
