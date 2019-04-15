package com.github.chanming2015.rewrite.service;

import com.github.chanming2015.rewrite.annotation.MyService;

@MyService
public class OrderService   {
    public String goBuy(String orderId){
        return "orderId===="+orderId;
    }
}
