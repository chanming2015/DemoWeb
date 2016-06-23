package com.github.chanming.classloader;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Description: 
 * Create Date:2016年6月22日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class GenericTest
{

    private Map<String, Integer> score;
    private String name;

    public static void main(String[] args) throws Exception
    {
        Class<GenericTest> clazz = GenericTest.class;
        Field field = clazz.getDeclaredField("score");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType)
        {
            ParameterizedType ptype = (ParameterizedType) type;
            System.out.println(ptype.getTypeName());
            for (Type string : ptype.getActualTypeArguments())
            {
                System.out.println(string.getTypeName());
            }
        }

        field = clazz.getDeclaredField("name");
        type = field.getGenericType();
        System.out.println(type);
    }
}
