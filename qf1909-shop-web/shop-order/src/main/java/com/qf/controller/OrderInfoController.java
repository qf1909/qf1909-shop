package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OrderInfoController {

    @GetMapping("/orderinfo")
    public String getIndex(){
        return "orderinfo";
    }

}
