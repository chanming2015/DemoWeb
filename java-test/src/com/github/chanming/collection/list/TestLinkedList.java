package com.github.chanming.collection.list;

import java.util.LinkedList;

/**
 * @author XuMaoSen
 * Create Date:2016年3月5日 上午12:19:23
 * Description:
 * Version:1.0.0
 */
public class TestLinkedList
{

    public static void main(String[] args)
    {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.offer("333");
        linkedList.push("222");
        linkedList.offerFirst("111");

        for (String string : linkedList)
        {
            System.out.println(string);
        }

        linkedList.pop();
        linkedList.pollLast();

        for (String string : linkedList)
        {
            System.out.println(string);
        }
    }
}
