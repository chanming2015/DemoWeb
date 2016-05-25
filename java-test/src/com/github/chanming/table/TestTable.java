package com.github.chanming.table;

import com.github.chanming2015.common.util.InvokeUtil;

/**
 * Description: 
 * Create Date:2016年5月25日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestTable implements ITableEntity
{

    private String id;
    private String username;
    private String password;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private static final String[] titles = {"id", "username", "password"};

    @Override
    public String[] getTitles()
    {
        return titles.clone();
    }

    @Override
    public String[] getData()
    {
        String[] data = new String[titles.length];
        for (int i = 0; i < data.length; i++)
        {
            Object obj = InvokeUtil.invokeGet(this, titles[i]);
            if (obj != null)
            {
                data[i] = obj.toString();
            }
            else
            {
                data[i] = "";
            }
        }
        return data;
    }
}
