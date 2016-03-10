package com.github.chanming.collection;

/**
 * @author XuMaoSen
 * Create Date:2016年3月4日 下午9:59:35
 * Description:
 * Version:1.0.0
 */
public class R
{
    public int count;

    /** @author XuMaoSen
     */
    public R(int count)
    {
        this.count = count;
    }

    /** @author XuMaoSen
     */
    @Override
    public int hashCode()
    {
        return this.count;
    }

    /** @author XuMaoSen
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof R)
        {
            R r = (R) obj;
            if (r.count == this.count)
            {
                return true;
            }
        }
        return false;
    }

    /** @author XuMaoSen
     */
    @Override
    public String toString()
    {
        return "R(" + hashCode() + ")" + "(" + this.count + ")";
    }
}
