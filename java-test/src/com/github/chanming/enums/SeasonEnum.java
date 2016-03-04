package com.github.chanming.enums;

/**
 * @author XuMaoSen
 * Create Date:2016年3月3日 下午9:13:20
 * Description:
 * Version:1.0.0
 */
public enum SeasonEnum
{
    SPRING("春"), SUMMER("夏"), FALL("秋"), WINTER("冬");

    private String name;

    private SeasonEnum(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
