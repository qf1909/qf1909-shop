package com.qf.handler;

import com.qf.bean.Orderdetail;
import com.qf.bean.ResultBean;
import com.qf.constant.RabbitConstant;
import com.qf.entity.TProductStore;
import com.qf.mapper.product.ProductStoreMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubtractStore {

   @Autowired
    private ProductStoreMapper productStoreMapper;

    @RabbitListener(queues = RabbitConstant.SUBTRACT_STORE_QUEUE)
     public void subtractStore(List<Orderdetail> orderdetailList){
        for (Orderdetail orderdetail : orderdetailList) {
            long productID = orderdetail.getProductID();
            Integer productNum = orderdetail.getNumber();
            TProductStore store = productStoreMapper.selectStoreBySingle(productID);//查商品库存
            Integer remainNum =  store.getPcount()-productNum;
            productStoreMapper.subtractStore(productID,remainNum);//更改库存
        }
        System.out.println("减库存成功!");
     }
}
