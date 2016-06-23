package com.github.chanming.classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description: 
 * Create Date:2016年6月21日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class MyInvocationHandler implements InvocationHandler
{

    private final Object target;

    /** @author XuMaoSen
     */
    public MyInvocationHandler(Object target)
    {
        this.target = target;
    }

    /** @author XuMaoSen
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {

        System.out.println("before");
        Object obj = method.invoke(target, args);
        System.out.println("after");
        return obj;
    }

}
