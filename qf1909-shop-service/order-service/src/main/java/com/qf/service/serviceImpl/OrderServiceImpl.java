package com.qf.service.serviceImpl;

import com.qf.bean.CartInfo;
import com.qf.bean.Order;
import com.qf.constant.RabbitConstant;
import com.qf.entity.TOrder;
import com.qf.entity.TOrderdetail;
import com.qf.entity.TProduct;
import com.qf.entity.TUser;
import com.qf.service.IOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 创建订单，并跳转到支付页面让用户进行支付
     * 组装订单数据
     *
     * @return
     * @throws Exception
     */
    public String insertAndPay( ) {

        //TODO 获取用户名
        UUID uuid = (UUID) redisTemplate.opsForValue().get("user_uuid");
        String userId ="xiaoming";
//        if (user == null) {
//            return "当前用户没有登录";
//        }

        //TODO 从Redis中获取用户购买的商品列表
        CartInfo cartInfo = (CartInfo) redisTemplate.opsForValue().get("cartinfo"+uuid);

        if (cartInfo == null || cartInfo.getProductList().size() == 0) {
            throw new NullPointerException("购物车中没有可支付的商品!");
        }
        //TODO 检测商品是否都有库存,如果没有库存需要提醒用户
        //TODO 库存不足，则提示用户某些商品的库存不足，请重新选购
        //TODO 获取配送方式

        //创建订单
        TOrder order = new TOrder();
        order.setAccount(userId);  //用户名
        order.setQuantity(cartInfo.getProductList().size()); //商品数量
        order.setStatus(Order.order_status_init);    //订单状态
        order.setPaystatus(Order.order_paystatus_n);  //支付状态
//        order.setOtherRequirement(e.getOtherRequirement());  //附加要求
        order.setOtherRequirement("1232");

        //创建订单明细
        LinkedList<TOrderdetail> orderDetailList = new LinkedList<>();
        for (int i = 0; i < cartInfo.getProductList().size(); i++) {
            TProduct product = cartInfo.getProductList().get(i);
            TOrderdetail orderdetail = new TOrderdetail();
            orderdetail.setProductId(product.getPid());
            orderdetail.setPrice(product.getSalePrice());
            orderdetail.setFee(0.0);   //配送费
            orderdetail.setProductName(product.getPname());
            orderdetail.setTotal0(product.getSalePrice() * orderdetail.getNumber());  //订单小计
            orderDetailList.add(orderdetail);
        }

        if (orderDetailList.size() == 1) {
            order.setRemark(orderDetailList.get(0).getProductName());
        } else {
            order.setRemark("一共" + orderDetailList.size() + "笔订单");
        }

        cartInfo.totalCacl();  //订单总金额
        order.setExpressCode(null); //配送方式
        order.setExpressCompanyName(null);  //配送公司名
        order.setFee(0.0);// 配送费

    //TODO 封装配送地址信息
    //TODO 创建订单并插入到数据库,减库存，使用mq的分布式事务解决方案

        List list = new ArrayList();
        list.add(userId);
        list.add(order);
        list.add(orderDetailList);
        rabbitTemplate.convertAndSend(RabbitConstant.ORDER_EXCHANGE,"",list);
    //Order orderData=orderService.createOrder(order, orderdetailList);

    //TODO 清空购物车
        return"redirect:http://localhost:9085/pay?orderId={1}&orderPayId={2}";
}
}
