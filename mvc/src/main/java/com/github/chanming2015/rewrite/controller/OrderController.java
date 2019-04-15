package com.github.chanming2015.rewrite.controller;

import com.github.chanming2015.rewrite.annotation.MyAutowrited;
import com.github.chanming2015.rewrite.annotation.MyController;
import com.github.chanming2015.rewrite.annotation.MyRequestMapping;
import com.github.chanming2015.rewrite.service.OrderService;

@MyController
@MyRequestMapping("/test")
public class OrderController   {
    @MyAutowrited
    OrderService orderService;

    @MyRequestMapping("/ok")
    public String buy()
    {
        return orderService.goBuy("12345678");
    }
}
