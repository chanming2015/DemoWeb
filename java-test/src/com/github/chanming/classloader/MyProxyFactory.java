package com.github.chanming.classloader;

import java.lang.reflect.Proxy;

/**
 * Description: 
 * Create Date:2016年6月21日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class MyProxyFactory
{

    public static Object getProxy(Object target)
    {
        MyInvocationHandler handler = new MyInvocationHandler(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass()
                .getInterfaces(), handler);
    }
}
