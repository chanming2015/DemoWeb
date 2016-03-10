package com.github.chanming.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author XuMaoSen
 * Create Date:2016年3月9日 下午9:42:40
 * Description:
 * Version:1.0.0
 */
public class TestSort
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(5);
        list.add(4);
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        Collections.swap(list, 1, 2);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        System.out.println(Collections.binarySearch(list, 3));

        Collections.rotate(list, 3);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println(list);

        System.out.println(Collections.max(list));
    }
}
