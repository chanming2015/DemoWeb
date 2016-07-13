package com.github.chanming.designmodel.strategy;

/**
 * Description: 
 * Create Date:2016年7月13日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class SimpleDuck extends Duck
{

    public SimpleDuck()
    {
        setFlyBehavior(new FlyBehaviorA());
        setQuackBehavior(new QuackBehaviorA());
    }

    /** @author XuMaoSen
     */
    @Override
    protected String display()
    {
        return performFly() + "----" + performQuack();
    }

    public static void main(String[] args)
    {
        Duck duck = new SimpleDuck();
        System.out.println(duck.display());
    }

}
