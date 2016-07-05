package com.github.chanming2015.springtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.chanming2015.springtest.entity.Test;
import com.github.chanming2015.springtest.service.TestService;

@Controller
public class IndexController
{

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login()
    {
        Test t = new Test();
        t.setUsername("admin");
        t.setPassword("root");
        testService.save(t);
        logger.info("to login page");
        return "login";
    }

}
