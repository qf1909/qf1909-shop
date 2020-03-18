package com.qf.service;

import com.qf.bean.Order;
import org.springframework.ui.ModelMap;

public interface IOrderService {

    String insertAndPay(String uuid);

}
