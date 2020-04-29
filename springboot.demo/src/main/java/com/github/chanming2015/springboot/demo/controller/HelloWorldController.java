package com.github.chanming2015.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
public class HelloWorldController
{
    @ApiOperation(value = "Hello World API")
    @ApiResponse(code = 200, message = "Search Success")
    @GetMapping("/hello")
    public String index()
    {
        return "Hello World";
    }
}

