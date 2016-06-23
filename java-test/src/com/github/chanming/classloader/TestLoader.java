package com.github.chanming.classloader;

/**
 * Description: 
 * Create Date:2016年5月24日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestLoader
{

    public static void main(String[] args)
    {
        // for (String string : args)
        // {
        // System.out.println("运行参数：" + string);
        // }

        Person s = (Person) MyProxyFactory.getProxy(new Student());
        s.walk();
        s.say("hehe");

    }
}
