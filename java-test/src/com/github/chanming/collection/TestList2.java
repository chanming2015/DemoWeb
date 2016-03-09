package com.github.chanming.collection;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author XuMaoSen
 * Create Date:2016年3月4日 下午11:44:25
 * Description:
 * Version:1.0.0
 */
public class TestList2
{
    public static void main(String[] args)
    {
        ArrayList<String> arrayList = new ArrayList<String>(4);
        arrayList.add("hahaha");
        arrayList.add("wowowo");

        ListIterator<String> it = arrayList.listIterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
            it.add("********");
        }

        System.out.println("----------------------");

        for (String string : arrayList)
        {
            System.out.println(string);
        }

        System.out.println("----------------------");

        while (it.hasPrevious())
        {
            String str = it.previous();
            if (str.equals("********"))
            {
                it.remove();
                continue;
            }
            System.out.println(str);
        }

        System.out.println("----------------------");

        for (String string : arrayList)
        {
            System.out.println(string);
        }

    }
}
