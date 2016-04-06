package com.github.chanming.jdbc;

import java.io.Serializable;

/**
 * Description: 
 * Create Date:2016年4月6日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class Test implements Serializable
{

    private static final long serialVersionUID = -2815848724042470263L;

    private Integer id;
    private String name;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
