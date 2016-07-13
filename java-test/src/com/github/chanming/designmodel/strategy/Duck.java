package com.github.chanming.designmodel.strategy;

/**
 * Description: 
 * Create Date:2016年7月13日
 * @author XuMaoSen
 * Version:1.0.0
 */
public abstract class Duck
{
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior)
    {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior)
    {
        this.quackBehavior = quackBehavior;
    }

    public String performFly()
    {
        return flyBehavior.fly();
    }

    public String performQuack()
    {
        return quackBehavior.quack();
    }

    protected abstract String display();

}
