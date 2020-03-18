package com.qf.controller;



import com.qf.bean.Order;
import com.qf.bean.ResultBean;
import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.entity.TUser;
import com.qf.service.IOrderService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class OrderConfirmController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/orderConfirm")
    public String getIndex(){
        return "orderConfirm";
    }

    /**
     * 点击 创建订单 按钮
     * @param e
     * @param model
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "creatOrder", method = RequestMethod.POST)
    @GetMapping("creatOrder")
    public String creatOrder(Order e, ModelMap model, HttpServletRequest request) throws Exception{
        Object user = request.getAttribute("user");
        //验证我当前是否已登录
        Cookie[] cookies = request.getCookies();
        Object o=null;
        String uuid ="";
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(CookieConstant.USER_LOGIN.equals(cookie.getName())){
                    //找到这个cookie
                    //拿到cookie的值，组织redis的键
                    uuid = cookie.getValue();
                    String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
                    o = redisTemplate.opsForValue().get(redisKey);
                    if(o ==null){
                        return "用户未登录";
                    }
                }
            }
        }
        TUser tUser = new TUser();
        tUser.setUname("xiaoming");
        tUser.setPassword("231321");
        redisTemplate.opsForValue().set(CookieConstant.USER_LOGIN+uuid,tUser);
        //TODO 从cookies获取用户名
//        UUID uuid = (UUID) redisTemplate.opsForValue().get("user_uuid");
//        String userId ="xiaoming";
//        if (user == null) {
//            return "当前用户没有登录";
//        }

        //TODO 从Redis中获取用户购买的商品列表

//        CartInfo cartInfo =null;
//        if(cartInfo ==null || cartInfo.getProductList().size() == 0){
//            return "购物车中没有商品";
//        }
//        //TODO 检测商品是否都有库存,如果没有库存需要提醒用户
//        //TODO 库存不足，则提示用户某些商品的库存不足，请重新选购
//        //TODO 获取配送方式

        return  orderService.creatOrder(uuid);
    }

}
