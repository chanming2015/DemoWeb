package com.github.chanming.collection.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XuMaoSen
 * Create Date:2016年3月5日 上午12:25:49
 * Description:
 * Version:1.0.0
 */
public class TestList3
{
    public static void main(String[] args)
    {
        String[] strs = new String[2000000];

        for (int i = 0; i < strs.length; i++)
        {
            strs[i] = i * 13 + "";
        }

        Thread t1 = new Thread()
        {
            @Override
            public void run()
            {
                long begin = System.currentTimeMillis();
                List<String> arrayList = Arrays.asList(strs);

                for (String string : arrayList)
                {
                    System.out.print("");
                }
                long end = System.currentTimeMillis();
                System.out.println("arrayList    " + (end - begin));
            }

        };

        Thread t2 = new Thread()
        {
            @Override
            public void run()
            {
                LinkedList<String> linkedList = new LinkedList<String>();
                long begin = System.currentTimeMillis();

                for (int i = 0; i < strs.length; i++)
                {
                    linkedList.offer(strs[i]);
                }
                for (String string : linkedList)
                {
                    System.out.print("");
                }
                long end = System.currentTimeMillis();
                System.out.println("linkedList    " + (end - begin));
            }

        };
        t2.start();
        t1.start();

    }
}
