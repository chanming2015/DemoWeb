package com.github.chanming2015.springtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController
{

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login()
    {
        logger.info("to login page");
        return "login";
    }

}
