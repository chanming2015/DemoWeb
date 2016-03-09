package com.github.chanming.collection;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import com.github.chanming.enums.SeasonEnum;

/**
 * @author XuMaoSen
 * Create Date:2016年3月4日 下午9:49:41
 * Description:
 * Version:1.0.0
 */
public class TestSet
{

    public static void main(String[] args)
    {
        HashSet<R> hashSet = new HashSet<R>();
        hashSet.add(new R(3));
        hashSet.add(new R(1));
        hashSet.add(new R(2));
        hashSet.add(new R(5));
        hashSet.add(new R(4));

        System.out.println(hashSet);

        Iterator<R> it = hashSet.iterator();
        R first = it.next();

        first.count = 5;
        System.out.println(hashSet);

        hashSet.remove(new R(1));
        hashSet.remove(new R(5));
        System.out.println(hashSet);

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.add("A");
        linkedHashSet.add("C");
        linkedHashSet.add("B");
        linkedHashSet.add("E");
        linkedHashSet.add("D");
        System.out.println(linkedHashSet);

        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(1);
        treeSet.add(4);
        System.out.println(treeSet);

        EnumSet<SeasonEnum> enumSet = EnumSet.allOf(SeasonEnum.class);
        System.out.println(enumSet);

    }

}
