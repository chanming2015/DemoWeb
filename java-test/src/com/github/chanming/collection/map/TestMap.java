package com.github.chanming.collection.map;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.github.chanming.collection.R;
import com.github.chanming.enums.SeasonEnum;

/**
 * @author XuMaoSen
 * Create Date:2016年3月9日 下午9:19:38
 * Description:
 * Version:1.0.0
 */
public class TestMap
{
    public static void main(String[] args)
    {
        HashMap<R, String> hashMap = new HashMap<R, String>();
        hashMap.put(new R(3), "3");
        hashMap.put(new R(1), "1");
        hashMap.put(new R(2), "2");
        hashMap.put(new R(5), "5");
        hashMap.put(new R(4), "4");

        System.out.println(hashMap);

        LinkedHashMap<R, String> linkedHashMap = new LinkedHashMap<R, String>();
        linkedHashMap.put(new R(3), "3");
        linkedHashMap.put(new R(1), "1");
        linkedHashMap.put(new R(2), "2");
        linkedHashMap.put(new R(5), "5");
        linkedHashMap.put(new R(4), "4");

        System.out.println(linkedHashMap);

        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        treeMap.put(3, "3");
        treeMap.put(1, "1");
        treeMap.put(2, "2");
        treeMap.put(5, "5");
        treeMap.put(4, "4");

        System.out.println(treeMap);

        EnumMap<SeasonEnum, String> enumMap = new EnumMap<SeasonEnum, String>(
                SeasonEnum.class);

        enumMap.put(SeasonEnum.SPRING, "春天");
        enumMap.put(SeasonEnum.SUMMER, "夏天");
        System.out.println(enumMap);
    }
}
