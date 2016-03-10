package com.github.chanming.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author XuMaoSen
 * Create Date:2016年3月4日 下午9:05:56
 * Description:
 * Version:1.0.0
 */
public class TestList
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        new Thread("AA")
        {
            /** @author XuMaoSen
             */
            @Override
            public void run()
            {
                while (true)
                {
                    list.add("xx");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }.start();

        new Thread("BB")
        {
            /** @author XuMaoSen
             */
            @Override
            public void run()
            {
                Iterator<String> it = list.iterator();
                while (true)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    while (it.hasNext())
                    {
                        String str = it.next();
                        if (str.equals("xx"))
                        {
                            it.remove();
                        }
                    }

                    for (String string : list)
                    {
                        System.out.println(string);
                    }
                }
            }

        }.start();

    }
}
