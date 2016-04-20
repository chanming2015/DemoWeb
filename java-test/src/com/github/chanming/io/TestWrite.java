package com.github.chanming.io;

import java.io.File;

/**
 * Description: 
 * Create Date:2016年4月20日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class TestWrite
{

    public static void main(String[] args)
    {
        String content = "0123456789abcdefgtdfhjgvbukyghjfvjyfv";

        int count = 400;

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            TestIOStream.writeString(new File("D:/test/aaa.txt"), content, true);
        }
        long l2 = System.currentTimeMillis();

        for (int i = 0; i < count; i++)
        {
            TestIOChannel.writeString(new File("D:/test/bbb.txt"), content, true);
        }
        long l3 = System.currentTimeMillis();

        for (int i = 0; i < count; i++)
        {
            TestIOFiles.writeString(new File("D:/test/ccc.txt"), content, true);
        }
        long l4 = System.currentTimeMillis();

        System.out.println(l2 - l1);
        System.out.println(l3 - l2);
        System.out.println(l4 - l3);
    }
}
