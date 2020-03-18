package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.service.OrderService;
import com.qf.bean.Order;
import com.qf.bean.Orderdetail;
import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TProduct;
import com.qf.entity.TProductStore;
import com.qf.entity.TUser;
import com.qf.util.StringUtil;
import com.qf.vo.ProductCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class OrderController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderService orderService;

    @GetMapping("orderConfirm")
    public String getOrderConfirm(@CookieValue(name = CookieConstant.USER_LOGIN)String uuid,Model model){
        String loginRedisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
        Object o = redisTemplate.opsForValue().get(loginRedisKey);
        TUser user = JSON.parseObject(JSON.toJSONString(o), TUser.class);
        Long id = user.getId();
        String orderRedisKey = StringUtil.getRedisKey(RedisConstant.PRODUCT_ORDER_PRE, id.toString());
        List<ProductCartVO> orderList = (List<ProductCartVO>) redisTemplate.opsForValue().get(orderRedisKey);
        model.addAttribute("orderList",orderList);
        //TODO   查询/新增   配送地址
        return "orderConfirm";
    }
//提交订单
        @RequestMapping("submitOrder")
        public String submitOrder(HttpServletRequest request){
            Object user = request.getAttribute("user");
            return  createOrder(user);
        }

  //创建订单
    private String createOrder(Object o) {
        if (o ==null){
            return  "用户未登录!";
        }
        TUser  user  = (TUser) o;
        String id = Long.valueOf(user.getId()).toString();
        String redisKey = StringUtil.getRedisKey(RedisConstant.PRODUCT_ORDER_PRE, id);
        List<ProductCartVO> orderList = (List<ProductCartVO>) redisTemplate.opsForValue().get(redisKey);
        if (orderList ==null || orderList.size()==0){
            throw  new RuntimeException("用户无创建订单的商品!");
        }
        //判断库存是否足够
        HashMap<Long,Integer> map = new HashMap<>();
        for (ProductCartVO productCartVO : orderList) {
            int count = productCartVO.getCount();
            long pid = productCartVO.getProduct().getPid();
            map.put(pid,count);
        }
        List<TProductStore>  storeList = (List<TProductStore>) redisTemplate.opsForValue().get(RedisConstant.PRODUCT_STORE);
        for (TProductStore store : storeList) {
            Integer orderCount = map.get(store.getPid());
            if (orderCount !=null){
                if (orderCount > store.getPcount()){
                      return "库存不足,重新选购";
                }
            }
        }
        //TODO 获取配送方式
        //创建订单
        Order order = new Order();
        order.setAccount(user.toString());
        order.setQuantity(orderList.size());
        order.setStatus(Order.order_status_init);//订单状态
        order.setPaystatus(Order.order_paystatus_n);//支付状态
        order.setOtherRequirement(null);


        //创建订单明细集合
        List<Orderdetail> orderdetailList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            ProductCartVO productCartVO = orderList.get(i);
            TProduct product = productCartVO.getProduct();
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setProductID((int) product.getPid());
            orderdetail.setPrice(String.valueOf(product.getSalePrice()));//商品现价
            orderdetail.setFee("0");//配送费
            orderdetail.setProductName(product.getPname());
            orderdetail.setNumber(productCartVO.getCount());
            orderdetail.setTotal0(String.valueOf(Double.valueOf(orderdetail.getPrice()) * orderdetail.getNumber()));//订单项小计
            orderdetailList.add(orderdetail);
        }
        //TODO 创建订单并插入到数据库,减库存，使用mq的分布式事务解决方案
        ResultBean resultBean = orderService.createOrder(order,orderdetailList);
        String redisKey1 = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, id);
        //清空购物车
        redisTemplate.delete(redisKey1);
        //部分清空
//        List<ProductCartVO> list = (List<ProductCartVO>) redisTemplate.opsForValue().get(redisKey1);
//        List<ProductCartVO> newList = new ArrayList<>();
//        HashMap<Long,ProductCartVO> orderMap = new HashMap<>();
//        for (ProductCartVO cartVO : orderList) {
//                 orderMap.put(cartVO.getProduct().getPid(),cartVO);
//        }
//        for (ProductCartVO productCartVO : list) {
//            ProductCartVO vo = orderMap.get(productCartVO.getProduct().getPid());
//            if (vo ==null){
//                newList.add(productCartVO);
//            }
//        }
        //重定向支付页面
        return "redirect:/paygate/pay?orderId={1}&orderPayId={2}";
    }
}


