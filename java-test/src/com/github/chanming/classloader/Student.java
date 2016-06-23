package com.github.chanming.classloader;

/**
 * Description: 
 * Create Date:2016年6月21日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class Student implements Person
{

    /** @author XuMaoSen
     */
    @Override
    public void walk()
    {
        System.out.println("walk");
    }

    /** @author XuMaoSen
     */
    @Override
    public void say(String name)
    {
        System.out.println("say " + name);
    }

}
