package com.github.chanming2015.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.chanming2015.springtest.entity.Test;
import com.github.chanming2015.springtest.repository.TestRepository;
import com.github.chanming2015.springtest.service.TestService;

/**
 * Description: 
 * Create Date:2016年7月5日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Service
public class TestServiceImpl implements TestService
{
    @Autowired
    private TestRepository testRepository;

    @Override
    public Test save(Test t)
    {
        return testRepository.save(t);
    }

}
