package com.github.chanming.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XuMaoSen
 * Create Date:2016年3月9日 下午9:57:33
 * Description:
 * Version:1.0.0
 */
public class TestSync
{
    public static void main(String[] args)
    {
        List<String> list = Collections
                .synchronizedList(new ArrayList<String>());

        System.out.println(list);

        List<Integer> integers = Collections.emptyList();

        System.out.println(integers);

        List<String> listOne = Collections.singletonList("aa");

        System.out.println(listOne);

        List<String> listUnmod = Collections.unmodifiableList(listOne);

        System.out.println(listUnmod);
        if (list instanceof List)
        {
            System.out.println("haha");
        }
    }
}
