package com.github.chanming.annotation;

import java.lang.reflect.Method;

/**
 * Description: 
 * Create Date:2016年4月7日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class Test
{

    private String name;

    private String pass;

    @ShowValue(value = "root")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @ShowValue(value = "admin")
    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public static void main(String[] args)
    {
        Test t = new Test();
        Method[] methods = t.getClass().getMethods();
        for (Method method : methods)
        {
            ShowValue sv = method.getAnnotation(ShowValue.class);
            if (sv != null)
            {
                System.out.println(sv.value());
            }
        }
    }
}
