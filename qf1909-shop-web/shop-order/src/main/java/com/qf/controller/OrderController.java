package com.qf.controller;

import com.qf.entity.TUser;
import com.qf.vo.ProductCartVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {


    @GetMapping("orderConfirm")
    public String getOrderConfirm(RedirectAttributes redirectAttributes,Model model){
        Object user = redirectAttributes.getAttribute("user");
        Object cart = redirectAttributes.getAttribute("cart");
//        model.addAttribute("user",user);
//        model.addAttribute("cart",cart);
        System.out.println(user);
        System.out.println(cart);
        return "orderConfirm";
    }


    @GetMapping("/order")
    public String getIndex(){
        return "order";
    }


}
