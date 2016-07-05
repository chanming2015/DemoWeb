package com.github.chanming2015.springtest.entity;

import javax.persistence.Entity;

/**
 * Description: 
 * Create Date:2016年7月5日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Entity
public class Test extends BaseEntity
{

    private static final long serialVersionUID = 7411796070485700889L;

    private String username;
    private String password;

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

}
